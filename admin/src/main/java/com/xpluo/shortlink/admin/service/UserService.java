package com.xpluo.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xpluo.shortlink.admin.dao.entity.UserDO;
import com.xpluo.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.xpluo.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @author luoxiaopeng
 * @date 2023/12/10
 */
public interface UserService extends IService<UserDO> {
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
}
