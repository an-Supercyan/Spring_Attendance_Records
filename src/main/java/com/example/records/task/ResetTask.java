package com.example.records.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Slf4j
//@Component
public class ResetTask {
    // TODO 每天凌晨三点重置签到状态为该日的凌晨零点


    @Scheduled(cron = "* * 3 * * ? *")
    public void resetAttendance(){

    }




    // TODO 结算当天缺勤人数，请假不算入缺勤次数
}
