package com.lan.javase._8thread._2创建线程;

/*
 * 创建一个子线程，完成1-20之间自然数的输出。同样地，主线程执行同样的操作
 * 创建多线程的第一种方式：继承java.lang.Thread类
 *
 * 创建多线程的方式二：通过实现的方式
 *
 * 对比一下继承的方式 vs 实现的方式
 * 1.联系：public class Thread implements Runnable
 * 2.哪个方式好？实现的方式优于继承的方式
 *    why?  ① 避免了java单继承的局限性
 *    		② 如果多个线程要操作同一份资源(或数据)，更适合使用实现的方式
 */
//1.创建一个 实现 Runnable
class MyRunnable implements Runnable{
	//2.重写Thread的run方法.方法内实现此子线程要完成的功能
	public void run(){
		Thread.currentThread().setName("傻逼");
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

public class TestRunnable {

	public static void main(String[] args) {
		//3.创建一个子类对象
		MyRunnable p = new MyRunnable();
		//4.将此对象作为形参传递给Thread类的构造器中，创建Thread类的对象，此对象即为一个线程
		Thread t1 = new Thread(p);
		//5.调用start()方法：启动线程并执行run()
		t1.start();//启动线程；执行Thread对象生成时构造器形参的对象的run()方法。

		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}	
}
