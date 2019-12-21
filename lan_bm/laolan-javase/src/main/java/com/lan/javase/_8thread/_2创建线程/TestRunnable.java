package com.lan.javase._8thread._2�����߳�;

/*
 * ����һ�����̣߳����1-20֮����Ȼ���������ͬ���أ����߳�ִ��ͬ���Ĳ���
 * �������̵߳ĵ�һ�ַ�ʽ���̳�java.lang.Thread��
 *
 * �������̵߳ķ�ʽ����ͨ��ʵ�ֵķ�ʽ
 *
 * �Ա�һ�¼̳еķ�ʽ vs ʵ�ֵķ�ʽ
 * 1.��ϵ��public class Thread implements Runnable
 * 2.�ĸ���ʽ�ã�ʵ�ֵķ�ʽ���ڼ̳еķ�ʽ
 *    why?  �� ������java���̳еľ�����
 *    		�� �������߳�Ҫ����ͬһ����Դ(������)�����ʺ�ʹ��ʵ�ֵķ�ʽ
 */
//1.����һ�� ʵ�� Runnable
class MyRunnable implements Runnable{
	//2.��дThread��run����.������ʵ�ִ����߳�Ҫ��ɵĹ���
	public void run(){
		Thread.currentThread().setName("ɵ��");
		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}
}

public class TestRunnable {

	public static void main(String[] args) {
		//3.����һ���������
		MyRunnable p = new MyRunnable();
		//4.���˶�����Ϊ�βδ��ݸ�Thread��Ĺ������У�����Thread��Ķ��󣬴˶���Ϊһ���߳�
		Thread t1 = new Thread(p);
		//5.����start()�����������̲߳�ִ��run()
		t1.start();//�����̣߳�ִ��Thread��������ʱ�������βεĶ����run()������

		for (int i = 1; i <= 20; i++) {
			System.out.println(Thread.currentThread().getName()+":"+i);
		}
	}	
}
