package com.prosay.test;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.prosay.utils.JDBCUtils;

import oracle.jdbc.OracleTypes;

public class CallProcTest {
	public static void main(String[] args) {
		CallableStatement cs = null;
		Connection conn = JDBCUtils.getConnection();
		// {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}
		// {call <procedure-name>[(<arg1>,<arg2>, ...)]}
		try {
			cs = conn.prepareCall("{call getMIncomeByN(?,?)}");
			cs.setString(1, "ALLEN");
			cs.registerOutParameter(2, OracleTypes.NUMBER);
			cs.execute();
			BigDecimal monthIncome = (BigDecimal) cs.getObject(2);
			System.out.println(monthIncome);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.closeAll(null, cs, conn);
		}
	}
}
