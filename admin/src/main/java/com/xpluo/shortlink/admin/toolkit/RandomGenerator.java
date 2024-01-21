package com.xpluo.shortlink.admin.toolkit;

import java.security.SecureRandom;

/**
 * @author luoxiaopeng
 * @date 2024/1/17
 * <p>
 * 生成指定长度的字符串工具类
 */
public class RandomGenerator {

    private static final String ALPHANUMERIC_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    /**
     * 生成指定长度的随机字符串
     *
     * @param length 长度
     * @return 包含数字和字符的随机字符串
     */
    public static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = ALPHANUMERIC_CHARS.charAt(random.nextInt(ALPHANUMERIC_CHARS.length()));
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
}

