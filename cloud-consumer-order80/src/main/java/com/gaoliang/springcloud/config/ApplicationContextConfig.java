package com.gaoliang.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
    @LoadBalanced// 赋予RestTemplate 负载均衡的能力, 根据默认机制调用具体的某个端口的服务
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }
}
