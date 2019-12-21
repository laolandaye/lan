package com.lan.javase._8thread._10线程池;

//1.创建一个继承与Thread的子类
class MyThread extends Thread{
    //2.重写Thread的run方法.方法内实现此子线程要完成的功能
    @Override
    public void run(){
        Thread.currentThread().setName("傻逼");
        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
