package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Shuai.Ma
 * @date 2022/3/17 11:12
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableDiscoveryClient
public class GoodsWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsWebApplication.class,args);
    }
}
