package com.wawahei.demo.controller;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

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
        LOGGER.debug("进入test方法");
        LOGGER.info("进入test方法");
        LOGGER.warn("进入test方法");
        LOGGER.error("进入test方法");
        return "hello";
    }
}