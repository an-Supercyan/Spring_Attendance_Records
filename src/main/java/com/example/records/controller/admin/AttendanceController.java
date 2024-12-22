package com.example.records.controller.admin;


import com.example.records.pojo.dto.AttendanceDTO;
import com.example.records.pojo.dto.AttendancePageQueryDTO;
import com.example.records.pojo.vo.AttendanceVO;
import com.example.records.result.PageResult;
import com.example.records.result.Result;

import com.example.records.service.AttendanceService;
import com.example.records.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/attendance")
@Api(tags = "员工考勤信息管理相关接口")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @ApiOperation("分页查询员工考勤信息")
    @GetMapping("/search")
    public Result<PageResult> pageAttendance(AttendancePageQueryDTO attendancePageQueryDTO) {
        log.info("分页查询员工考勤信息{}", attendancePageQueryDTO);
        PageResult pageResult = attendanceService.pageAttendance(attendancePageQueryDTO);
        return Result.success(pageResult);
    }




}
