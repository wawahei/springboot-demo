package com.wawahei.demo.controller.login;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.response.BackResult;
import com.wawahei.demo.response.ResponseCodeEnum;
import com.wawahei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 17:54
 **/
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/api/user/login")
    public BackResult login(HttpServletRequest request, String name){
        User user = userService.getInfo(name);
        if(user != null){
            HttpSession session = request.getSession();
            session.setAttribute("loginUserId",user.getId());
            redisTemplate.opsForValue().set("loginUser:"+user.getId(),session.getId());
            return new BackResult(1);
        }
        return new BackResult(ResponseCodeEnum.LOGIN_ERROR);
    }

    @RequestMapping("/api/user/getUserInfo")
    public BackResult getUserInfo(String name){
        User user = userService.getInfo(name);
        if(user!=null){
            return new BackResult(user);
        }
        return new BackResult(ResponseCodeEnum.LOGIN_NOT_EXIST);
    }
}