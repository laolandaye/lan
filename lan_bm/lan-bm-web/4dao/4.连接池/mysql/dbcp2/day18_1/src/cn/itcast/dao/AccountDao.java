package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Account;


public interface AccountDao {
	public void accountOut(String accountOut, double money);

	public void accountIn(String accountIn, double money);

	/**
	 * 查询所有
	 */
	public List<Account> findAll();
}
