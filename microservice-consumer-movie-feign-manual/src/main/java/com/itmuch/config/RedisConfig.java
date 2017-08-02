package com.itmuch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * Created by leo on 2017/8/2.
 */
@Configuration
public class RedisConfig {

    @Bean
    public JedisPool jedisPool(){
        return new JedisPool("172.16.4.18",22121);
    }
}
