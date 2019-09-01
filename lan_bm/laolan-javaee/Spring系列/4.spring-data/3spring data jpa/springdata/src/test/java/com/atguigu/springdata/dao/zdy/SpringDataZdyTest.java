package com.atguigu.springdata.dao.zdy;

import com.atguigu.springdata.dao.zdy.PersonZdyRepsotory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDataZdyTest {

	private ApplicationContext ctx = null;
	private PersonZdyRepsotory personZdyRepsotory = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personZdyRepsotory = ctx.getBean(PersonZdyRepsotory.class);
	}


	@Test
	public void testCustomRepositoryMethod(){
		personZdyRepsotory.test();
	}

}
