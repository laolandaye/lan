package com.lan.javase._8thread.Ӧ��._1��ʱ��;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.*;

/**
 *  ԭ�����ӣ�https://blog.csdn.net/lzl9421na/article/details/87881960
 */
public class _3ScheduledExecutorService {
    public static void main(String[] args) {
        // �ֶ������̳߳�, �����̳߳صķ�ʽ���֣��Լ�ѡ��
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