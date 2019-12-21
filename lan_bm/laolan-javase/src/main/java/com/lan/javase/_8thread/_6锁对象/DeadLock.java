package com.lan.javase._8thread._6锁对象;

//死锁的问题：处理线程同步时容易出现。
//不同的线程分别占用对方需要的同步资源不放弃，都在等待对方放弃自己需要的同步资源，就形成了线程的死锁
//写代码时，要避免死锁！
public class DeadLock {
    public static void main(String[] args) {
        new Thread(new Runnable() { // 创建线程, 代表中国人
            @Override
            public void run() {
                synchronized ("刀叉") { // 中国人拿到了刀叉
                    System.out.println(Thread.currentThread().getName()
                            + ": 你不给我筷子, 我就不给你刀叉");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized ("筷子") {
                        System.out.println(Thread.currentThread()
                                .getName() + ": 给你刀叉");
                    }
                }
            }
        }, "中国人").start();
        new Thread(new Runnable() { // 美国人
            @Override
            public void run() {
                synchronized ("筷子") { // 美国人拿到了筷子
                    System.out.println(Thread.currentThread().getName()
                            + ": 你先给我刀叉, 我再给你筷子");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized ("刀叉") {
                        System.out.println(Thread.currentThread()
                                .getName() + ": 好吧, 把筷子给你.");
                    }
                }
            }
        }, "美国人").start();
    }
}

