package com.example.records.mapper;

import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.pojo.dto.UserRegisterDTO;
import com.example.records.pojo.entity.User;
import com.example.records.result.PageResult;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    Page<User> searchUser(UserPageQueryDTO userPageQueryDTO);

    void insert(User user);

    void update(User user);
}
