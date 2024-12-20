package com.example.records.controller.users;

import com.example.records.pojo.dto.UserDTO;
import com.example.records.result.Result;
import com.example.records.service.UserService;
import com.example.records.utils.AliOssUtil;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public Result getUserInfo() {
        log.info("用户注销");
        return Result.success();
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("上传菜品图片")
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
            UserDTO userDTO = new UserDTO();
            userDTO.setAvatar(filePath);
            userService.update(userDTO);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("上传文件失败{}", e);
        }
        return Result.error("图片上传失败");
    }
}
