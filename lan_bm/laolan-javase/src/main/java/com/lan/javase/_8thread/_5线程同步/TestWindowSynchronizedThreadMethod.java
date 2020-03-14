package com.lan.javase._8thread._5线程同步;

//模拟火车站售票窗口，开启三个窗口售票，总票数为100张
//存在线程的安全问题--->使用同步代码块处理。
class WindowSynchronizedThreadMethod extends Thread {
	static int ticket = 100;
	static Object obj = new Object();

	public void run() {
		while (true) {
			// synchronized (this) {//在本问题中，this表示：w1,w2,w3
			synchronized (obj) {
				 show();
			}
		}
	}

	public synchronized void show() {// this充当的锁
		if (ticket > 0) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "售票，票号为："
					+ ticket--);
		}
	}
}

public class TestWindowSynchronizedThreadMethod {
	public static void main(String[] args) {
		WindowSynchronizedThreadCode w1 = new WindowSynchronizedThreadCode();
		WindowSynchronizedThreadCode w2 = new WindowSynchronizedThreadCode();
		WindowSynchronizedThreadCode w3 = new WindowSynchronizedThreadCode();

		w1.setName("窗口1");
		w2.setName("窗口2");
		w3.setName("窗口3");

		w1.start();
		w2.start();
		w3.start();

	}

}
