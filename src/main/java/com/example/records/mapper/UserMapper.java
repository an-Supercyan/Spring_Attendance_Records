package com.example.records.mapper;

import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.pojo.dto.UserRegisterDTO;
import com.example.records.pojo.entity.User;
import com.example.records.result.PageResult;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getUserByUsername(String username);

    /**
     * 分页组合条件查询用户
     * @param userPageQueryDTO
     * @return
     */
    Page<User> searchUser(UserPageQueryDTO userPageQueryDTO);

    /**
     * 新增用户
     * @param user
     */
    void insert(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getUserById(Long id);

    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where id = #{id}")
    void deleteUserById(Long id);
}
