
package com.lan.javase._8thread._7������;


/**
 * ������һ���˻��������������ֱ���ͬһ���˻���3000Ԫ��ÿ�δ�1000����3�Ρ�ÿ�δ����ӡ�˻���
 * 1.�Ƿ��漰���̣߳��ǡ���2��������2�ַ�ʽ��
 * 2.�Ƿ������ݣ��У�ͬһ���˻�
 * 3.�����߳�ͬ����2�ַ�ʽ�����̰߳�ȫ��
 */

class Account {
	double balance;		//���

	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	//��Ǯ
	public synchronized void deposit(double amt){
		balance += amt;
		try {
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println(Thread.currentThread().getName() +":" +balance);
	}
}

class Customer extends Thread{
	
	Account account;
	
	public Customer(Account account) {
		this.account = account;
	}
	public void run(){
		for (int i = 0; i < 3; i++) {
			account.deposit(1000);
		}
	}
}

public class TestAccount {
	public static void main(String[] args) {
		Account account = new Account();
		Customer customer1 = new Customer(account);
		Customer customer2 = new Customer(account);
		
		customer1.setName("��");
		customer2.setName("��");
		
		customer1.start();
		customer2.start();
	}
}
