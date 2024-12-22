package com.example.records.mapper;

import com.example.records.pojo.dto.AttendancePageQueryDTO;
import com.example.records.pojo.entity.Attendance;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {
    Page<Attendance> pageAttendance(AttendancePageQueryDTO attendancePageQueryDTO);
}