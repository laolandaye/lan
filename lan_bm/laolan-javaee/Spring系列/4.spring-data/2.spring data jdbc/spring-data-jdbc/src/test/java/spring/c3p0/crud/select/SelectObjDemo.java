package spring.c3p0.crud.select;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import spring.c3p0.User;

import java.sql.*;

public class SelectObjDemo {

    /* *
     * jdbcTemplate.queryForObject(sql, Integer.class);
     * */

    //1 查询表有多少条记录
    @Test
    public void testCount() {
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3307/jdbc");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //调用方法得到记录数
        String sql = "select count(*) from t_user";
        //调用jdbcTemplate的方法
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    //2 jdbc实现代码
    @Test
    public void testJDBC() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //创建连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/jdbc", "root", "root");
            //编写sql语句
            String sql = "select * from user where username=?";
            //预编译sql
            psmt = conn.prepareStatement(sql);
            //设置参数值
            psmt.setString(1, "lucy");
            //执行sql
            rs = psmt.executeQuery();
            //遍历结果集
            while(rs.next()) {
                //得到返回结果值
                String username = rs.getString("username");
                String password = rs.getString("password");
                //放到user对象里面
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);

                System.out.println(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    //3 查询返回对象
    @Test
    public void testObject() {
        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3307/jdbc");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //写sql语句，根据username查询
        String sql = "select * from user where username=?";
        //调用jdbcTemplate的方法实现
        //第二个参数是接口 RowMapper，需要自己写类实现接口，自己做数据封装
        User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), "mary");
        System.out.println(user);
    }

}
