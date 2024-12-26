package com.example.records.service;

import com.example.records.pojo.dto.AttendanceDTO;
import com.example.records.pojo.dto.AttendancePageQueryDTO;
import com.example.records.result.PageResult;

public interface AttendanceService {
    /**
     * 分页组合条件查询
     * @param attendancePageQueryDTO
     * @return
     */
    PageResult pageAttendance(AttendancePageQueryDTO attendancePageQueryDTO);

    /**
     * 编辑员工考勤信息
     * @param attendanceDTO
     */
    void updateAttendance(AttendanceDTO attendanceDTO);

    /**
     * 删除员工考勤信息
     * @param id
     */
    void deleteAttendance(Long id);
}
