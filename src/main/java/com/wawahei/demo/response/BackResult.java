package com.wawahei.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-17 17:42
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackResult<T> implements Serializable {
    private static final long serialVersionUID = -4893056161330875398L;

    //返回代码
    private String code = ResponseCodeEnum.SUCCESS.getCode();

    //异常信息
    private String message = ResponseCodeEnum.SUCCESS.getMessage();

    //返回数据
    private T data;

    public BackResult(ResponseCodeEnum responseCodeEnum) {
        this.code = responseCodeEnum.getCode();
        this.message = responseCodeEnum.getMessage();
    }

    public BackResult(T data) {
        this.data = data;
    }
}