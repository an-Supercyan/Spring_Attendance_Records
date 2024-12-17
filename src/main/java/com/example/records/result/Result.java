package com.example.records.result;

import java.io.Serializable;

//RESTful风格统一返回对象Result
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


    public static <T> Result<T> erro(String message){
        Result<T> result = new Result<T>();
        result.code = 0;
        result.msg = message;
        return result;
    }
}
