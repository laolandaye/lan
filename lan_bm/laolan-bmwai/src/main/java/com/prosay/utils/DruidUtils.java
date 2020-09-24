package com.prosay.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid数据库连接池的核心类 ： DruidDataSourceFactory
 * @ClassName	:	C3P0Utils
 * @Description	:	TODO(这里用一句话描述这个类的作用)
 * @author		:	猿道教育Java学院院长-Will老师
 * @date		:	2018年7月19日 下午10:22:18
 *         需要本课视频的同学加【猿道王牌助理--Shirley老师】QQ：1916054912
 *	                     需要本课视频的同学加【猿道一级助理--Anna老师】QQ：2123479036
 *<a href="https://ke.qq.com/course/204364#tuin=626ac804">Java免费直播分享入场券</a>
 */
public class DruidUtils {

	// 无参构造方法会去获取c3p0-config.xml配置文件中的默认配置信息default-config
	//static DataSource ds = new ComboPooledDataSource();
	// 获取配置文件中named-config name="oracle11gorcl"对应的配置
	static DruidDataSourceFactory f = new DruidDataSourceFactory();
	static DataSource ds = null;
	static{
		Properties properties = new Properties();
		try {
			properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			properties.setProperty("driverClassName", properties.getProperty("jdbc.driver"));
			properties.setProperty("url", properties.getProperty("jdbc.url"));
			properties.setProperty("username", properties.getProperty("jdbc.user"));
			properties.setProperty("password", properties.getProperty("jdbc.password"));
			ds = f.createDataSource(properties);	// 由此倒推
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	public static void closeConnection(){

	}

	public static void main(String[] args) {
		for (int i = 0; i < 4; i++) {
			System.out.println(DruidUtils.getConnection());
		}
	}
}
