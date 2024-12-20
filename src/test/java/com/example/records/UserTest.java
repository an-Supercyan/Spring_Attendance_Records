package com.example.records;

import com.example.records.mapper.UserMapper;
import com.example.records.pojo.dto.UserPageQueryDTO;
import com.example.records.result.PageResult;
import com.example.records.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
class UserTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        log.info("test");
    }

    @Test
    void pageUser(){
        log.info("pageUser");
        UserPageQueryDTO userPageQueryDTO = new UserPageQueryDTO();
        userPageQueryDTO.setPage(1);
        userPageQueryDTO.setPageSize(10);
        userPageQueryDTO.setBeginDateScope(LocalDateTime.now().minusDays(7));
        userPageQueryDTO.setEndDateScope(LocalDateTime.now());
        PageResult pageResult = userService.pageUser(userPageQueryDTO);
        log.info("pageResult{}",pageResult);
    }

}
