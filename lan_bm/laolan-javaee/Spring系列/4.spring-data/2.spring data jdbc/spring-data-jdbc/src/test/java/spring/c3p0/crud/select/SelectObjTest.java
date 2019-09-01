package spring.c3p0.crud.select;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.c3p0.User;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class SelectObjTest {

    /* *
     * jdbcTemplate.queryForObject();
     * jdbcTemplate.queryForMap();
     * */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //1 查询表有多少条记录
    @Test
    public void testCount() {
        //调用方法得到记录数
        String sql = "select count(*) from t_user";
        //调用jdbcTemplate的方法
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }

    //2 jdbc实现代码

    //3 查询返回对象
    @Test
    public void testOneObj() {
        //写sql语句，根据username查询
        String sql = "select * from user where username=?";
        //调用jdbcTemplate的方法实现
        //第二个参数是接口 RowMapper，需要自己写类实现接口，自己做数据封装
        User user = jdbcTemplate.queryForObject(sql, new MyRowMapper(), "mary");
        System.out.println(user);
    }

    //3 查询返回 Map
    @Test
    public void testOneMap() {
        //写sql语句，根据username查询
        String sql = "select * from user where username=?";
        //调用jdbcTemplate的方法实现
        //第二个参数是接口 RowMapper，需要自己写类实现接口，自己做数据封装
        Map map = jdbcTemplate.queryForMap(sql, "mary");
        System.out.println(map);
    }

}
