package com.itmuch.feign;

import com.itmuch.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by leo on 2017/8/4.
 */
@Component
public class FeignClientFallBack implements UserFeignClient{
    @Override
    public User findById(Long id) {
        User user=new User();
        user.setName("默认用户");
        user.setId(id);
        return user;
    }
}
