package com.example.records.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AttendanceDTO implements Serializable {
    private Long id;
    private String department;
    private LocalDateTime checkInTime;
    private Integer requiredHours;
    private Integer totalWorkHours;
    private Integer overtimeHours;

}
