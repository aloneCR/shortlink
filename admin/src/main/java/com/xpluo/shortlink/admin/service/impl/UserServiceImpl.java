package com.xpluo.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xpluo.shortlink.admin.common.biz.user.properties.JwtProperties;
import com.xpluo.shortlink.admin.common.constant.UserConstant;
import com.xpluo.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.xpluo.shortlink.admin.common.convention.exception.ClientException;
import com.xpluo.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.xpluo.shortlink.admin.dao.entity.UserDO;
import com.xpluo.shortlink.admin.dao.mapper.UserMapper;
import com.xpluo.shortlink.admin.dto.req.user.UserLoginReqDTO;
import com.xpluo.shortlink.admin.dto.req.user.UserLogoutReqDTO;
import com.xpluo.shortlink.admin.dto.req.user.UserRegisterReqDTO;
import com.xpluo.shortlink.admin.dto.req.user.UserUpdateReqDTO;
import com.xpluo.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.xpluo.shortlink.admin.dto.resp.UserRespDTO;
import com.xpluo.shortlink.admin.service.UserService;
import com.xpluo.shortlink.admin.toolkit.JwtUtil;
import jakarta.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.xpluo.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.xpluo.shortlink.admin.common.enums.UserErrorCodeEnum.USER_EXIST;
import static com.xpluo.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;

/**
 * 用户接口实现层
 *
 * @author luoxiaopeng
 * @date 2023/12/10
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RBloomFilter<String> userRegisterBloomFilter;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        UserDO userDO = userMapper.getUserByUsername(username);
        if (null == userDO) {
            throw new ClientException("用户名不存在", BaseErrorCode.CLIENT_ERROR);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (hasUsername(requestParam.getUsername())) {
            throw new ClientException("用户名已存在", USER_NAME_EXIST);
        }

        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());

        if (lock.tryLock()) {
            try {
                int inserted = userMapper.insertUser(BeanUtil.toBean(requestParam, UserDO.class));
                if (inserted <= 0) {
                    throw new ClientException(UserErrorCodeEnum.USER_REGISTER_FAILED);
                }
                userRegisterBloomFilter.add(requestParam.getUsername());
            } catch (DuplicateKeyException ex) {
                throw new ClientException(USER_EXIST);
            } finally {
                lock.unlock();
            }
        } else {
            throw new ClientException(USER_NAME_EXIST);
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO 验证当前用户是否为登录用户，并验证用户名是否和当前登录用户名一致
        LambdaQueryWrapper<UserDO> wrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        userMapper.updateUser(BeanUtil.toBean(requestParam, UserDO.class));
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {
        // 使用lambda过滤器判断用户是否已经存在
        if (!userRegisterBloomFilter.contains(requestParam.getUsername())) {
            throw new ClientException("用户不存在");
        }

        Map<Object, Object> hasLoginMap = stringRedisTemplate.opsForHash().entries("login_" + requestParam.getUsername());
        if (CollUtil.isNotEmpty(hasLoginMap)) {
            String token = hasLoginMap.keySet().stream()
                    .findFirst()
                    .map(Object::toString)
                    .orElseThrow(() -> new ClientException("用户登录错误"));
            return new UserLoginRespDTO(token);
        }

        UserDO userDO = userMapper.getUserByUsernameAndPassword(requestParam.getUsername(), requestParam.getPassword());
        if (userDO == null) {
            throw new ClientException("用户不存在");
        }
        // 生成令牌Token
        Map<String, Object> claims = new HashMap<>();
        claims.put(UserConstant.USER_ID_KEY, userDO.getUsername());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), token, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.MINUTES);


        return new UserLoginRespDTO(token);
    }

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get(username, token) != null;
    }

    @Override
    public Boolean logout(UserLogoutReqDTO requestParam) {
        // 判断用户是否已经登录
        if (stringRedisTemplate.opsForHash().get("login_" + requestParam.getUsername(), requestParam.getToken()) != null) {
            // 清空Redis中用户登录信息
            if (Boolean.TRUE.equals(stringRedisTemplate.delete("login_" + requestParam.getUsername()))) {
                return true;
            }
        }
        throw new ClientException("用户退出登录失败");
    }
}
