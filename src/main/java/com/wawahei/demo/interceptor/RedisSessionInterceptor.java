package com.wawahei.demo.interceptor;

import com.wawahei.demo.response.BackResult;
import com.wawahei.demo.response.ResponseCodeEnum;
import com.wawahei.demo.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 17:17
 **/
public class RedisSessionInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //无论访问的地址是不是正确的，都进行登录验证，登录成功后的访问再进行分发，404的访问自然会进入到错误控制器中
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUserId") != null){
            try{
                Object loginUserId = session.getAttribute("loginUserId");
                String loginSessionId = (String)redisTemplate.opsForValue().get("loginUser:"+loginUserId);
                if(loginSessionId != null && loginSessionId.equals(session.getId())){
                    return true;
                }
            }catch (Exception e){
                LOGGER.error("RedisSessionInterceptor.preHandle:",e);
            }
        }

        response401(response);
        return false;
    }

    private void response401(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            BackResult backResult = new BackResult(ResponseCodeEnum.NEED_LOGIN);
            response.getWriter().print(JsonUtils.objectToJson(backResult));
        } catch (IOException e) {
            LOGGER.error("RedisSessionInterceptor.response401:",e);
        }
    }

}