package com.lan.javase._8thread._10�̳߳�;

//1.����һ���̳���Thread������
class MyThread extends Thread{
    //2.��дThread��run����.������ʵ�ִ����߳�Ҫ��ɵĹ���
    @Override
    public void run(){
        Thread.currentThread().setName("ɵ��");
        for (int i = 1; i <= 20; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
