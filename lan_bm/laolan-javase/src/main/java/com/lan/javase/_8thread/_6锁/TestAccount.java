
package com.lan.javase._8thread._6锁;


/**
 * 银行有一个账户。有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 * 1.是否涉及多线程？是。有2个储户（2种方式）
 * 2.是否共享数据？有，同一个账户
 * 3.考虑线程同步（2种方式处理线程安全）
 */

class Account {
	double balance;		//余额

	public Account() {
		// TODO Auto-generated constructor stub
	}

	//存钱
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

		customer1.setName("甲");
		customer2.setName("乙");

		customer1.start();
		customer2.start();
	}
}
