package com.xpluo.shortlink.admin.config;

import com.xpluo.shortlink.admin.common.biz.user.UserTransmitInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private UserTransmitInterceptor userTransmitInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册自定义拦截器...");
        registry.addInterceptor(userTransmitInterceptor)
                .addPathPatterns("/api/shortlink/v1/**")
                .excludePathPatterns("/api/shortlink/v1/user/login",
                        "/api/shortlink/v1/admin/user");
    }
}
