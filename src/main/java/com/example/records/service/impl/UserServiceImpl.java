package com.example.records.service.impl;


import com.example.records.context.BaseContext;
import com.example.records.mapper.UserMapper;
import com.example.records.pojo.dto.UserDTO;
import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.pojo.entity.User;
import com.example.records.result.PageResult;
import com.example.records.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void update(UserDTO userDTO) {
        User user = new User();
        Long userId = BaseContext.getCurrentId();
        String avatar = userDTO.getAvatar();
        user.setId(userId);
        user.setAvatar(avatar);
        userMapper.update(user);
    }
}
