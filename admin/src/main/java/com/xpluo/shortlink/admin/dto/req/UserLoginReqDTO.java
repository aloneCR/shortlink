package com.xpluo.shortlink.admin.dto.req;

import lombok.Data;

/**
 * 用户登录请求体
 * @author luoxiaopeng
 * @date 2023/12/23
 */
@Data
public class UserLoginReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
