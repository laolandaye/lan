package com.lan.gbase;

import java.sql.*;

public class TestConnection {
    public static void main(String args[]) {
        try {
            String url = "jdbc:gbase://192.168.0.131:5258/testdb";
            String user = "root";
            String passwd = "root";

            Connection connect = DriverManager.getConnection(url, user, passwd);
            // 连接URL为 jdbc:gbase//服务器地址/数据库名 ，后面的2个参数分别是登  陆用户名和密码
            //testdb 数据库实例

            System.out.println("Success connect Gbase 8a server!");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from test");
            // user 为你表的名称
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            System.out.print("get data error!");
            e.printStackTrace();
        }
    }
}
