package com.jdbcmysql.test;

import com.jdbcmysql.JdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  适合执行相同的的sql、
 */

public class PreparedStatementBatchTest {

    public static void main(String[] args) throws SQLException {
        // 向person表中插入1000条数据
        String sql = "insert into person values(?,?)";

        Connection conn;
        PreparedStatement pst;
        try {
            conn = JdbcUtils2.getConnection();
            // 得到一个 prepareStatement 对象
            pst = conn.prepareStatement(sql);

            // 批处理
            long l = System.currentTimeMillis();//计算时间的，这个比Statement快
            for (int i = 1; i <= 1000; i++) {
                pst.setInt(1, i);
                pst.setString(2, "name" + i);
                pst.addBatch();

                if (i % 100 == 0) {
                    pst.executeBatch();
                    pst.clearBatch(); //清空缓存。
                }

            }

            //执行批处理
            pst.executeBatch();

            JdbcUtils2.release(conn, pst);

            System.out.println(System.currentTimeMillis() - l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
