package com.xpluo.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * 用户响应实体类
 * @author luoxiaopeng
 * @date 2023/12/10
 */
@Data
public class UserRespDTO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

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
