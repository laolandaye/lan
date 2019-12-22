package com.atguigu.mp.test;

import com.atguigu.mp.beans.User;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestOracle {
	
	ApplicationContext ctx  = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	EmployeeMapper employeeMapper = ctx.getBean("employeeMapper", EmployeeMapper.class);
	
	UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);
	
	
	/**
	 * 测试Oracle 主键 Sequence
	 */
	@Test
	public void testOracle() {
		User user = new User();
		user.setLogicFlag(1);
		user.setName("OracleSEQ");
		userMapper.insert(user);
	}
	
}
