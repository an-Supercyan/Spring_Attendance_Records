package com.example.records.mapper;

import com.example.records.pojo.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface OperateLogMapper {
    /*    *//**
     * 将管理员操作存储到日志表中
     * @param operateLog
     */
    @Insert("insert into operate_log (operate_user,operate_time,class_name,method_name,method_params,return_value,cost_time)"
            + "values(#{operateUser},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog operateLog);
}
