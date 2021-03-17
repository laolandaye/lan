package com.atguigu.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ExecutorConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        int i = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
        System.out.println("获取到服务器的cpu内核:" + i + "个");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);//核心池大小
        executor.setMaxPoolSize(100);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("tsak-asyn");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        return executor;
    }

    @Bean("taskExecutor2")
    public Executor taskExecutor2() {
        int i = Runtime.getRuntime().availableProcessors();//获取到服务器的cpu内核
        System.out.println("获取到服务器的cpu内核:" + i + "个");

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(6);//核心池大小
        executor.setMaxPoolSize(100);//最大线程数
        executor.setQueueCapacity(1000);//队列程度
        executor.setKeepAliveSeconds(1000);//线程空闲时间
        executor.setThreadNamePrefix("池子2*******************");//线程前缀名称
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());//配置拒绝策略
        return executor;
    }
}
