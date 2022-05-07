package com.ms.user;

import com.ms.common.exception.DrugsExecption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Shuai.Ma
 * @date 2022/3/20 15:32
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ms.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
