package com.itmuch.feign;

import com.itmuch.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by leo on 2017/7/29.
 */
@FeignClient(name = "microservice-provider-user",
//        fallback = UserFeignClient.FeignClientFallBack.class
        fallbackFactory = UserFeignClient.FeignClientFallBackFactory.class,
        configuration = FeignDisableHystrixConfiguration.class //禁用hystrix
)
public interface UserFeignClient {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    @Component
    class FeignClientFallBackFactory implements FallbackFactory<UserFeignClient> {

        public static final Logger logger= LoggerFactory.getLogger(FeignClientFallBackFactory.class);

        @Override
        public UserFeignClient create(Throwable cause) {
            return new UserFeignClient() {
                @Override
                public User findById(Long id) {
                    //打印回退原因
                    FeignClientFallBackFactory.logger.info("fallback, reason was:",cause);
                    User user=new User();
                    user.setName("默认用户1111");
                    user.setId(id);
                    return user;
                }
            };
        }


    }

}
