package com.xpluo.shortlink.admin.dto.req.user;

import lombok.Data;

/**
 * 用户注册请求体
 *
 * @author luoxiaopeng
 * @date 2023/12/13
 */
@Data
public class UserRegisterReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
