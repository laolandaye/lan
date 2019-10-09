package com.prosay.utils;
/**
 * JDBC工具类：① 封装获取连接的方法；②封装关闭资源的方法
 * @ClassName	:	JDBCUtils
 * @Description	:	TODO(这里用一句话描述这个类的作用)
 * @author		:	猿道教育Java学院院长-Will老师
 * @date		:	2018年7月17日 下午8:48:13
 *         需要本课视频的同学加【猿道王牌助理--Shirley老师】QQ：1916054912
 *	                     需要本课视频的同学加【猿道一级助理--Anna老师】QQ：2123479036
 *<a href="https://ke.qq.com/course/204364#tuin=626ac804">Java免费直播分享入场券</a>
 */

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils{
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	// 参数信息如果写Java代码中，每一次修改都需要重新编译java源代码生成class字节码
	// 写到properties配置文件中时，不需要重新编译，可以直接执行
	static{		// 在类加载的时候 只会执行一次
		Properties properties = new Properties();
		InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
		try {
			properties.load(in);
			driver = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 必须是sourceFolder文件夹，文件后缀必须是properties
		//ResourceBundle rsb = ResourceBundle.getBundle("db.properties");
		/*ResourceBundle rsb = ResourceBundle.getBundle("db");
		driver = rsb.getString("jdbc.driver");
		url = rsb.getString("jdbc.url");
		user = rsb.getString("jdbc.user");
		password = rsb.getString("jdbc.password");*/
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接的驱动加载失败！\n" + e.getMessage());
		}
	}
	// ① 封装获取连接的方法
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			return null;
		}
	}

	// ②封装关闭资源的方法
	public static void closeAll(ResultSet rs,Statement st,Connection conn){
		if (rs != null) {
			try {
				rs.close();
				rs = null;		// 方便垃圾回收机制及时对内存进行清理回收
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
				st = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
