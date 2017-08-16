package com.itmuch.service;

import com.itmuch.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;


/**
 * Created by leo on 2017/8/16.
 */
@Service
public class AggregationService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id){
        return Observable.create(observer-> {
            //请求用户微服务的端点
            User user=restTemplate.getForObject("http://microservice-provider-user/{id}",User.class,id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserById(Long id){
        return Observable.create(observer-> {
            //请求电影微服务端点
            User user=restTemplate.getForObject("http://microservice-consumer-movie/user/{id}",User.class,id);
            observer.onNext(user);
            observer.onCompleted();
        });
    }

    public User fallback(Long id){
        User user=new User();
        user.setId(-1L);
        return user;
    }

}
