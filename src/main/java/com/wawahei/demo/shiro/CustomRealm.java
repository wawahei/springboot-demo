package com.wawahei.demo.shiro;

import com.wawahei.demo.bean.Permission;
import com.wawahei.demo.bean.Role;
import com.wawahei.demo.bean.User;
import com.wawahei.demo.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 10:45
 **/
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

//    授权，即角色或者权限验证。
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String)principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Role role:user.getRoles()){
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限
            for(Permission p:role.getPermissions()){
                simpleAuthorizationInfo.addStringPermission(p.getPermissionName());
            }
        }
        return simpleAuthorizationInfo;
    }

//    身份认证/登录(账号密码验证)。
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(StringUtils.isEmpty(authenticationToken.getCredentials())){
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByName(name);
        if(user == null){
            //这里返回后会报出对应异常
            return null;
        }
        //这里验证authenticationToken和simpleAuthenticationInfo的信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,user.getPassword(),getName());
        return simpleAuthenticationInfo;
    }
}