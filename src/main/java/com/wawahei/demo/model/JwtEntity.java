package com.wawahei.demo.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 15:39
 **/
@Configuration
@ConfigurationProperties(prefix = "config.jwt")
@PropertySource("classpath:jwtcofig.properties")
@Data
public class JwtEntity {
    private String secret;
    private long expire;
    private String header;
}