package com.wawahei.demo.config;

import com.wawahei.demo.interceptor.RequireAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 14:07
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        RequireAuthInterceptor requireAuthInterceptor = new RequireAuthInterceptor();
        registry.addInterceptor(requireAuthInterceptor);
    }
}