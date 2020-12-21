package com.wawahei.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 10:31
 **/
@Data
@AllArgsConstructor
public class Permission {
    private String id;
    private String permissionName;
}