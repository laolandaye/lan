package com.lan.javase._8thread.Ӧ��._1��ʱ��;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class _2TimerTask {

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
                    //��Ҫִ�еĲ���
                    for (int i = 0; i < 20000000; i++) {
                        System.out.print("");
                    }
                    System.out.println(new Date() + "��ʱִ��һ�Σ�����ִ��ʱ��");
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
                    //��Ҫִ�еĲ���
                    for (int i = 0; i < 20000000; i++) {
                        System.out.print("");
                    }
                    System.out.println(new Date() + "��ʱִ��һ��222222������ִ��ʱ��");
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
                    //��Ҫִ�еĲ���
                    for (int i = 0; i < 20000000; i++) {
                        System.out.print("");
                    }
                    System.out.println(new Date() + "��ʱִ��һ�Σ�������ִ��ʱ��");
                }catch(Exception e){
                    System.out.println(new Date()+":capacity statistics fail");
                }
            }
        };
        timer.schedule(timerTast, Calendar.getInstance().getTime(), 2000);
    }

}