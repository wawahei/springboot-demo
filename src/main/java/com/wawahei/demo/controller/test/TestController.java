package com.wawahei.demo.controller.test;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-16 15:15
 **/
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{name}")
    public User getUser(@PathVariable("name") String name){
        return  userService.getInfo(name);
    }
}