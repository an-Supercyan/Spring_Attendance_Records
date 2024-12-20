package com.example.records.controller.admin;

import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.result.PageResult;
import com.example.records.result.Result;
import com.example.records.service.UserService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/users")
@Api(tags = "用户管理接口")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;


    @ApiOperation("获取用户信息")
    @GetMapping("/page")
    public Result<PageResult> pageUser(UserPageQueryDTO userPageQueryDTO) {
        log.info("获取用户分页查询信息{}", userPageQueryDTO);
        PageResult pageResult = userService.pageUser(userPageQueryDTO);
        return Result.success(pageResult);
    }
}
