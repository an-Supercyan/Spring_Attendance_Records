package com.example.records.controller.admin;


import com.example.records.pojo.dto.AttendanceDTO;
import com.example.records.pojo.vo.AttendanceVO;
import com.example.records.result.Result;

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
    UserService userService;

    @ApiOperation("新增员工考勤信息")
    @PostMapping("/add")
    public Result<AttendanceVO> addAttendance(@RequestBody AttendanceDTO attendanceDTO){
        log.info("获取员工登录信息:{}",attendanceDTO);
        return Result.success();
    }



}
