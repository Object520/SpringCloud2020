package com.gaoliang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class PaymentHystrixDashboardApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixDashboardApplication9001.class, args);
    }
}
