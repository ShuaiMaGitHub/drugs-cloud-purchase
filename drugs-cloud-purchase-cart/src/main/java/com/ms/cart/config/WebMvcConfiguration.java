package com.ms.cart.config;

import com.ms.cart.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Shuai.Ma
 * @date 2022/4/12 20:26
 */
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtProperties jwtProperties;

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor(jwtProperties);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
    }
}
