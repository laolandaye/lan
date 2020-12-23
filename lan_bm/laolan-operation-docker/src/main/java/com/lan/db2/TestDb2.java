package com.lan.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDb2 {

    public static void main(String[] args) {
        getConnect();
    }

    public static void getConnect() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            conn = DriverManager.getConnection( "jdbc:db2://192.168.0.130:20000/testdb", "db2inst1", "bmsoft@123");

            String url = "select * from TEST";
            ps = conn.prepareStatement(url);

            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("USER_NAME"));
            }
            conn.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
