package com.lan.javase._8thread._5�߳�ͬ��;
/**
 * ģ���վ��Ʊ������3�����ڣ���Ʊ��100��
 * �����̵߳İ�ȫ����
 */

class Window extends Thread {
	static int ticket = 100;

	public void run() {
		while (true) {
			if (ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "��Ʊ��Ʊ��Ϊ��"
						+ ticket--);
			} else {
				break;
			}
		}
	}
}

public class TestWindow {
	public static void main(String[] args) {
		Window w1 = new Window();
		Window w2 = new Window();
		Window w3 = new Window();
		
		String aString = "00";
		
		w1.setName("����1");
		w2.setName("����2");
		w3.setName("����3");
		
		w1.start();
		w2.start();
		w3.start();
			
	}

}
