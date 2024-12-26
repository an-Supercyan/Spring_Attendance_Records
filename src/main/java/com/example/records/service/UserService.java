package com.example.records.service;


import com.example.records.pojo.dto.UserDTO;
import com.example.records.pojo.dto.UserLoginDTO;
import com.example.records.pojo.dto.UserPageQueryDTO;

import com.example.records.pojo.vo.UserVO;
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
     * 更新用户头像信息
     * @param userDTO
     */
    void updateAvatar(UserDTO userDTO);

    /**
     * 根据id获取用户信息
     * @param userDTO
     * @return
     */
    UserVO getUserInfoById(UserDTO userDTO);

    /**
     * 更改用户账户状态
     * @param id
     */
    void chageStatus(Long id);

    /**
     * 模糊查询用户信息
     * @param userPageQueryDTO
     * @return
     */
    PageResult searchUser(UserPageQueryDTO userPageQueryDTO);

    /**
     * 更新用户信息
     * @param userDTO
     */
    void updateUserInfo(UserDTO userDTO);

    /**
     * 根据用户id删除用户信息
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 检查用户旧密码
     * @param userLoginDTO
     */
    void checkOldPassword(UserLoginDTO userLoginDTO);

    /**
     * 修改用户密码
     * @param userLoginDTO
     */
    void changePassword(UserLoginDTO userLoginDTO);
}

