package com.example.records.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {
    private String username;
    private String employeeName;
    private Integer gender;
    private String avatar;
    private Integer role;
    private String department;
}
