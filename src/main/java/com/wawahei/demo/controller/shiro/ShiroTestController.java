package com.wawahei.demo.controller.shiro;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.response.BackResult;
import com.wawahei.demo.response.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 11:17
 **/
@RestController
@Slf4j
public class ShiroTestController {

    @GetMapping("/shiro/login")
    public BackResult login(User user){
        if(StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())){
            return new BackResult(ResponseCodeEnum.LOGIN_PARAM_EMPTY);
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(),user.getPassword());
        try{
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
            //subject.checkRole("admin");
            //subject.checkPermissions("query", "add");
        }catch (UnknownAccountException e){
            log.error("用户名不存在！", e);
            return new BackResult(ResponseCodeEnum.LOGIN_NOT_EXIST);
        }catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return new BackResult(ResponseCodeEnum.LOGIN_ERROR);
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return new BackResult(ResponseCodeEnum.NO_PRIMISSION);
        }
        return new BackResult();
    }

    @RequiresRoles("admin")
    @GetMapping("/shiro/admin")
    public BackResult admin() {
        return new BackResult("admin success!");
    }

    @RequiresPermissions("query")
    @GetMapping("/shiro/index")
    public BackResult index() {
        return new BackResult("index success!");
    }

    @RequiresPermissions("add")
    @GetMapping("/shiro/add")
    public BackResult add() {
        return new BackResult("add success!");
    }
}