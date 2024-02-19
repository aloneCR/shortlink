package com.xpluo.shortlink.project.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 布隆过滤器的配置
 *
 * @author luoxiaopeng
 * @date 2024/02/01
 */
@Configuration
public class RBloomFilterConfiguration {

    /**
     * 防止生成短链接时查询数据库的布隆过滤器
     */
    @Bean
    public RBloomFilter<String> shortLinkCreateCachePenetrationBloomFilter(RedissonClient redissonClient) {
        RBloomFilter<String> cachePenetrationBloomFilter = redissonClient.getBloomFilter("shortLinkCreateCachePenetrationBloomFilter");
        cachePenetrationBloomFilter.tryInit(100000000L, 0.01);
        return cachePenetrationBloomFilter;
    }
}
