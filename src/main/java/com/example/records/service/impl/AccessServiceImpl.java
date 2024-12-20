package com.example.records.service.impl;

import com.example.records.mapper.AuthorityMapper;
import com.example.records.mapper.UserMapper;
import com.example.records.pojo.dto.UserLoginDTO;
import com.example.records.pojo.dto.UserRegisterDTO;
import com.example.records.pojo.entity.Authority;
import com.example.records.pojo.entity.User;
import com.example.records.pojo.vo.UserLoginVO;
import com.example.records.service.AccessService;
import org.apache.commons.codec.digest.DigestUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl implements AccessService {
    private static final Logger log = LoggerFactory.getLogger(AccessServiceImpl.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthorityMapper authorityMapper;

    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        if (!user.getPassword().equals(DigestUtils.md5Hex(password))) {
            throw new RuntimeException("密码错误");
        }

        if (user.getStatus() == 0) {
            throw new RuntimeException("用户已被禁用");
        }
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        return userLoginVO;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        String inviteCode = userRegisterDTO.getInviteCode();
        String username = userRegisterDTO.getUsername();
        Authority authority = authorityMapper.getAuthorityByInviteCode(inviteCode);
        User user = userMapper.getUserByUsername(username);
        if (authority == null) {
            throw new RuntimeException("用户邀请码错误");
        }

        if (user != null) {
            throw new RuntimeException("用户已存在");
        }

        //新增用户到数据库
        User registerUser = new User();
        BeanUtils.copyProperties(userRegisterDTO, registerUser);
        registerUser.setInviteCodeId(authority.getId());
        registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));
        //根据获取到的权限信息设置用户表冗余字段身份
        if (authority.getIdentity() == 1){
            registerUser.setStatus(1);
        }else {
            registerUser.setStatus(0);
        }

        userMapper.insert(registerUser);
        Long userId = registerUser.getId();
        log.info("用户注册成功，用户id为：{}", userId);

        //更新权限表
        authority.setUserId(userId);
        authorityMapper.update(authority);
    }
}
