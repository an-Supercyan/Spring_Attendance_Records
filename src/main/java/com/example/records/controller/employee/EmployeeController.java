package com.example.records.controller.employee;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/employee")
@RestController
@Slf4j
@Api(tags = "员工相关接口")
public class EmployeeController {
    // TODO 员工签到请求

    // TODO 员工请假请求

    // TODO 员工注销登陆

    // TODO 员工修改密码

    // TODO 员工更换头像

    // TODO 使用websocket实现与管理员管理页面的通信，员工签到或迟到时会提醒管理员
}
