package com.ms.cart.interceptor;

import com.ms.auth.entity.UserInfo;
import com.ms.auth.utils.JwtUtils;
import com.ms.cart.config.JwtProperties;
import com.ms.common.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuai.Ma
 * @date 2022/4/12 20:20
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtProperties jwtProperties;

    // 定义一个线程域，存放登录用户
    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public LoginInterceptor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * 前置方法
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 查询cookie中的token
        String token = CookieUtils.getCookieValue(request, "LY_TOKEN");
        if (StringUtils.isBlank(token)) {
            // 未登录,返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        // 有token，查询用户信息
        try {
            // 解析成功，证明已经登录
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, jwtProperties.getPublicKey());
            // 放入线程域
            THREAD_LOCAL.set(userInfo);
            return true;
        } catch (Exception e){
            // 抛出异常，证明未登录,返回401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

    }

    /**
     * 完成方法
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //结束后清除线程局部变量，应为使用的是Tomcat的线程池，线程不会结束，也就不会释放线程的局部变量
        THREAD_LOCAL.remove();
    }

    public static UserInfo getLoginUser() {
        return THREAD_LOCAL.get();
    }
}
