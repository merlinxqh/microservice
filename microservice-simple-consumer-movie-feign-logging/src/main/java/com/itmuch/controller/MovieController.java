package com.itmuch.controller;

import com.itmuch.entity.User;
import com.itmuch.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by leo on 2017/7/28.
 */
@RestController
public class MovieController {

    private static final Logger logger= LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        return this.restTemplate.getForObject("http://localhost:8000/"+id,User.class);
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){
        return userFeignClient.findById(id);
    }

    /**
     * post请求多参数
     * @param user
     * @return
     */
    @PostMapping("/post")
    public User post(@RequestBody User user){
        return this.userFeignClient.post(user);
    }

    @GetMapping("/log-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance=loadBalancerClient.choose("microservice-provider-user");
        logger.info("{},{},{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());

    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return this.discoveryClient.getInstances("microservice-provider-user");
    }
}
