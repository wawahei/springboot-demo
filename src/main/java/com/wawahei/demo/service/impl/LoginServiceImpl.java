package com.wawahei.demo.service.impl;

import com.wawahei.demo.bean.Permission;
import com.wawahei.demo.bean.Role;
import com.wawahei.demo.bean.User;
import com.wawahei.demo.service.LoginService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 10:35
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public User getUserByName(String name) {
        return getMapByName(name);
    }


    private User getMapByName(String name){
        Permission p1 = new Permission("1","query");
        Permission p2 = new Permission("2","add");
        Set<Permission> permissionSet = new HashSet<>();
        permissionSet.add(p1);
        permissionSet.add(p2);
        Role role = new Role("1","admin",permissionSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User(1,"kk","123456",roleSet);
        Map<String,User> map = new HashMap<>();
        map.put(user.getName(),user);

        Set<Permission> permissionSet1 = new HashSet<>();
        permissionSet1.add(p1);
        Role role1 = new Role("2","user",permissionSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User(2,"gg","123456",roleSet1);
        map.put(user1.getName(),user1);

        return map.get(name);
    }
}