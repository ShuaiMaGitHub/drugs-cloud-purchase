package com.ms.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * cors解决跨域问题
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 11:05
 */
@Configuration
public class DrugsCorsConfiguration {

    @Bean
    public CorsFilter corsFilter(){
        //初始化cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        //允许跨域的域名，如果要携带cookie，不能写*。*：代表所有域名都可以跨域访问
        configuration.addAllowedOrigin("http://manage.drugs.com");
        configuration.setAllowCredentials(true); //允许携带cookie
        configuration.addAllowedMethod("*"); //代表所有的请求方法
        configuration.addAllowedHeader("*"); //允许携带任何的头信息

        //初始化cors配置源对象
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**",configuration);

        //返回corsFilter实例，参数：cors配置源对象
        return new CorsFilter(configurationSource);
    }


}