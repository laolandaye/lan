package com.prosay.test;

import java.sql.*;

public class TestJDBC03 {

	public static void main(String[] args) {
		//queryEmp("SMITH");	// 查询员工表所有信息
		//queryEmp("SMITH' or '1'='1 ");	// SQL注入问题
		queryEmp("SMITH' or 1=1 --");	// SQL注入问题
	}

	/**
	 * 根据员工姓名去查询员工信息
	 * @param ename	姓名
	 */
	private static void queryEmp(String name) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		/* No suitable driver found for 没有合适的驱动
		 * jdbc:oracle:thin:@127.0.0.1:1521:orcl
		 */
		String url = "jdbc:oracle:thin:@192.168.25.101:1521:orcl";
		String user = "scott";
		String password = "tiger";
		try {
			// ① 加载驱动(类比理解为找谁来修桥)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//OracleDriver，Class类的描述和抽象，所有的Java类都会具有共同的特征（Filed）和共同的方法(Method)
			// ② 建立与数据库的连接  DriverManager（类比施工队）Connection（类比桥梁）
			conn = DriverManager.getConnection(url, user, password);
			//System.out.println(connection);
			// 在企业开发中，不允许在SQL语句中使用*，SQL性能优化，*是取出所有列，IO消耗很大
			String sql = "SELECT empno,ename,job,mgr as manager,sal,deptno FROM emp where ename=?";
			// ③ 预编译对象（类比汽车）  ? 作为占位符
			ps = conn.prepareStatement(sql);// sql语句已经预编译
			ps.setString(1, name);	// 替换掉占位符的值
			System.out.println(sql);
			// ④ 得到结果集（针对查询）如果是executeUpte返回int
			rs = ps.executeQuery();		// 执行时不再需要传入sql语句
			// ⑤ 遍历结果集 next返回判断是否有下一条记录，如果有，这取出
			while (rs!=null && rs.next()) {
				// rs类比游标，移动后，实质代表一行数据，通过索引取值
				int empno = rs.getInt(1);	// 注意从1开始
				String ename = rs.getString("ename");
				String job = rs.getString("job");
				//int mgr = rs.getInt("mgr");	// 列名无效
				int mgr = rs.getInt("manager");	// 注意使用【列别名】
				double sal = rs.getDouble("sal");
				//int deptno = rs.getInt(8);
				int deptno = rs.getInt("deptno");
				System.out.println(empno + "," + ename  + "," +  job  + "," +  mgr  + "," + sal  + "," + deptno );
				/*
				 *  通过列名取值，问题：通过索引取值和通过列名取值有什么区别？
				 *  索引取值更快，Java中索引唯一从1开始的就是JDBC编程
				 *  企业开发中一般不适用索引值取列数据，一旦查询select后面选取的字段数量右边，或者列的数量右边，而select后面还是*，无效的列索引
				 *  实际企业开发中一般填写查询中的【列别名】，
				 */
			}
			//System.out.println(rs);
			// oracle.jdbc.driver.OracleResultSetImpl@d041cf
			// ResultSet是sun公司定义的JDBC的一套规范和标准中的一个接口
			/*
			 * 【面试题】execute、executeUpdate、executeQuery三个方法的区别？
			 * 可以执行的SQL语句
			 * 	execute 执行：任意，特别注意：execute返回返回false说明这条SQL语句执行失败？？？
			 * 				返回值是boolean布尔类型，有结果集，返回true，无结果集，返回false
			 * 				执行更新语句，执行成功，返回false。失败会抛出异常！！！！！！
			 * 				执行结果返回一个ResultSet对象或一个Object，返回true？
			 * 	executeUpdate执行更新：insert、update、delete等DML、DDL
			 * 				返回值是int，更新成功的记录条数
			 * 	executeQuery执行查询：DQL（select）
			 * 				返回值是ResultSet对象，结果集
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{		// 不管上面代码时候正常执行，还是报错了，最终都会执行的代码
			// ⑥ 释放资源（交货、交车、关闭高速公路）
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
