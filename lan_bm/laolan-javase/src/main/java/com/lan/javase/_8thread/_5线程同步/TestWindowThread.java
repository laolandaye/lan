package com.lan.javase._8thread._5线程同步;
/**
 * 使用实现Runnable接口的方式，售票
 * 模拟火车站售票，开启3个窗口，总票数100张
 * 存在线程的安全问题
 */

class WindowThread extends Thread {
	static int ticket = 100;

	public void run() {
		while (true) {
			if (ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "售票，票号为："
						+ ticket--);
			} else {
				break;
			}
		}
	}
}

public class TestWindowThread {
	public static void main(String[] args) {
		WindowSynchronizedThreadMethod w1 = new WindowSynchronizedThreadMethod();
		WindowSynchronizedThreadMethod w2 = new WindowSynchronizedThreadMethod();
		WindowSynchronizedThreadMethod w3 = new WindowSynchronizedThreadMethod();
		
		String aString = "00";
		
		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");
		
		w1.start();
		w2.start();
		w3.start();
			
	}

}
