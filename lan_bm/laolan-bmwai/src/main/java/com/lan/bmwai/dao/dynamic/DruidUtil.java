package com.lan.bmwai.dao.dynamic;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态加载，多数据源
 * Druid数据库连接池的核心类 ： DruidDataSourceFactory
 */
public class DruidUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DruidUtil.class);

	// 线程局部变量，保存某个线程使用的连接
	private static ThreadLocal<Connection> thread = new ThreadLocal<>();

	public static Connection getConnection(String dbConfPath) {
		// 首先从线程局部变量中获取连接，如果线程局部变量中没有保存有连接，说明是第一次获取
		Connection conn = thread.get();
		if (conn == null) {
			try {
				// 第一次获取，从数据源，数据库连接池中获取
				DataSource dataSource = DruidDataSourceFactory.createDataSource(DruidDbConf.getProperties(dbConfPath));
				conn = dataSource.getConnection();
                thread.set(conn); // 将con装入到ThreadLocal中。
				LOG.info("数据源，数据库连接池连接成功");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
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
				LOG.info("开启事务成功");
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
				LOG.info("提交事务成功");
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
                LOG.debug("回滚事务成功");
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
				LOG.info("Conn已关闭连接");
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
				LOG.info("Connection已关闭连接");
			}
		}
	}


	/**
	 * 释放 连接，关闭结果集(合并)
	 */
	public static void release(Connection conn, PreparedStatement pstmt, ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				LOG.error("关闭ResultSet失败", e);
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LOG.error("关闭PreparedStatement失败", e);
				e.printStackTrace();
			}
		}
		if(null != conn) {
			try {
				closeConnection(conn);
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}

}
