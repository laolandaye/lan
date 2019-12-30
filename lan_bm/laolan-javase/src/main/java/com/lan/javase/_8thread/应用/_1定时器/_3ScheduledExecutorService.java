package com.lan.javase._8thread.应用._1定时器;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 *  原文链接：https://blog.csdn.net/lzl9421na/article/details/87881960
 */
public class _3ScheduledExecutorService {
    public static void main(String[] args) {
        // 手动创建线程池, 创建线程池的方式多种，自己选择
        ScheduledExecutorService executorService  = new ScheduledThreadPoolExecutor(10);

        System.out.println("          x = " + System.currentTimeMillis());
        executorService.scheduleWithFixedDelay(new MyRunable(), 1, 2, TimeUnit.SECONDS);
        System.out.println("          y = " + System.currentTimeMillis());
    }

    static class MyRunable implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("     begin = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("     end = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}