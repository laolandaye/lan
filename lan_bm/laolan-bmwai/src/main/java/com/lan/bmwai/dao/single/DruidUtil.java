package com.lan.bmwai.dao.single;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

/**
 * Druid数据库连接池的核心类 ： DruidDataSourceFactory
 */
public class DruidUtil {

	private static final Logger LOG = LoggerFactory.getLogger(DruidUtil.class);

	// 线程局部变量，保存某个线程使用的连接
	private static ThreadLocal<Connection> thread = new ThreadLocal<>();
	private static DataSource dataSource = null;
	static{
		Properties properties = new Properties();
		try {
			properties.load(DruidUtil.class.getClassLoader().getResourceAsStream("single/db.properties"));
			properties.setProperty("driverClassName", properties.getProperty("jdbc.driver"));
			properties.setProperty("url", properties.getProperty("jdbc.url"));
			properties.setProperty("username", properties.getProperty("jdbc.user"));
			properties.setProperty("password", properties.getProperty("jdbc.password"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);	// 由此倒推
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		// 首先从线程局部变量中获取连接，如果线程局部变量中没有保存有连接，说明是第一次获取
		Connection conn = thread.get();
		if (conn == null) {
			try {
				// 第一次获取，从数据源，数据库连接池中获取
				conn = dataSource.getConnection();
                thread.set(conn); // 将con装入到ThreadLocal中。
				LOG.info("数据源，数据库连接池连接成功");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	/**
	 * 释放 连接，关闭结果集(单个结果，非事务使用)
	 */
	public static void closeConnection() {
		Connection conn = thread.get();
		if(conn == null){
			LOG.info("Connection未开启");
		} else {
			try {
				conn.close();
				thread.remove();//移走
				LOG.info("Conn已关闭连接");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 释放 连接，关闭结果集（事务，本类调用就可以了）
	 */
	private static void closeConnection(Connection connection) {
		Connection conn = thread.get();
		if(conn == null){
			LOG.info("Connection未开启");
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
				LOG.info("Connection未开启");
			} else {
				try {
					connection.close();
					thread.remove();// 移除线程
					LOG.info("Connection已关闭连接");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LOG.error("关闭PreparedStatement失败", e);
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				LOG.error("关闭Statement失败", e);
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet res) {
		if (res != null) {
			try {
				res.close();
			} catch (SQLException e) {
				LOG.error("关闭ResultSet失败", e);
				e.printStackTrace();
			}
		}
	}

	/*********************************************************************************/

	/**
	 * 开启事务
	 */
	public static void openTransaction() throws SQLException {
		Connection conn = getConnection();
		// 设置自动提交为false，执行SQL语句时并没有立即生效，需要手动调用commit方法
		conn.setAutoCommit(false);
		LOG.info("开启事务成功");
	}

	/**
	 * 提交事务
	 */
	public static void commitTransaction() {
		Connection conn = getConnection();
		try {
			conn.commit();
			LOG.info("提交事务成功");
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeConnection(conn);
		}
	}

	/**
	 * 回滚事务，发生异常时，应该撤销的操作
	 */
	public static void rollbackTransaction()  {
		Connection conn = getConnection();
		try {
			conn.rollback();
			LOG.error("回滚事务成功");
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeConnection(conn);
		}

	}

	/**
	 * 保存事务点
	 */
	public static Savepoint saveTransPoint() throws SQLException {
		Connection conn = getConnection();
		Savepoint sp = conn.setSavepoint();
		LOG.info("保存事务节点");
		return sp;
	}

	/**
	 * 保存事务点
	 */
	public static Savepoint saveTransPoint(String name) throws SQLException {
		Connection conn = getConnection();
		Savepoint sp = conn.setSavepoint(name);
		LOG.info("保存事务节点：{}", name);
		return sp;
	}

	/**
	 * 回滚事务，发生异常时，应该撤销的操作
	 */
	public static void rollbackTransPoint(Savepoint sp)  {
		Connection conn = getConnection();
		try {
			conn.rollback(sp);
			LOG.error("回滚事务成功:{}", sp.getSavepointName());
			conn.commit();
			LOG.info("提交事务节点：{}", sp.getSavepointName());
		} catch (SQLException se) {
			se.printStackTrace();
		}  finally {
			closeConnection(conn);
		}

	}
}
