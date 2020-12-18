package com.wawahei.demo.config;

import com.wawahei.demo.interceptor.RedisSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 17:51
 **/
@Configuration
public class WebSecurityConfig extends WebMvcConfigurerAdapter{

    @Bean
    public RedisSessionInterceptor getSessionInterceptor(){
        return new RedisSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/user/login");
        super.addInterceptors(registry);
    }
}