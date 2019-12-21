package com.lan.javase._8thread._10�̳߳�;

import java.util.concurrent.*;

public class TestScheduledThreadPool {
    public static void main(String[] args) throws InterruptedException, ExecutionException{
        // �������̳߳�:�̶��������̳߳أ�����ڵ�һ���̶��������̳߳� ǿ���� �ٿ���ִ����ʱ���񣬢�Ҳ����ִ��
        // �з���ֵ������
        // scheduledThreadPool.submit(); ִ�д��з���ֵ������
        // scheduledThreadPool.schedule() ����ִ����ʱ����.
        // �̶��������̳߳أ�����ִ����ʱ����Ҳ����ִ�д��з���ֵ������
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        FutureTask<String> ft = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("hello");
                return Thread.currentThread().getName();
            }
        });
        scheduledThreadPool.submit(ft);

        // ͨ��FutureTask�����÷���ֵ.
        String result = ft.get();
        System.out.println("result : " + result);

        // ִ����ʱ����
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : bobm!");
            }
        }, 3L, TimeUnit.SECONDS);

    }
}
