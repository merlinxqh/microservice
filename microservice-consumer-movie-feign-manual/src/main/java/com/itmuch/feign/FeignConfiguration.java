package com.itmuch.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by leo on 2017/7/29.
 * Feign的配置类
 */
//@Configuration
public class FeignConfiguration {

//    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }
}
