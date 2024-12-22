package com.example.records.pojo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class UserPageQueryDTO implements Serializable {
    private String employeeName;
    private String department;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entryDateBegin;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entryDateEnd;
    private Integer page;
    private Integer pageSize;
}
