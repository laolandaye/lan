package cn.itcast.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.dao.AccountDao;
import cn.itcast.dao.AccountDaoImpl;
import cn.itcast.domain.Account;
import cn.itcast.exception.AccountException;
import cn.itcast.utils.JdbcUtils;

public class AccountService {
	// 汇款方法
	// accountIn 收款人
	// accountOut 汇款人
	// money 金额
	public void account(String accountIn, String accountOut, double money){
		// 在这个方法中要调用AccountDao中两个方法

		AccountDao dao = new AccountDaoImpl();
		Connection conn = null;

		try {
			conn = JdbcUtils.getConnection();
			
			// 开启事务
			JdbcUtils.beginStraction();

			dao.accountOut( accountOut, money);
			dao.accountIn( accountIn, money);
			
			JdbcUtils.commitStraction();
		} catch (Exception e) {
			e.printStackTrace();
			// 出现问题，进行事务回滚
			try {
				JdbcUtils.rollbackStraction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			// 事务提交
			try {
				
				JdbcUtils.release(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

	public List<Account> findAll() {
		return new AccountDaoImpl().findAll();
	}
	
	

}
