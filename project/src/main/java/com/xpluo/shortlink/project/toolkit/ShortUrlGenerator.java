package com.xpluo.shortlink.project.toolkit;

import cn.hutool.core.lang.hash.MurmurHash;

/**
 * 生成6位的短链接
 *
 * @author luoxiaopeng
 * @date 2024/2/15
 */
public class ShortUrlGenerator {
    /**
     * 生成的短链接中每个字符只能是大小写字母，或者0-9的数字
     */
    private static final char[] BITS = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final int SIZE = BITS.length;

    private static String convertDecToBase62(long num, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = (int) (num % SIZE);
            sb.append(BITS[idx]);
            num /= SIZE;
        }
        return sb.reverse().toString();
    }

    /**
     * 生成6位短链接
     *
     * @param url 原始链接
     * @return 短链接
     */
    public static String generateShortUrl(String url, int length) {
        int hashcode = MurmurHash.hash32(url);
        long num = hashcode < 0 ? Integer.MAX_VALUE - (long) hashcode : hashcode;
        return convertDecToBase62(num, length);
    }
}