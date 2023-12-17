package com.xpluo.shortlink.admin.common.enums;

import com.xpluo.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * 用户错误码
 *
 * @author luoxiaopeng
 * @date 2023/12/13
 */
public enum UserErrorCodeEnum implements IErrorCode {
    USER_NULL("B000200", "用户不存在"),
    USER_NAME_EXIST("B000201", "用户名已存在"),
    USER_EXIST("B000202", "用户已存在"),
    USER_REGISTER_FAILED("B000203", "用户注册失败");

    private final String code;
    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
