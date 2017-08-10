//package com.itmuch.ribbon;
//
//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Created by leo on 2017/7/29.
// * 该类为ribbon的配置类 , 此配置不灵活 配置属性文件 application.yml 实现负载均衡配置
// */
////@Configuration
//public class RibbonConfiguration {
//
//    @Bean
//    public IRule ribbonRule(){
//        //负载均衡规则,改为随机
//        return new RandomRule();
//    }
//}
