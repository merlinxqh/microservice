package com.itmuch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by leo on 2017/7/28.
 */
@SpringBootApplication
/**
 * 声明这是一个eureka client
 * 也可以使用 @EnableEurekaClient 注解代替. 在Spring cloud中, 服务发现组件可以有多种选择, 例如Zookeeper, Consul等.
 * @EnableEurekaClient 为各种服务组件提供了支持, 该注解是 spring-cloud-commons 项目的注解, 是一个高度的抽象.
 */
@EnableDiscoveryClient
public class ProviderUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApplication.class,args);
    }
}
