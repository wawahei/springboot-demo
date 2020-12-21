package com.wawahei.demo.exception;

import com.wawahei.demo.response.BackResult;
import com.wawahei.demo.response.ResponseCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: yanghailang
 * @create: 2020-12-21 09:49
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public BackResult exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        LOGGER.error("url:{}|errorMsg:{}", request.getRequestURI(), exception.getMessage(), exception);
        BackResult backResult = new BackResult(ResponseCodeEnum.INTERNAL_SERVER_ERROR);
        backResult.setData(exception.getMessage());
        return backResult;
    }
}