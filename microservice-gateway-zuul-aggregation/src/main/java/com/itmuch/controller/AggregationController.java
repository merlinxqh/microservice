package com.itmuch.controller;

import com.itmuch.entity.User;
import com.itmuch.service.AggregationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

/**
 * Created by leo on 2017/8/16.
 */
@RestController
public class AggregationController {
    public static final Logger logger= LoggerFactory.getLogger(AggregationController.class);

    @Autowired
    private AggregationService aggregationService;

    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String,User>> aggregate(@PathVariable Long id){
        return toDeferredResult(aggregateObservable(id));
    }

    public Observable<HashMap<String,User>> aggregateObservable(Long id){
        //合并两个或多个Observalbes发射出的数据项,根据指定的函数变换他们
        return Observable.zip(
                this.aggregationService.getUserById(id),
                this.aggregationService.getMovieUserById(id),
                (user,movie)-> {
                    HashMap<String,User> map=new HashMap<>();
                    map.put("user",user);
                    map.put("movie",movie);
                    return map;
                }
        );
    }

    public DeferredResult<HashMap<String,User>> toDeferredResult(Observable<HashMap<String,User>> details){
        DeferredResult<HashMap<String,User>> result=new DeferredResult<>();

        //订阅
        details.subscribe(new Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                logger.info("完成....");
            }

            @Override
            public void onError(Throwable throwable) {
                logger.info("出错了...",throwable);
            }

            @Override
            public void onNext(HashMap<String, User> movieDetails) {
                result.setResult(movieDetails);
            }
        });
        return  result;
    }
}
