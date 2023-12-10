package com.xpluo.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xpluo.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.xpluo.shortlink.admin.common.convention.exception.ClientException;
import com.xpluo.shortlink.admin.dao.entity.UserDO;
import com.xpluo.shortlink.admin.dao.mapper.UserMapper;
import com.xpluo.shortlink.admin.dto.resp.UserRespDTO;
import com.xpluo.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 用户接口实现层
 *
 * @author luoxiaopeng
 * @date 2023/12/10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
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
}
