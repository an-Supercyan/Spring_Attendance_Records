package com.example.records.pojo.vo;

import lombok.Data;


import java.io.Serializable;

@Data

public class UserLoginVO implements Serializable {
    private Long id;
    private String username;
    private String employeeName;
    private Integer role;
    private String token;
}
