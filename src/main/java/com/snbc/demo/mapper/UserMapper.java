package com.snbc.demo.mapper;

import com.snbc.demo.bean.User;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-16 13:28
 **/
public interface UserMapper {

    User getInfo(String name);
}
