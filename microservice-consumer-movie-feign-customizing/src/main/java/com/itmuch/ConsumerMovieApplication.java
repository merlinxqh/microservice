package com.itmuch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by leo on 2017/7/28.
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class ConsumerMovieApplication {

    /**
     * 实例化一个bean 并使用该方法名作为bean的名称.
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsumerMovieApplication.class,args);
    }
}
