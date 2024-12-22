package com.example.records.service;

import com.example.records.pojo.dto.AttendancePageQueryDTO;
import com.example.records.result.PageResult;

public interface AttendanceService {
    /**
     * 分页组合条件查询
     * @param attendancePageQueryDTO
     * @return
     */
    PageResult pageAttendance(AttendancePageQueryDTO attendancePageQueryDTO);
}
