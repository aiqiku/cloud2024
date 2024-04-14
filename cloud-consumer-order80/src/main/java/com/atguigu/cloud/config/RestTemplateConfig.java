package com.atguigu.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author aiqiku
 * @create 2024/4/10 12:37
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //consul默认支持负载均衡 所以需要在RestTemplate添加LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
