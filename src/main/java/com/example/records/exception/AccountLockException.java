package com.example.records.exception;

public class AccountLockException extends BaseException{
    public AccountLockException(){

    }
    public AccountLockException(String msg){
        super(msg);
    }
}
