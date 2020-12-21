package com.wawahei.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 10:31
 **/
@Data
@AllArgsConstructor
public class Role {

    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permission> permissions;
}