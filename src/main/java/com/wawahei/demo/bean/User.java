package com.wawahei.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-16 13:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private static final long serialVersionUID = -6892536116981855597L;
    private Integer id;
    private String name;
    private String password;

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
}