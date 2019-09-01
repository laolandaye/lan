package spring.c3p0.crud.select;

import org.springframework.jdbc.core.RowMapper;
import spring.c3p0.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MyRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int num) throws SQLException {
		// 1 从结果集里面把数据得到
		String username = rs.getString("username");
		String password = rs.getString("password");

		// 2 把得到数据封装到对象里面
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		return user;
	}

}






