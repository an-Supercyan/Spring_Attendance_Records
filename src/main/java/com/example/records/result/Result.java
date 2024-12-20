package com.example.records.result;

import lombok.Data;

import java.io.Serializable;

//RESTful风格统一返回对象Result
//必须实现Serializable接口，否则无法序列化。且需要添加@Data注解保证getter和setter
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }


    public static <T> Result<T> success(T object){
        Result<T> result = new Result<T>();
        result.code = 1;
        result.data = object;
        return result;
    }


    public static <T> Result<T> error(String message){
        Result<T> result = new Result<T>();
        result.code = 0;
        result.msg = message;
        return result;
    }
}
