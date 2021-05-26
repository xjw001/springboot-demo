package com.xjw.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobSchedule {

   // @Scheduled(cron = "0/5 * * * * ?")
    public void queryStatus(){
        System.out.println("执行定时任务");
    }
}
