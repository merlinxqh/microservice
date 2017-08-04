package com.itmuch.feign;

import com.itmuch.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by leo on 2017/8/4.
 */
@Component
public class FeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {

    public static final Logger logger= LoggerFactory.getLogger(FeignClientFallBackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
//                FeignClientFallBackFactory.logger.info("",cause);
                User user=new User();
                user.setName("默认用户1111");
                user.setId(id);
                return user;
            }
        };
    }


}
