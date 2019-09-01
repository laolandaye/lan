package spring.c3p0.crud.select;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import spring.c3p0.User;

import java.util.List;

public class SelectListObjsDemo {

    /*
     * 2 jdbcTemplate实现查询，有接口 RowMapper，
     * jdbcTemplate针对这个接口没有提供实现类，得到不同的类型数据需要自己进行数据封装
     * jdbcTemplate.query(sql,new MyRowMapper());
     * */

    //4 查询返回对象集合
    @Test
    public void testList() {

        //		ComboPooledDataSource dataSource = new ComboPooledDataSource();
        //		dataSource.setDriverClass("com.mysql.jdbc.Driver");
        //		dataSource.setJdbcUrl("jdbc:mysql:///spring7");
        //		dataSource.setUser("root");
        //		dataSource.setPassword("root");

        //设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring7");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //写sql语句
        String sql = "select * from user";
        //调用jdbcTemplate的方法实现
        List<User> list = jdbcTemplate.query(sql, new MyRowMapper());

        System.out.println(list);

    }
}






