package spring.c3p0.crud.bacthUpdate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class BacthUpdateDemo {

    /**
     * 批量更新：批量insert,update,delete
     * 最后一个参数时候 Object[] 的list 类型
     */
    @Test
    public void testBatchUpdate() throws SQLException {
        //			 设置数据库信息
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql:///spring7");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //			 创建jdbcTemplate对象，设置数据源
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


        String sql = "INSERT INTO employees(last_name , email , dept_id) VALUES(? ,? ,?)";

        List<Object[]> lists = new ArrayList<>();
        lists.add(new Object[]{"AA", "aa@163.com" , 10});
        lists.add(new Object[]{"bb", "bb@163.com" , 9});
        lists.add(new Object[]{"cc", "cc@163.com" , 8});
        lists.add(new Object[]{"dd", "dd@163.com" , 7});
        lists.add(new Object[]{"ee", "ee@163.com" , 6});

        jdbcTemplate.batchUpdate(sql, lists);
    }



}
