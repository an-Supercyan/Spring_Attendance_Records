package com.example.records.controller.admin;

import com.example.records.annotation.AdminLog;
import com.example.records.pojo.dto.UserDTO;
import com.example.records.pojo.dto.UserLoginDTO;
import com.example.records.pojo.vo.UserLoginVO;
import com.example.records.pojo.vo.UserVO;
import com.example.records.result.Result;
import com.example.records.service.UserService;
import com.example.records.utils.AliOssUtil;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/userInfo")
@Api(tags = "登陆用户信息管理")
@Slf4j
public class UserInfoController {
    @Autowired
    AliOssUtil aliOssUtil;
    @Autowired
    UserService userService;

    @GetMapping("/logout")
    @ApiOperation("用户注销登陆")
    public Result logout() {
        log.info("用户注销");
        return Result.success();
    }

    // TODO 修改当前用户登陆密码


    @GetMapping("/info")
    @ApiOperation("获取当前用户登陆信息")
    public Result<UserVO> getUserInfo(UserDTO userDTO) {
        log.info("获取当前用户Id:{}", userDTO.getId());
        UserVO userVO = userService.getUserInfoById(userDTO);
        return Result.success(userVO);
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @AdminLog
    @PostMapping("/upload")
    @ApiOperation("上传用户头像")
    public Result<String> upload(MultipartFile file) {
        log.info("获取到文件对象{}", file);
        try {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            //截取文件后缀文件类型
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //更新文件名(需要将UUID转化为字符串)
            String fileName = UUID.randomUUID().toString() + suffixName;
            //将文件传递到aliyunOSS中 并获取文件在OSS中的Path路径
            String filePath = aliOssUtil.upload(file.getBytes(), fileName);
            //更新对应用户的信息(目前只更新用户头像信息)
            UserDTO userDTO = new UserDTO();
            userDTO.setAvatar(filePath);
            userService.updateAvatar(userDTO);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("上传文件失败{}", e);
        }
        return Result.error("图片上传失败");
    }

    @ApiOperation("检验用户旧密码")
    @PostMapping("/checkOldPassword")
    public Result<String> checkPassword(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("获取用户旧密码信息:{}",userLoginDTO);
        userService.checkOldPassword(userLoginDTO);
        return Result.success();
    }

    @ApiOperation("用户重置密码")
    @PostMapping("/changePassword")
    public Result<String> updateUserInfo(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("获取更改的用户信息:{}",userLoginDTO);
        userService.changePassword(userLoginDTO);
        return Result.success();
    }
}
