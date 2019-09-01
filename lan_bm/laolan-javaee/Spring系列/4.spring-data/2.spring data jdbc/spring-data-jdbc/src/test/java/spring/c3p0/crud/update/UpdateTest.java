package spring.c3p0.crud.update;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class UpdateTest {

	/**
	 *  jdbcTemplate.update(sql, [] objs);
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// 1 添加操作
	@Test
	public void add() {
		String sql = "insert into user values(?,?)";
		int rows = jdbcTemplate.update(sql, "lucy","250");
		System.out.println(rows);
	}

	//2 修改操作
	@Test
	public void update() {
		String sql = "update user set password=? where username=?";
		Object [] objs = {"1314","lucy"};
		int rows = jdbcTemplate.update(sql, objs);
		System.out.println(rows);
	}


	//3 删除操作
	@Test
	public void delete() {
		String sql = "delete from user where username=?";
		int rows = jdbcTemplate.update(sql, "lucy");
		System.out.println(rows);
	}


	/*
	 * QueryRunner runner = new QueryRuner(datasource);
	 * 返回对象
	 * runner.query(sql,new BeanHandler<User>(User.class));
	 * 
	 * 返回list集合
	 * runner.query(sql,new BeanListHander<User>(User.class))
	 * 
	 * 1 在dbutils时候，有接口 ResultSetHandler
	 * dbutils提供了针对不同的结果实现类
	 * 
	 * 2 jdbcTemplate实现查询，有接口 RowMapper，
	 * jdbcTemplate针对这个接口没有提供实现类，得到不同的类型数据需要自己进行数据封装
	 * 
	 * */




}







