package spring.c3p0.crud.update;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class UpdateDemo {

	/**
	 *  jdbcTemplate.update(sql, "lucy","250");
	 */

	// 1 添加操作
	@Test
	public void add() {
		//			 设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://jdbc:mysql://localhost:3307/jdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		//			 创建jdbcTemplate对象，设置数据源
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		//			 调用jdbcTemplate对象里面的方法实现操作
		//创建sql语句
		String sql = "insert into user values(?,?)";
		int rows = jdbcTemplate.update(sql, "lucy","250");
		System.out.println(rows);
	}

	//2 修改操作
	@Test
	public void update() {
		//设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://jdbc:mysql://localhost:3307/jdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		//创建jdbcTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		//调用jdbcTemplate里面的方法实现 update方法
		String sql = "update user set password=? where username=?";
		Object [] objs = {"1314","lucy"};
		int rows = jdbcTemplate.update(sql, objs);
		System.out.println(rows);
	}


	//3 删除操作
	@Test
	public void delete() {
		//设置数据库信息
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://jdbc:mysql://localhost:3307/jdbc");
		dataSource.setUsername("root");
		dataSource.setPassword("root");

		//创建jdbcTemplate对象
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		//调用update方法实现删除
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







