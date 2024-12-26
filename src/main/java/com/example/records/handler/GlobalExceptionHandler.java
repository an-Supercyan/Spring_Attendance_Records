package com.example.records.handler;


import com.example.records.constant.MessageConstant;
import com.example.records.exception.BaseException;
import com.example.records.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

//定义全局异常处理器，所有后端出现的异常都会被全局异常处理器拦截，并且给前端返回一个状态码如404或500，或直接返回指定的erro状态码
// TODO 全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result<String> handleException(BaseException ex){
        log.error("异常信息：{}",ex.getMessage());
        return Result.error(ex.getMessage());
    }

    public Result<String> handleSameException(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}",ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + MessageConstant.ACCOUNT_EXIST;
            return Result.error(msg);
        }else {
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

}
