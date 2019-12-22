package com.atguigu.mp.test;

import com.atguigu.mp.beans.User;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.atguigu.mp.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMySqlInjector {
	
	ApplicationContext ctx  = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	EmployeeMapper employeeMapper = ctx.getBean("employeeMapper", EmployeeMapper.class);

	UserMapper userMapper = ctx.getBean("userMapper",UserMapper.class);

	/**
	 * 测试逻辑删除
	 */
	@Test
	public void testLogicDelete() {

		Integer result = userMapper.deleteById(1);
		System.out.println("result:" +result );

		User user = userMapper.selectById(1);
		System.out.println(user);
	}


	/**
	 * 测试自定义全局操作
	 */
	@Test
	public void  testMySqlInjector() {
		Integer result = employeeMapper.deleteAll();
		System.out.println("result: " +result );
	}
	
}
