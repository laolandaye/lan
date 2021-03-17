package com.atguigu.task.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Service
public class ManyService {

    @Async(value = "taskExecutor2")
    public void testCountDownLatch(CountDownLatch countDownLatch,int j, int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[" + j + " , " + i + "] 处理数据中..." + new Date() + "  " + Thread.currentThread().getName());
        countDownLatch.countDown();
    }

}
