package com.lan.javase._8thread._10线程池;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

public class TestCachedThreadPool {
    public static void main(String[] args) {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("Mdemo-pool-%d").build();
        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(5,200,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1024),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        // 缓存线程池，无上限
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        cachedThreadPool.shutdown();
    }
}
