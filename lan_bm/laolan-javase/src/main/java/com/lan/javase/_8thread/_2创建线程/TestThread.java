package com.lan.javase._8thread._2创建线程;

/*
 * 创建一个子线程，完成1-20之间自然数的输出。同样地，主线程执行同样的操作
 * 创建多线程的第一种方式：继承java.lang.Thread类
 */

//1.创建一个继承与Thread的子类
class MyThread extends Thread{

	public MyThread() {
	}

	//2.重写Thread的run方法.方法内实现此子线程要完成的功能
	@Override
	public void run(){
		Thread.currentThread().setName("傻逼");
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

public class TestThread {

	public static void main(String[] args) {
		//3.创建一个子类对象
		MyThread st = new MyThread();
		//4.调动现成的Start（），启动此线程；调用相应的run方法
		//一个线程只能够执行一次start()
		//不能通过Thread实现类对象的run()去启动一个线程
		st.start();
		
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}	
}
