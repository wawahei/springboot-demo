package com.wawahei.demo.controller.redis;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.response.BackResult;
import com.wawahei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-18 09:30
 **/
@RestController
public class RedisTestController {

    @Autowired
    private UserService userService;

    @GetMapping("/addSession")
    public BackResult addSession(HttpServletRequest request,String name){
        String sessionId = request.getSession().getId();
        String requestURI = request.getRequestURI()+":"+request.getServerPort();
        User user = userService.getInfo(name);
        request.getSession().setAttribute("user_"+user.getId(),user);
        Map<String, String> sessionInfoMap = new HashMap(2);
        sessionInfoMap.put("sessionId",sessionId);
        sessionInfoMap.put("requestURI",requestURI);
        BackResult backResult = new BackResult(sessionInfoMap);
        return backResult;
    }

    @GetMapping("/getSession")
    public BackResult getSession(HttpServletRequest request,String name){
        String sessionId = request.getSession().getId();
        String requestURI = request.getRequestURI()+":"+request.getServerPort();
        User user = userService.getInfo(name);
        Map<String, Object> sessionInfoMap = new HashMap<>(2);
        sessionInfoMap.put("sessionId", sessionId);
        sessionInfoMap.put("requestURI", requestURI);
        sessionInfoMap.put("user_"+user.getId(), user);
        BackResult backResult = new BackResult(sessionInfoMap);
        return backResult;
    }
}