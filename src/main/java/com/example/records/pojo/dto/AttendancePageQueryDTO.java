package com.example.records.pojo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AttendancePageQueryDTO implements Serializable {
    private Boolean hasAbsence;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime checkInTime;
    private String employeeName;
    private String department;
    private Integer page;
    private Integer pageSize;
}
