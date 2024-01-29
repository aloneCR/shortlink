package com.xpluo.shortlink.admin.common.biz.user;

import com.xpluo.shortlink.admin.common.biz.user.properties.JwtProperties;
import com.xpluo.shortlink.admin.common.constant.UserConstant;
import com.xpluo.shortlink.admin.toolkit.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author luoxiaopeng
 * @date 2024/2/4
 */
@Component
@Slf4j
public class UserTransmitInterceptor implements HandlerInterceptor {
    @Resource
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        String token = request.getHeader(jwtProperties.getUserTokenName());
        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            UserInfoDTO userInfoDTO = UserInfoDTO
                    .builder()
                    .username(claims.get(UserConstant.USER_ID_KEY, String.class))
                    .build();
            UserContext.setUser(userInfoDTO);
        } catch (ExpiredJwtException ex) {
            log.error("Token已过期，请重新登录");
            return false;
        } catch (Exception ex) {
            log.error("鉴权失败:{}", ex.getMessage(), ex);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        UserContext.removeUser();
    }
}
