package cn.itcast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import cn.itcast.domain.Account;
import cn.itcast.exception.AccountException;
import cn.itcast.utils.JdbcUtils;

public class AccountDaoImpl implements AccountDao {

	// 从accountOut账户转出money
	public void accountOut(String accountOut, double money) {
		String sql = "update account set money=money-? where name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setString(2, accountOut);
			int row = pstmt.executeUpdate();
			if (row == 0) {
				throw new AccountException("转出失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtils.release(pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 向accountIn账户转入money
	public void accountIn( String accountIn, double money) {
		String sql = "update account set money=money+? where name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = JdbcUtils.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setString(2, accountIn);
			int row = pstmt.executeUpdate();
			if (row == 0) {
				throw new AccountException("转入失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtils.release(pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询所有
	 */
	@SuppressWarnings("finally")
	public List<Account> findAll() {
		String sql = "SELECT * FROM account";
		List<Account> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			conn=JdbcUtils.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			list = new ArrayList<Account>();
			res = pstmt.executeQuery();
			
			while (res.next()) {
				Account account = new Account(
						res.getInt("id"), res.getString("name"), res.getDouble("money")); 
				list.add(account);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtils.release(pstmt, res);
				JdbcUtils.release(conn);
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				return list;
			}
			
		}
	}

}
