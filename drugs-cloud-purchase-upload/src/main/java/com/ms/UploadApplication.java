package com.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 16:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class);
    }
}
