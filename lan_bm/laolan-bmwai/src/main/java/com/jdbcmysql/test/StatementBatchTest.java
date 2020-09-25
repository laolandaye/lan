package com.jdbcmysql.test;

import com.jdbcmysql.JdbcUtils;
import com.jdbcmysql.JdbcUtils2;
import com.jdbcmysql.po.Car;

import java.sql.*;
import java.util.ArrayList;

public class StatementBatchTest {

    public static void main(String[] args) throws SQLException {
        // 定义sql语句
        String sql1 = "create table person(id int,name varchar(20))";
        String sql2 = "insert into person values(1,'tom')";
        String sql3 = "insert into person values(2,'fox')";
        String sql4 = "insert into person values(3,'tony')";
        String sql5 = "update person set name='张三' where id=1";
        String sql6 = "delete from person where id=3";

        Connection conn;
        Statement st;
        try {
            conn = JdbcUtils2.getConnection();
            // 得到一个Statement对象
            st = conn.createStatement();

            // 使用批处理执行sql
            st.addBatch(sql1);
            st.addBatch(sql2);
            st.addBatch(sql3);
            st.addBatch(sql4);
            st.addBatch(sql5);
            st.addBatch(sql6);

            // 执行批处理
            st.executeBatch();

            JdbcUtils2.release(conn, st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


