package com.wawahei.demo.service;

import com.wawahei.demo.bean.User;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-16 13:31
 **/
public interface UserService {
    User getInfo(String name);
}