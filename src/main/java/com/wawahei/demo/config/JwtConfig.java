package com.wawahei.demo.config;

import com.wawahei.demo.model.JwtEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 15:23
 **/

@Component
public class JwtConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtConfig.class);

    @Autowired
    private JwtEntity jwtEntity;

    /*
     * 根据身份ID标识，生成Token
     */
    public String getToken(String identityId){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+jwtEntity.getExpire()*1000);
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setSubject(identityId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,jwtEntity.getSecret())
                .compact();
    }

    /*
     * 获取 Token 中注册信息
     */
    public Claims getTokenClaim(String token){
        try{
            return Jwts.parser()
                    .setSigningKey(jwtEntity.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            LOGGER.error("JwtConfig.getTokenClaim",e);
            return null;
        }
    }

    /*
     * Token 是否过期验证
     */
    public boolean isTokenExpired(Date expirationTime){
        return expirationTime.before(new Date());
    }
}