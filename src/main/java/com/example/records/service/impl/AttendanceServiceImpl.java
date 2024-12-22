package com.example.records.service.impl;


import com.example.records.mapper.AttendanceMapper;
import com.example.records.pojo.dto.AttendancePageQueryDTO;
import com.example.records.pojo.entity.Attendance;
import com.example.records.result.PageResult;
import com.example.records.service.AttendanceService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;

    @Override
    public PageResult pageAttendance(AttendancePageQueryDTO attendancePageQueryDTO) {
        PageHelper.startPage(attendancePageQueryDTO.getPage(), attendancePageQueryDTO.getPageSize());
        if(attendancePageQueryDTO.getHasAbsence()){
            //获取当日零点时间
            attendancePageQueryDTO.setCheckInTime(LocalDate.now().atStartOfDay());
        }
        Page<Attendance> page = attendanceMapper.pageAttendance(attendancePageQueryDTO);
        long total = page.getTotal();
        List<Attendance> records = page.getResult();
        return new PageResult(total, records);
    }
}
