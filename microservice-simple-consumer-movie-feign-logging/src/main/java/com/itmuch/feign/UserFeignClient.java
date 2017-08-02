package com.itmuch.feign;

import com.itmuch.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by leo on 2017/7/29.
 */
@FeignClient(name = "microservice-provider-user",configuration = FeignLogConfiguration.class)
public interface UserFeignClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/findByParams", method = RequestMethod.GET)
    User findByParams(@RequestParam("id") Long id,@RequestParam("username") String username);

    @RequestMapping(value = "/findByMap",method = RequestMethod.GET)
    User findByMap(@RequestParam Map<String,Object> map);

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    User post(@RequestBody User user);
}
