package com.kun.xining7.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * cron 表达式生成网站
 * http://www.bejson.com/othertools/cron/
 */

@Component
public class ScheduledTest {

    //每10秒执行一次
    @Scheduled(cron = "0/10 * * * * ?")
    public void a() {
        System.out.println("定时任务每10秒执行一次");
    }

    //每月1，8,19号0点执行
    @Scheduled(cron = "0 0 0 1,8,19 * ?")
    public void b() {
        System.out.println("每月1，8,19号0点执行");
    }

}
