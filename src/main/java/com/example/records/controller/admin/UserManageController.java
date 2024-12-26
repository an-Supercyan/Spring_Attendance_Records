package com.example.records.controller.admin;

import com.example.records.annotation.AdminLog;
import com.example.records.pojo.dto.UserDTO;
import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.result.PageResult;
import com.example.records.result.Result;
import com.example.records.service.UserService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/users")
@Api(tags = "用户管理接口")
@Slf4j
public class UserManageController {
    @Autowired
    UserService userService;

    @ApiOperation("获取用户信息")
    @GetMapping("/page")
    public Result<PageResult> pageUser(UserPageQueryDTO userPageQueryDTO) {
        log.info("获取用户分页查询信息{}", userPageQueryDTO);
        PageResult pageResult = userService.pageUser(userPageQueryDTO);
        return Result.success(pageResult);
    }

    @ApiOperation("搜索用户信息")
    @GetMapping("/search")
    public Result<PageResult> searchUser(UserPageQueryDTO userPageQueryDTO){
        log.info("获取搜索{}", userPageQueryDTO);
        PageResult pageResult = userService.searchUser(userPageQueryDTO);
        return Result.success(pageResult);
    }

    @AdminLog
    @ApiOperation("更改用户账户状态")
    @PostMapping("/status/{id}")
    public Result<String> updateUserStatus(@PathVariable Long id){
        log.info("获取更改的用户账户id:{}",id);
        userService.chageStatus(id);
        return Result.success();
    }

    @AdminLog
    @ApiOperation("更新用户信息")
    @PutMapping("/update")
    public Result<String> updateUser(@RequestBody UserDTO userDTO){
        log.info("获取更改的用户信息:{}",userDTO);
        userService.updateUserInfo(userDTO);
        return Result.success();
    }

    @AdminLog
    @ApiOperation("删除用户信息")
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable Long id){
        log.info("获取删除的用户信息:{}",id);
        userService.deleteUser(id);
        return Result.success();
    }
}
