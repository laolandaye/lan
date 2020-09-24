package com.jdbcmysql;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 方法二通过输入流扫描  jdbc.properties
 */
public class JdbcUtils2 {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        /**
         * 输入流
         */
        InputStream in = JdbcUtils2.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();

        try {
            //将输入流加载到配置文件的
            properties.load(in);
        } catch (IOException e) {
            System.out.println("jdbc.properties加载失败");
            e.printStackTrace();
        }

        //读取配置文件参数
        driver = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        user = properties.getProperty("username");
        password = properties.getProperty("password");

    }

    static {
        try {
            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("注册驱动失败");
            e.printStackTrace();
        }
    }


    /**
     * 创建连接
     *
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("创建数据库连接失败");
            e.printStackTrace();
            return null;
        }
    }

    // 用于批量处理，没有返回结果
    public static void release(Connection conn, Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
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

    public static void release(Connection conn, PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
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

    public static void release(Connection conn, Statement stmt, ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                System.out.println("关闭ResultSet失败");
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("关闭Statement失败");
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("关闭Connection失败");
                e.printStackTrace();
            }
        }
    }

    public static void release(Connection conn, PreparedStatement pstmt, ResultSet res) {
        if (res != null) {
            try {
                res.close();
            } catch (SQLException e) {
                System.out.println("关闭ResultSet失败");
                e.printStackTrace();
            }
        }

        if (pstmt != null) {
            try {
                pstmt.close();
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
