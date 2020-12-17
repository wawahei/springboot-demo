package com.wawahei.demo.service.impl;

import com.wawahei.demo.bean.User;
import com.wawahei.demo.mapper.UserMapper;
import com.wawahei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-16 13:32
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getInfo(String name) {
        return userMapper.getInfo(name);
    }
}