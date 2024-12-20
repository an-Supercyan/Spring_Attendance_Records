package com.example.records.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserPageQueryDTO implements Serializable {
    private String employeeName;
    private String department;
    private LocalDateTime beginDateScope;
    private LocalDateTime endDateScope;
    private Integer page;
    private Integer pageSize;
}
