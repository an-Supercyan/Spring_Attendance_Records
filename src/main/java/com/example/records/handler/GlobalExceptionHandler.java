package com.example.records.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//定义全局异常处理器，所有后端出现的异常都会被全局异常处理器拦截，并且给前端返回一个状态码如404或500，或直接返回指定的erro状态码
// TODO 全局异常处理器
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

}
