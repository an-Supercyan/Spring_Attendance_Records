package com.example.records.service.impl;

import com.example.records.context.BaseContext;
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
import org.springframework.transaction.annotation.Transactional;

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
        //若登陆成功则记录当前用户的currentID，用于后续的用户个人信息更新操作(通过拦截器实现)
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        return userLoginVO;
    }

    @Override
    @Transactional
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
        //根据获取到的权限信息设置用户表冗余字段
        if (authority.getIdentity() == 1){
            registerUser.setRole(1);
        }else {
            registerUser.setRole(0);
        }
        //设置用户状态默认启用
        registerUser.setStatus(1);
        userMapper.insert(registerUser);
        Long userId = registerUser.getId();
        log.info("用户注册成功，用户id为：{}", userId);

        //更新权限表
        authority.setUserId(userId);
        authorityMapper.update(authority);
    }
}
