package com.example.records.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer status;
    private String avatar;
    private String employeeName;
    private String department ;
    private Long inviteCodeId;
    private LocalDateTime entryDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
