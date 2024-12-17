package com.example.records.controller;


import com.example.records.pojo.dto.EmployeeDTO;
import com.example.records.pojo.vo.EmployeeVO;
import com.example.records.result.Result;
import com.example.records.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin/employee")
@Api(tags = "员工信息管理相关接口")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<EmployeeVO> login(@RequestBody EmployeeDTO employeeDTO){
        log.info("获取员工登录信息:{}",employeeDTO);
        return Result.success();
    }

    @ApiOperation("用户退出")
    @GetMapping("/logout")
    public Result<String> logout(){
        log.info("用户退出");
        return Result.success();
    }
}
