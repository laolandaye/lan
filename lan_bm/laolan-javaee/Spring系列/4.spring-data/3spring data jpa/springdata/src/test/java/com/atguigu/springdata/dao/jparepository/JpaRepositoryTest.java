package com.atguigu.springdata.dao.jparepository;

import com.atguigu.springdata.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class JpaRepositoryTest {

	private ApplicationContext ctx = null;
	private PersonJpaRepsotory personJpaRepsotory = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personJpaRepsotory = ctx.getBean(PersonJpaRepsotory.class);
	}

	//强制执行持久化
	@Test
	public void testJpaRepository(){
		Person person = new Person();
		person.setBirth(new Date());
		person.setEmail("xy@atguigu.com");
		person.setLastName("xyz");
		person.setId(28);

		Person person2 = personJpaRepsotory.saveAndFlush(person);

		System.out.println(person == person2);
	}

}
