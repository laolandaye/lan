package com.jdbcmysql.dao;

import com.jdbcmysql.JdbcUtils2;
import com.jdbcmysql.po.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 使用 JdbcUtils2
 */

public class CarDao {

    public ArrayList<Car> seleteAll() {
        Connection conn = JdbcUtils2.getConnection();

        String sql = "select * from car";
        PreparedStatement pstmt;
        ResultSet res;
        ArrayList<Car> list = null;
        try {
            pstmt = conn.prepareStatement(sql);

            res = pstmt.executeQuery();

            list = new ArrayList<>();

            while (res.next()) {
                Car car = new Car();
                car.setId(res.getInt(1));
                car.setName(res.getString(2));
                car.setType(res.getString(3));
                car.setPrice(res.getDouble(4));
                list.add(car);
            }

            JdbcUtils2.release(conn, pstmt, res);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}



