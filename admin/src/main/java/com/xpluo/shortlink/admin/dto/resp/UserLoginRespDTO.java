package com.xpluo.shortlink.admin.dto.resp;

/**
 * @author luoxiaopeng
 * @date 2023/12/23
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录接口响应体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRespDTO {
    /**
     * 用户Token
     */
    private String token;
}
