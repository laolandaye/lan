package com.lan.javase._8thread._6������;

//���������⣺�����߳�ͬ��ʱ���׳��֡�
//��ͬ���̷ֱ߳�ռ�öԷ���Ҫ��ͬ����Դ�����������ڵȴ��Է������Լ���Ҫ��ͬ����Դ�����γ����̵߳�����
//д����ʱ��Ҫ����������
public class DeadLock {
    public static void main(String[] args) {
        new Thread(new Runnable() { // �����߳�, �����й���
            @Override
            public void run() {
                synchronized ("����") { // �й����õ��˵���
                    System.out.println(Thread.currentThread().getName()
                            + ": �㲻���ҿ���, �ҾͲ����㵶��");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized ("����") {
                        System.out.println(Thread.currentThread()
                                .getName() + ": ���㵶��");
                    }
                }
            }
        }, "�й���").start();
        new Thread(new Runnable() { // ������
            @Override
            public void run() {
                synchronized ("����") { // �������õ��˿���
                    System.out.println(Thread.currentThread().getName()
                            + ": ���ȸ��ҵ���, ���ٸ������");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized ("����") {
                        System.out.println(Thread.currentThread()
                                .getName() + ": �ð�, �ѿ��Ӹ���.");
                    }
                }
            }
        }, "������").start();
    }
}

