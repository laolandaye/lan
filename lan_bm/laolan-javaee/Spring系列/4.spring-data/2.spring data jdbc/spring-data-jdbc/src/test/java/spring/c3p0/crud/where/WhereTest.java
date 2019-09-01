package spring.c3p0.crud.where;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.c3p0.User;
import spring.c3p0.crud.select.MyRowMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class WhereTest {

    /*
     * jdbcTemplate之sql参数注入：https://www.cnblogs.com/VergiLyn/p/6161081.html
     * ？ 数组形式的参数
     * ： map形式的参数
     *    javaBean形式的参数
     * */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //1 ? 数组形式的参数
    @Test
    public void testWenHaoArray() {
        //写sql语句
        List<Object> params = new ArrayList<>();
        StringBuffer sbSql = new StringBuffer();
        sbSql.append(" select * from user ");
        sbSql.append(" where username=? and password = ? ");
        params.add("李雷");
        params.add("520");
        //调用jdbcTemplate的方法实现
        List<User> list = jdbcTemplate.query(sbSql.toString(), params.toArray(), new MyRowMapper());

        for (User user : list) {
            System.out.println(user);
        }
    }

    //2 ：map形式的参数
    @Test
    public void testMaoHaoMap() {
        List<String> pwds = new ArrayList<>();
        pwds.add("521");
        pwds.add("520");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        //写sql语句
        StringBuffer sbSql = new StringBuffer();
        sbSql.append(" select * from user ");
        sbSql.append(" where username=:username and password in (:password) ");

        Map<String,Object> params = new HashMap<>();
        params.put("username", "李雷");
        params.put("password", pwds);
        //调用jdbcTemplate的方法实现
        List<User> list = namedParameterJdbcTemplate.query(sbSql.toString(), params, new MyRowMapper());

        for (User user : list) {
            System.out.println(user);
        }
    }

    //3 ：javabean形式的参数
    @Test
    public void testMaoHaoBean() {
        List<String> pwds = new ArrayList<>();
        pwds.add("521");
        pwds.add("520");

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        //写sql语句
        StringBuffer sbSql = new StringBuffer();
        sbSql.append(" select * from user ");
        sbSql.append(" where username=:username and password in (:password) ");

        ParamBean bean = new ParamBean();
        bean.setUsername("李雷");
        bean.setPassword(pwds);
        SqlParameterSource params = new BeanPropertySqlParameterSource(bean);
        //调用jdbcTemplate的方法实现
        List<User> list = namedParameterJdbcTemplate.query(sbSql.toString(), params, new MyRowMapper());

        for (User user : list) {
            System.out.println(user);
        }
    }
}






