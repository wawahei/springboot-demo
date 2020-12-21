package com.wawahei.demo.service;

import com.wawahei.demo.bean.User;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 10:34
 **/
public interface LoginService {

    public User getUserByName(String name);
}
