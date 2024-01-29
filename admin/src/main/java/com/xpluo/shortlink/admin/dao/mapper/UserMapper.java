package com.xpluo.shortlink.admin.dao.mapper;

import com.xpluo.shortlink.admin.dao.entity.UserDO;
import org.apache.ibatis.annotations.Param;

/**
 * 用户持久层
 * @author luoxiaopeng
 * @date 2023/12/10
 */
public interface UserMapper {
    UserDO getUserByUsername(@Param("username") String username);

    int insertUser(@Param("user") UserDO userDO);

    int updateUser(@Param("user") UserDO userDO);

    UserDO getUserByUsernameAndPassword(@Param("username") String username,
                                        @Param("password") String password);
}