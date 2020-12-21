package com.wawahei.demo.response;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 17:43
 **/
public enum  ResponseCodeEnum {

    SUCCESS("200", "成功！"),
    INTERNAL_SERVER_ERROR("500", "未知错误，请联系管理员！"),

    LOGIN_ERROR("300","用户名或密码错误！"),
    LOGIN_NOT_EXIST("301","用户不存在！"),
    LOGIN_PARAM_EMPTY("302","用户名或者密码为空"),
    LOGIN_PARAM_ERROR("303","用户名或者密码错误"),
    NO_PRIMISSION("304","没有权限"),
    NEED_LOGIN("399","用户未登录！");



    public String code;
    public String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}