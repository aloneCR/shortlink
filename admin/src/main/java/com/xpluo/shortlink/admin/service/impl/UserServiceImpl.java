package com.xpluo.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpluo.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.xpluo.shortlink.admin.common.convention.exception.ClientException;
import com.xpluo.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.xpluo.shortlink.admin.dao.entity.UserDO;
import com.xpluo.shortlink.admin.dao.mapper.UserMapper;
import com.xpluo.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.xpluo.shortlink.admin.dto.resp.UserRespDTO;
import com.xpluo.shortlink.admin.service.UserService;
import jakarta.annotation.Resource;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import static com.xpluo.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.xpluo.shortlink.admin.common.enums.UserErrorCodeEnum.USER_NAME_EXIST;

/**
 * 用户接口实现层
 *
 * @author luoxiaopeng
 * @date 2023/12/10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Resource
    private RBloomFilter<String> userRegisterBloomFilter;

    @Resource
    private RedissonClient redissonClient;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.
                lambdaQuery(UserDO.class).
                eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (null == userDO) {
            throw new ClientException(BaseErrorCode.CLIENT_ERROR);
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
            throw new ClientException(USER_NAME_EXIST);
        }

        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());

        if (lock.tryLock()) {
            try {
                int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if (inserted <= 0) {
                    throw new ClientException(UserErrorCodeEnum.USER_REGISTER_FAILED);
                }

                userRegisterBloomFilter.add(requestParam.getUsername());
            } finally {
                lock.unlock();
            }
        }
        throw new ClientException(USER_NAME_EXIST);
    }
}
