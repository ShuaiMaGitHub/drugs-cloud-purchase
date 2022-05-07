package com.ms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@MapperScan("com.ms.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class);
    }
}
