package com.snbc.demo.controller;

import com.snbc.demo.bean.User;
import com.snbc.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-16 11:24
 **/
@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String sayHello(){
        return "index";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(String name){
        return  userService.getInfo(name);
    }

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("welcome","welcome you come");
        return "hello";
    }
}