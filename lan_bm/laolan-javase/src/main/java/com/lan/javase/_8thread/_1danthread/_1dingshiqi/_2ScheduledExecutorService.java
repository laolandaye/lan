package _8thread._1danthread._1dingshiqi;

import _8thread.tool.CalendarUtil;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _2ScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        System.out.println("          x = " + System.currentTimeMillis());
        executorService.scheduleWithFixedDelay(new MyRunable(), 1, 2, TimeUnit.SECONDS);
        System.out.println("          y = " + System.currentTimeMillis());
    }

    static class MyRunable implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("     begin = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(4);
                System.out.println("     end = " + System.currentTimeMillis() + ", name: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}