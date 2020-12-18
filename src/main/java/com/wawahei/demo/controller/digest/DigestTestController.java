package com.wawahei.demo.controller.digest;

import com.wawahei.demo.interceptor.RequireAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 14:10
 **/
@RestController
public class DigestTestController {

    @RequireAuth
    @RequestMapping("/digest/login")
    public String login(){
        return "{code:0,data:{username:\"test\"}}";
    }

    @RequireAuth
    @RequestMapping("/digest/index")
    public String index(){
        return "{code:0,data:{xxx:\"xxxooo\"}}";
    }
}