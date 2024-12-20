package com.example.records.service;

import com.example.records.pojo.dto.UserLoginDTO;
import com.example.records.pojo.dto.UserRegisterDTO;
import com.example.records.pojo.vo.UserLoginVO;



public interface AccessService {

    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    UserLoginVO login(UserLoginDTO userLoginDTO);

    /**
     * 用户注册
     *
     * @param userRegisterDTO
     * @return
     */
    void register(UserRegisterDTO userRegisterDTO);
}
