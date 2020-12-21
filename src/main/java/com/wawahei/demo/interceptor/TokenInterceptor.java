package com.wawahei.demo.interceptor;

import com.wawahei.demo.config.JwtConfig;
import com.wawahei.demo.exception.CustomException;
import com.wawahei.demo.model.JwtEntity;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 15:52
 **/
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JwtEntity jwtEntity;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if(uri.contains("/jwt/login")){
            return true;
        }

        String token = request.getHeader(jwtEntity.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtEntity.getHeader());
        }
        if(StringUtils.isEmpty(token)){
            throw new CustomException(jwtEntity.getHeader()+"不能为空");
        }

        Claims claims = jwtConfig.getTokenClaim(token);
        if(claims == null || jwtConfig.isTokenExpired(claims.getExpiration())){
            throw new CustomException(jwtEntity.getHeader()+"失效，请重新登录");
        }
        //设置 identityId 用户身份ID
        request.setAttribute("identityId",claims.getSubject());


        return true;
    }
}