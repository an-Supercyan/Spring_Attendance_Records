package com.example.records.aspect;

import com.alibaba.fastjson2.JSONObject;

import com.example.records.mapper.OperateLogMapper;
import com.example.records.pojo.entity.OperateLog;
import com.example.records.properties.JwtProperties;
import com.example.records.utils.JwtUtils;
import io.jsonwebtoken.Claims;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class AdminLogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Autowired
    private JwtProperties jwtProperties;

    @Around("@annotation(com.example.records.annotation.AdminLog)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String jwt = request.getHeader("Token");//在Request请求对象的请求头中获取jwt令牌
        Claims claims = JwtUtils.parseJWT(jwtProperties.getAdminSecretKey(),jwt);//通过解析jwt令牌获取claims对象
        Integer operateUser = (Integer) claims.get("userId");//通过claims对象获取jwt令牌中携带的登录员工信息
        String operateName = (String) claims.get("employeeName");

        LocalDateTime operateTime = LocalDateTime.now();
        //获取操作时间

        String className = joinPoint.getTarget().getClass().getName();
        //获取操作类名

        String methodName = joinPoint.getSignature().getName();
        //获取操作方法名

        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);
        //获取方法参数

        Long start = System.currentTimeMillis();
        //获取操作开始时间

        Object result = joinPoint.proceed();

        Long end = System.currentTimeMillis();
        //获取操作结束时间

        String returnValue = JSONObject.toJSONString(result);
        //获取返回值

        long costTime = end - start;

        // TODO Mapper层的日志插入
        OperateLog operateLog = new OperateLog(null,operateUser,operateName,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        return result;
    }
}
