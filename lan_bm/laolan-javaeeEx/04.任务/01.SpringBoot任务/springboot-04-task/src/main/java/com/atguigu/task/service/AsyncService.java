package com.atguigu.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

@Service
public class AsyncService {

    //告诉Spring这是一个异步方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中..." + new Date());
    }

    @Async(value = "taskExecutor")
    public void helloExecutor(int i){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + " 处理数据中..." + new Date() + "  " + Thread.currentThread().getName());
    }

    @Async(value = "taskExecutor")
    public void testCountDownLatch(CountDownLatch countDownLatch, int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + " 处理数据中..." + new Date() + "  " + Thread.currentThread().getName());
        countDownLatch.countDown();
    }

    @Autowired
    private ManyService manyService;

    @Async(value = "taskExecutor")
    public void testCountDownLatch(int j) {
        System.out.println(j + "儿子线程:开始执行多线程任务:::"+System.currentTimeMillis());
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            manyService.testCountDownLatch(countDownLatch, j, i);
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(j + "儿子线程已近执行完了:::"+Thread.currentThread().getName());
    }
}
