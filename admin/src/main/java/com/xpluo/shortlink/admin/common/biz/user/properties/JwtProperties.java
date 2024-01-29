package com.xpluo.shortlink.admin.common.biz.user.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 生成token的相关配置
 *
 * @author luoxiaopeng
 * @date 2024/2/4
 */
@Component
@ConfigurationProperties(prefix = "short-link.jwt")
@Data
public class JwtProperties {
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}
