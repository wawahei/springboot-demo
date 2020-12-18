package com.wawahei.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 09:06
 **/
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30)
@Configuration
public class RedisSessionConfig {
    @Bean
    public static ConfigureRedisAction configureRedisAction(){
        //让springSession不再执行config命令
        return ConfigureRedisAction.NO_OP;
    }
}