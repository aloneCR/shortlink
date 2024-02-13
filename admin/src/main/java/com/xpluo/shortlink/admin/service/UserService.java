package com.xpluo.shortlink.admin.service;

import com.xpluo.shortlink.admin.dto.req.user.UserLoginReqDTO;
import com.xpluo.shortlink.admin.dto.req.user.UserLogoutReqDTO;
import com.xpluo.shortlink.admin.dto.req.user.UserRegisterReqDTO;
import com.xpluo.shortlink.admin.dto.req.user.UserUpdateReqDTO;
import com.xpluo.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.xpluo.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @author luoxiaopeng
 * @date 2023/12/10
 */
public interface UserService {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户响应实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 是否存在 True:存在 False:不存在
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     *
     * @param requestParam 注册用户的请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 根据用户名更新用户信息
     *
     * @param requestParam 用户信息
     */
    void update(UserUpdateReqDTO requestParam);

    /**
     * 用户登录
     *
     * @param requestParam 请求体
     * @return 响应体
     */
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    /**
     * 检查用户是否登录
     *
     * @param username 用户名
     * @param token    用户登录Token
     * @return Ture: 已登录 False: 未登录
     */
    Boolean checkLogin(String username, String token);

    Boolean logout(UserLogoutReqDTO requestParam);
}
