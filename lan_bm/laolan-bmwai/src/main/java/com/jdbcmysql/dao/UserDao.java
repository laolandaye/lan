package com.jdbcmysql.dao;

import com.jdbcmysql.JdbcUtils;
import com.jdbcmysql.po.User;

import java.sql.*;

public class UserDao {

        // 查找用户---使用Statement完成登录操作，存在风险(sql注入)
        public User _findUser(User user) throws SQLException {

            // 1.sql语句
            String sql = "select * from user where username='" + user.getUsername()
                    + "' and password='" + user.getPassword() + "'";

            // 2.执行sql
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;

            try {
                con = JdbcUtils.getConnection();
                st = con.createStatement();
                rs = st.executeQuery(sql);

                if (rs.next()) { // 如果可以next,代表查找到了这个用户的信息，就将结果集中的信息封装到User对象中.
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    return u;
                }

            } finally {
                try {
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(st);
                    JdbcUtils.closeConnection(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        // 使用PreparedStatement来完成操作，它可以解决sql注入.
        public User findUser(User user) throws SQLException {

            // 1.sql语句
            String sql = "select * from user where username=? and password=?";

            // 2.执行sql
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                con = JdbcUtils.getConnection();
                pst = con.prepareStatement(sql);

                pst.setString(1, user.getUsername());
                pst.setString(2, user.getPassword());

                rs = pst.executeQuery();// 无参数

                if (rs.next()) { // 如果可以next,代表查找到了这个用户的信息，就将结果集中的信息封装到User对象中.
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    return u;
                }

            }finally {
                try {
                    JdbcUtils.closeResultSet(rs);
                    JdbcUtils.closeStatement(pst);
                    JdbcUtils.closeConnection(con);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }



}
