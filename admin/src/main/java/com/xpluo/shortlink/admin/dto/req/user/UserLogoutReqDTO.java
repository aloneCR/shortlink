package com.xpluo.shortlink.admin.dto.req.user;

import lombok.Data;

/**
 * @author luoxiaopeng
 * @date 2023/12/24
 */
@Data
public class UserLogoutReqDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户token
     */
    private String token;
}
