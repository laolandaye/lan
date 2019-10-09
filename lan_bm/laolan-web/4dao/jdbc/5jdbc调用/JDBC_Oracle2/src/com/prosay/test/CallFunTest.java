package com.prosay.test;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.prosay.utils.JDBCUtils;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.util.RepConversion;

public class CallFunTest {
	public static void main(String[] args) {
//		fun1();
		fun2();
	}

	public static void fun1() {
		CallableStatement cs = null;
		PreparedStatement ps = null;
		Connection conn = JDBCUtils.getConnection();
		// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		// {call <procedure-name>[(<arg1>,<arg2>, ...)]}
		try {
			cs = conn.prepareCall("{?=call fun_getNameJob(?)}");
			cs.setInt(2, 7369);
			cs.registerOutParameter(1, OracleTypes.VARCHAR);
			cs.execute();
			String info = cs.getString(1);
			System.out.println(info);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeAll(null, cs, conn);
		}
	}

	public static void fun2() {
		CallableStatement cs = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = JDBCUtils.getConnection();
		// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		// {call <procedure-name>[(<arg1>,<arg2>, ...)]}
		try {
			System.out.println("==函数的第二种调用方法==");
			//String sql = "SELECT fun_getNameJob(?) as empInfo FROM emp";
			String sql = "SELECT fun_getNameJob(?) as empInfo FROM dual";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 7369);
			rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				String info = rs.getString("empInfo");
				System.out.println(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeAll(rs, ps, conn);
		}
	}
}
