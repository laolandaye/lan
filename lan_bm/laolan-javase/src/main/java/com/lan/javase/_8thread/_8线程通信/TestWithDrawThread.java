package com.lan.javase._8thread._8线程通信;


class Account {
    private String accountId;
    private double balance;

    public Account() {
    }

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String toString() {
        return "Account [accountId=" + accountId + ", balance=" + balance + "]";
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((accountId == null) ? 0 : accountId.hashCode());
        long temp;
        temp = Double.doubleToLongBits(balance);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (accountId == null) {
            if (other.accountId != null) return false;
        } else if (!accountId.equals(other.accountId))
            return false;
        if (Double.doubleToLongBits(balance) != Double
                .doubleToLongBits(other.balance))
            return false;
        return true;
    }
}

class WithDrawThread extends Thread {
    Account account;
    //要取款的额度
    double withDraw;

    public WithDrawThread(String name, Account account, double amt) {
        super(name);
        this.account = account;
        this.withDraw = amt;
    }

    public void run() {
        synchronized (account) {
            if (account.getBalance() > withDraw) {
                System.out.println(Thread.currentThread().getName()
                        + ":取款成功，取现的金额为：" + withDraw);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                account.setBalance(account.getBalance() - withDraw);
            } else {
                System.out.println("取现额度超过账户余额，取款失败");
            }
            System.out.println("现在账户的余额为：" + account.getBalance());
        }
    }
}

public class TestWithDrawThread {
    public static void main(String[] args) {
        Account account = new Account("88888888", 10000);
        Thread t1 = new WithDrawThread("小雪", account, 8000);
        Thread t2 = new WithDrawThread("小雪's wife", account, 2800);
        t1.start();
        t2.start();
    }

}
