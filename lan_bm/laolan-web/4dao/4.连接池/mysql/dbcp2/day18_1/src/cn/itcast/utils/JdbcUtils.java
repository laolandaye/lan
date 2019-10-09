package cn.itcast.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;


public class JdbcUtils {


	
	private static final ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	private static DataSource dataSource;  
	static {  
		InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcp.properties");  
		Properties properties= new Properties();  
		
		try {  
			properties.load(in);  
		}catch(IOException e) {  
			e.printStackTrace();  
		}  

		try {
			dataSource = BasicDataSourceFactory.createDataSource(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  

	public static Connection getConnection() throws SQLException {
		Connection conn = tl.get();// 从ThreadLocal中获取Connection。第一次获取得到的是null.

		if (conn == null) {
			// 2.获取连接
			conn = dataSource.getConnection();
			tl.set(conn); // 将con装入到ThreadLocal中。
		}
		return conn;
	}

	/** 
	 * 开始事务 
	 * @throws SQLException 
	 */  
	public static void beginStraction() throws SQLException{  
		Connection conn = tl.get();
		if (conn == null) {
			throw new SQLException("还没有开始Connection");  
		}
		conn.setAutoCommit(false);  
		tl.set(conn); //向 ThreadLocal 填充 数据库连接  

	}  

	/** 
	 * 提交事务 
	 * @throws SQLException 
	 */  
	public static void commitStraction() throws SQLException{  
		Connection conn = tl.get();  
		// 判断事务是否开始了，如果还没有开始事务，则不能提交  
		if(conn == null)  
			throw new SQLException("还没有开始事务");  
		conn.commit();  
		tl.remove();//移走  
	}  

	/** 
	 * 回滚事务 
	 * @throws SQLException 
	 */  
	public static void rollbackStraction() throws SQLException{  
		Connection conn = tl.get();  
		if(conn == null)  
			throw new SQLException("无法回滚事务");  

		conn.rollback();  
		tl.remove();  
	}  

	/** 
	 * 释放 连接，归还连接池 
	 * @param connection 
	 * @throws SQLException 
	 */  
	public static void release(Connection connection) throws SQLException{  

		Connection conn = tl.get();  
		if(conn == null){  
			throw new SQLException("Connection未开启");  
		} else {  
			conn.close();  
		}  
		if(conn != connection){  
			if(connection == null){  
				throw new SQLException("Connection未开启");  
			} else {  
				connection.close();  
			}
		}  

	}  

	/** 
	 * 释放 连接，关闭结果集 
	 * @param connection 
	 * @throws SQLException 
	 */  
	public static void release(Statement stmt,ResultSet res) throws SQLException{  
		if(res != null){  
			res.close();  
		}  

		if (stmt != null) {
			stmt.close();
		}
	}  
	public static void release(PreparedStatement pstmt,ResultSet res) throws SQLException{  
		if(res != null){  
			res.close();  
		}  

		if (pstmt != null) {
			pstmt.close();
		}
	}  
}
