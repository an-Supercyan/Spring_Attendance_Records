package com.example.records.controller.access;

import com.example.records.constant.JwtClaimsConstant;
import com.example.records.pojo.dto.UserLoginDTO;
import com.example.records.pojo.dto.UserRegisterDTO;
import com.example.records.pojo.vo.UserLoginVO;
import com.example.records.properties.JwtProperties;
import com.example.records.result.Result;
import com.example.records.service.AccessService;
import com.example.records.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping
@Slf4j
@Api(tags = "登陆注册相关接口")
public class AccessController {
    @Autowired
    private AccessService accessService;
    @Autowired
    private JwtProperties jwtProperties;


    @PostMapping("/login")
    @ApiOperation("用户登录接口")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("获取用户登陆信息:{}", userLoginDTO);
        UserLoginVO userLoginVO = accessService.login(userLoginDTO);
        //生成jwt令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userLoginVO.getId());
        claims.put(JwtClaimsConstant.EMPLOYEE_NAME, userLoginVO.getEmployeeName());
        String token = JwtUtils
                .createJWT(jwtProperties.getAdminSecretKey(), jwtProperties.getAdminTtl(), claims);
        userLoginVO.setToken(token);
        return Result.success(userLoginVO);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册接口")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("获取用户注册信息:{}", userRegisterDTO);
        accessService.register(userRegisterDTO);
        return Result.success();
    }
}
