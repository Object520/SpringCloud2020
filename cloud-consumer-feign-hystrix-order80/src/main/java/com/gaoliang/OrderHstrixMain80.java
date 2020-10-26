package com.gaoliang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrderHstrixMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHstrixMain80.class, args);
    }
}
