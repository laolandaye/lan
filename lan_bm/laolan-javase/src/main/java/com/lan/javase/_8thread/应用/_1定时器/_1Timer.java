package com.lan.javase._8thread.应用._1定时器;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class _1Timer {

    public static void main(String args[]){
        testDingshi();
//        testDingshi2();
    }

    private static void testDingshi() {
        final Timer timer=new Timer();
        TimerTask timerTast=new TimerTask() {
            @Override
            public void run() {
                try{
                    //需要执行的操作
                    for (int i = 0; i < 20000000; i++) {
                        System.out.print("");
                    }
                    System.out.println(new Date() + "定时执行一次，包括执行时间");
                }catch(Exception e){
                    System.out.println(new Date()+":capacity statistics fail");
                }
            }
        };
        timer.scheduleAtFixedRate(timerTast, Calendar.getInstance().getTime(), 2000);

        TimerTask timerTast2=new TimerTask() {
            @Override
            public void run() {
                try{
                    //需要执行的操作
                    for (int i = 0; i < 20000000; i++) {
                        System.out.print("");
                    }
                    System.out.println(new Date() + "定时执行一次222222，包括执行时间");
                }catch(Exception e){
                    System.out.println(new Date()+":capacity statistics fail");
                }
            }
        };
        timer.scheduleAtFixedRate(timerTast2, Calendar.getInstance().getTime(), 2000);
    }


    private static void testDingshi2() {
        final Timer timer=new Timer();
        TimerTask timerTast=new TimerTask() {
            @Override
            public void run() {
                try{
                    //需要执行的操作
                    for (int i = 0; i < 20000000; i++) {
                        System.out.print("");
                    }
                    System.out.println(new Date() + "定时执行一次，不包括执行时间");
                }catch(Exception e){
                    System.out.println(new Date()+":capacity statistics fail");
                }
            }
        };
        timer.schedule(timerTast, Calendar.getInstance().getTime(), 2000);
    }

}