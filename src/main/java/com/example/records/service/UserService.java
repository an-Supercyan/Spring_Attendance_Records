package com.example.records.service;


import com.example.records.pojo.dto.UserDTO;
import com.example.records.pojo.dto.UserPageQueryDTO;

import com.example.records.pojo.entity.User;
import com.example.records.result.PageResult;

public interface UserService {


    /**
     * 获取用户信息
     *
     * @param userPageQueryDTO
     * @return
     */
    PageResult pageUser(UserPageQueryDTO userPageQueryDTO);

    /**
     * 更新用户信息
     * @param userDTO
     */
    void update(UserDTO userDTO);
}

