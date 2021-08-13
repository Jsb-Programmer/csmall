package com.cskaoyan.exception;

import com.cskaoyan.bean.BaseRespVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionControllerAdvice
 * @Description
 * @Author 王昀昊
 * @Date 2021/8/13 14:25
 * @Version 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public BaseRespVo brandCreateExceptionHandler(){
        return BaseRespVo.fail("系统正在升级");
    }
}
