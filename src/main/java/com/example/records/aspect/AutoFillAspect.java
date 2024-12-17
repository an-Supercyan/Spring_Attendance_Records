package com.example.records.aspect;



import com.example.records.annotation.AutoFill;
import com.example.records.constant.AutoFillConstant;
import com.example.records.context.BaseContext;
import com.example.records.enumeration.OperationType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;


@Component
@Aspect
public class AutoFillAspect {

    @Pointcut("execution(* com.example.records.service.impl.*.*(..))&&@annotation(com.example.records.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        //获取方法签名的注解值
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType value = annotation.value();

        //获取方法签名的第一个参数
        Object[] args = joinPoint.getArgs();
        //判断参数是否存在
        if (args == null || args.length == 0) {
            return;
        }

        //存在则获取该对象
        Object entity = args[0];
        LocalDateTime now = LocalDateTime.now();
        //在解析jwt令牌时进行currentId的设置
        Long currentId = BaseContext.getCurrentId();

        //根据注解的Value值进行不同Dao操作的公共字段填充
        if (value == OperationType.INSERT) {
            try {
                //通过反射获取需要调用的Setter方法
                Method setCreatTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setCreatTime.invoke(entity, now);
                setUpdateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentId);
                setUpdateUser.invoke(entity, currentId);


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (value == OperationType.UPDATE) {
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentId);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

}
