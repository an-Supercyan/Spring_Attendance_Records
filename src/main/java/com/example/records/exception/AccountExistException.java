package com.example.records.exception;

public class AccountExistException extends BaseException{
    public AccountExistException() {
    }
    public AccountExistException(String msg) {
        super(msg);
    }
}
