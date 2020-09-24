package com.lan.bmwai.dao.dynamic;

import java.util.Properties;

/**
 * 配置文件管理
 */
public class DruidDbConf {

	/**
	 *
	 * @param dbConfPath 配置文件路径
	 * @return
	 */
	public static Properties getProperties(String dbConfPath){
		Properties properties = new Properties();
		try {
			properties.load(DruidDbConf.class.getClassLoader().getResourceAsStream(dbConfPath));
			properties.setProperty("driverClassName", properties.getProperty("jdbc.driver"));
			properties.setProperty("url", properties.getProperty("jdbc.url"));
			properties.setProperty("username", properties.getProperty("jdbc.user"));
			properties.setProperty("password", properties.getProperty("jdbc.password"));
			properties.setProperty("removeAbandoned", "true");
			properties.setProperty("removeAbandonedTimeout", "1800");
			return properties;
		} catch (Exception e) {
			e.printStackTrace();
			return properties;
		}
	}

	public static final String DEFAULT_DATA_SOURCE = "bmwai/db.properties";

	public static final String DATA_SOURCE1 = "dynamic/db.properties";
	public static final String DATA_SOURCE2 = "dynamic/db2.properties";
	public static final String DATA_SOURCE3 = "dynamic/db3.properties";

}
