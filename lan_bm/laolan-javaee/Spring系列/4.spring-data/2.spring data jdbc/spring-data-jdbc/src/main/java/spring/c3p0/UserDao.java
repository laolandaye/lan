package spring.c3p0;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {
	
	//得到JdbcTemplate对象
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	//添加操作
	public void add() {
		//创建jdbcTemplate对象
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into user values(?,?)";
		jdbcTemplate.update(sql, "李雷","520");
	}

}
