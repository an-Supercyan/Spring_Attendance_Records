package com.example.records.pojo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    private String employeeName;
    private String department;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entryDate;
    private String avatar;
    private Integer status;
    private Integer role;
    private Integer gender;
}
