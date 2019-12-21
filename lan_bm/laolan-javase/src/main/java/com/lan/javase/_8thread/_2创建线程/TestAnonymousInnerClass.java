package com.lan.javase._8thread._2创建线程;

public class TestAnonymousInnerClass {

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        }).start();

        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
