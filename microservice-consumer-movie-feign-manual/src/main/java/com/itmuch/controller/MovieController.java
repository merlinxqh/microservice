package com.itmuch.controller;

import com.itmuch.entity.User;
import com.itmuch.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by leo on 2017/7/28.
 */
@Import(FeignClientsConfiguration.class) //spring cloud为feign默认提供的配置类
@RestController
public class MovieController {

    private static final Logger logger= LoggerFactory.getLogger(MovieController.class);

    private UserFeignClient userFeignClient;

    private UserFeignClient adminFeignClient;

    @Autowired
    public MovieController(Decoder decoder,Encoder encoder,Client client, Contract contract){
        this.userFeignClient= Feign.builder().client(client).encoder(encoder).decoder(decoder)
                .requestInterceptor(new BasicAuthRequestInterceptor("user","password1"))
                .target(UserFeignClient.class,"http://microservice-provider-user");
        this.adminFeignClient= Feign.builder().client(client).encoder(encoder).decoder(decoder)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin","password2"))
                .target(UserFeignClient.class,"http://microservice-provider-user");
    }

    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id){
       return this.userFeignClient.findById(id);
    }

    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id){
        return this.adminFeignClient.findById(id);
    }

}
