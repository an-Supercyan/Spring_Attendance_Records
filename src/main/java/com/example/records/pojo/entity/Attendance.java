package com.example.records.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
    private Long id;
    private Long userId;
    private String employeeName;
    private String department;
    private Integer requiredHours;
    private LocalDateTime checkInTime;
    private Integer totalWorkHours;
    private Integer overtimeHours;
    private Integer absencesCount;
    private LocalDateTime entryDate;
    private String noCheckInReason;
}
