package com.example.records.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String employeeName;
    private String department;
    private LocalDateTime entryDate;
    private String avatar;
    private Integer status;
}
