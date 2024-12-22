package com.example.records.service.impl;


import com.example.records.context.BaseContext;
import com.example.records.mapper.UserMapper;
import com.example.records.pojo.dto.UserDTO;
import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.pojo.entity.User;
import com.example.records.pojo.vo.UserVO;
import com.example.records.result.PageResult;
import com.example.records.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResult pageUser(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.searchUser(userPageQueryDTO);
        long total = page.getTotal();
        List<User> records = page.getResult();
        return new PageResult(total, records);
    }

    @Override
    public void updateAvatar(UserDTO userDTO) {
        User user = new User();
        Long userId = BaseContext.getCurrentId();
        System.out.println(userId);
        String avatar = userDTO.getAvatar();
        user.setId(userId);
        user.setAvatar(avatar);
        userMapper.update(user);
    }

    @Override
    public UserVO getUserInfoById(UserDTO userDTO) {
        User user = userMapper.getUserById(userDTO.getId());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public void chageStatus(Long id) {
        User user = userMapper.getUserById(id);
        if (user.getStatus() == 1) {
            user.setStatus(0);
        } else {
            user.setStatus(1);
        }
        userMapper.update(user);
    }

    @Override
    public PageResult searchUser(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.searchUser(userPageQueryDTO);
        long total = page.getTotal();
        List<User> records = page.getResult();
        return new PageResult(total, records);
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        try {
            userMapper.update(user);
        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUserById(id);
    }
}
