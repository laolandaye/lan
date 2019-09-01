package spring.c3p0.crud.select;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.c3p0.User;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class SelectListObjsTest {

    /*
     * 2 jdbcTemplate实现查询，有接口 RowMapper，
     * jdbcTemplate针对这个接口没有提供实现类，得到不同的类型数据需要自己进行数据封装
     * jdbcTemplate.query(sql,new MyRowMapper());
     *
     * jdbcTemplate.queryForList()
     * */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //1 查询返回对象集合
    @Test
    public void testObjs() {
        //写sql语句
        String sql = "select * from user";
        //调用jdbcTemplate的方法实现
        List<User> list = jdbcTemplate.query(sql, new MyRowMapper());

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testObjs2() {
        //写sql语句
        String sql = "select * from user";
        //调用jdbcTemplate的方法实现
        List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));

        for (User user : list) {
            System.out.println(user);
        }
    }

    //2 查询返回 list<Map>
    @Test
    public void testMaps() {
        //写sql语句
        String sql = "select dept_id as deptId from employees";
        //调用jdbcTemplate的方法实现
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);

        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }
}






