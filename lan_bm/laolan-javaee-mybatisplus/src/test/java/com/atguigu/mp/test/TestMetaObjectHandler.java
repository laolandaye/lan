package com.atguigu.mp.test;

import com.atguigu.mp.beans.User;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMetaObjectHandler {
	
	ApplicationContext ctx  = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	EmployeeMapper employeeMapper = ctx.getBean("employeeMapper", EmployeeMapper.class);
	
	UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);

	/**
	 * 测试公共字段填充
	 */
	@Test
	public void testMetaObjectHandler() {
		User user = new User();
		//user.setName("Tom");
		user.setLogicFlag(1);

		userMapper.insert(user);
		user.setId(2);
//		userMapper.updateById(user);
	}
	
}
