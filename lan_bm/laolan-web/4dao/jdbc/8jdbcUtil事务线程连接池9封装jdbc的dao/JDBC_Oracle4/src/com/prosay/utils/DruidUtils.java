package com.prosay.utils;

import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Druid数据库连接池的核心类 ： DruidDataSourceFactory
 * @ClassName	:	C3P0Utils
 * @Description	:
 * @author		:	猿道教育Java学院院长-Will老师
 * @date		:	2018年7月19日 下午10:22:18
 */
public class DruidUtils {
	// 线程局部变量，保存某个线程使用的连接
	private static ThreadLocal<Connection> thread = new ThreadLocal<>();
	// 无参构造方法会去获取c3p0-config.xml配置文件中的默认配置信息default-config
	//static DataSource ds = new ComboPooledDataSource();
	// 获取配置文件中named-config name="oracle11gorcl"对应的配置
	static DruidDataSourceFactory factory = new DruidDataSourceFactory();
	static DataSource dataSource = null;
	static{
		Properties properties = new Properties();
		try {
			properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			properties.setProperty("driverClassName", properties.getProperty("jdbc.driver"));
			properties.setProperty("url", properties.getProperty("jdbc.url"));
			properties.setProperty("username", properties.getProperty("jdbc.user"));
			properties.setProperty("password", properties.getProperty("jdbc.password"));
            dataSource = factory.createDataSource(properties);	// 由此倒推
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		// 首先从线程局部变量中获取连接，如果线程局部变量中没有保存有连接，说明是第一次获取
		Connection conn = thread.get();
		if (conn == null) {
			try {
				// 第一次获取，从数据源，数据库连接池中获取
				conn = dataSource.getConnection();
                thread.set(conn); // 将con装入到ThreadLocal中。
				System.out.println("数据源，数据库连接池连接成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * 开启事务
	 */
	public static void openTransaction() throws SQLException{
		Connection conn = thread.get();
		// 判断事务是否开始了，如果还没有开始事务，则不能提交
		if(conn == null) {
			throw new SQLException("还没有开始事务");
		} else {
			try {
				// 设置自动提交为false，执行SQL语句时并没有立即生效，需要手动调用commit方法
				conn.setAutoCommit(false);
                thread.set(conn); //向 ThreadLocal 填充 数据库连接
				System.out.println("开启事务成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() throws SQLException{
		Connection conn = thread.get();
		// 判断事务是否开始了，如果还没有开始事务，则不能提交
		if(conn == null) {
			throw new SQLException("还没有开始事务");
		} else {
			try {
				conn.commit();
                thread.remove();//移走
				System.out.println("提交事务成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 回滚事务，发生异常时，应该撤销的操作
	 */
	public static void rollbackTransaction() throws SQLException{
		Connection conn = thread.get();
		if(conn == null) {
			throw new SQLException("无法回滚事务");
		} else {
			try {
				conn.rollback();
                thread.remove();
				System.out.println("回滚事务成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection(Connection connection) throws SQLException{
		Connection conn = thread.get();
		if(conn == null){
			throw new SQLException("Connection未开启");
		} else {
			try {
				conn.close();
                thread.remove();//移走
				System.out.println("Conn已关闭连接");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if(conn != connection){
			if(connection == null){
				throw new SQLException("Connection未开启");
			} else {
				connection.close();
                thread.remove();// 移除线程
				System.out.println("Connection已关闭连接");
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("关闭PreparedStatement失败");
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				System.out.println("关闭ResultSet失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放 连接，关闭结果集
	 */
	public static void release(Connection conn, PreparedStatement pstmt, ResultSet res) throws SQLException {
		closeResultSet(res);
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}

	public static void release(Connection conn, PreparedStatement pstmt)  throws SQLException {
		closePreparedStatement(pstmt);
		closeConnection(conn);
	}

	public static void release(PreparedStatement pstmt, ResultSet res) {
		closeResultSet(res);
		closePreparedStatement(pstmt);
	}

	public static void release(PreparedStatement pstmt) {
		closePreparedStatement(pstmt);
	}

}
