package com.atguigu.task.controller;

import com.atguigu.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        return "hello";
    }

    @GetMapping("/helloExecutor")
    public String helloExecutor(){
        System.out.println("开始执行多线程任务:::"+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            asyncService.helloExecutor(i);
        }
        System.out.println("主线程继续执行:::"+Thread.currentThread().getName());
        return "helloExecutor";
    }

    @GetMapping("/testCountDownLatch")
    public String testCountDownLatch(){
        System.out.println("开始执行多线程任务:::"+System.currentTimeMillis());
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            asyncService.testCountDownLatch(countDownLatch, i);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子线程已近执行完了，主线程继续执行:::"+Thread.currentThread().getName());
        return "testCountDownLatch";
    }

    // 多进程同步跑
    @GetMapping("/testManyCountDownLatch")
    public String testManyCountDownLatch(){
        System.out.println("NO1主线程: 开始执行多线程任务:::"+System.currentTimeMillis());
        for (int j = 0; j < 5; j++) {
            asyncService.testCountDownLatch(j);
        }
        return "testManyCountDownLatch";
    }


}
