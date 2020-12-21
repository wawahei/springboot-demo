package com.wawahei.demo.controller.jwt;

import com.wawahei.demo.config.JwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 16:11
 **/
@RestController
public class JwtTestController {

    @Autowired
    private JwtConfig jwtConfig;



    @RequestMapping("/jwt/login")
    public Map testJwt(String name,String password){
        Map<String,String> result = new HashMap<>() ;

        String token = jwtConfig.getToken(name+password);
        if(!StringUtils.isEmpty(token)){
            result.put("token",token) ;
        }
        result.put("userName",name) ;
        return result ;
    }

    // 需要 Token 验证的接口
    @RequestMapping("/jwt/info")
    public String info (){
        return "info" ;
    }
}