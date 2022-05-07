package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/13 16:46
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableDiscoveryClient
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);
    }
}
