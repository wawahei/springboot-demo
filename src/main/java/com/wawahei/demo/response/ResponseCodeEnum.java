package com.wawahei.demo.response;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 17:43
 **/
public enum  ResponseCodeEnum {

    SUCCESS("2000", "成功！"),
    INTERNAL_SERVER_ERROR("5000", "未知错误，请联系管理员！"),

    LOGIN_ERROR("3000","用户名或密码错误！"),
    LOGIN_NOT_EXIST("3001","用户不存在！"),
    NEED_LOGIN("3999","用户未登录！");

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