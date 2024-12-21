package com.example.records.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {
    private String username;
    private String password;
    private String inviteCode;
}
