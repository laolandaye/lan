package com.lan.javase._8thread._2�����߳�;

/*
 * ����һ�����̣߳����1-20֮����Ȼ���������ͬ���أ����߳�ִ��ͬ���Ĳ���
 * �������̵߳ĵ�һ�ַ�ʽ���̳�java.lang.Thread��
 */

//1.����һ���̳���Thread������
class MyThread extends Thread{
	//2.��дThread��run����.������ʵ�ִ����߳�Ҫ��ɵĹ���
	public void run(){
		Thread.currentThread().setName("ɵ��");
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

public class TestThread {

	public static void main(String[] args) {
		//3.����һ���������
		MyThread st = new MyThread();
		//4.�����ֳɵ�Start�������������̣߳�������Ӧ��run����
		//һ���߳�ֻ�ܹ�ִ��һ��start()
		//����ͨ��Threadʵ��������run()ȥ����һ���߳�
		st.start();
		
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}	
}
