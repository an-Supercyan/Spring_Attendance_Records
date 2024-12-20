package com.example.records.mapper;

import com.example.records.pojo.entity.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AuthorityMapper {


    Authority getAuthorityByInviteCode(String inviteCode);

    @Update("update authority set user_id = #{userId} where id = #{id}")
    void update(Authority authority);
}
