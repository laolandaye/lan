package com.atguigu.springboot;

import com.atguigu.springboot.bean.Person;
import com.atguigu.springboot.bean.PersonConfigurationProperties;
import com.atguigu.springboot.bean.PersonValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
/**
 * SpringBoot单元测试;
 *
 * 可以在测试期间很方便的类似编码一样进行自动注入等容器的功能
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot02ConfigApplicationTests {

	@Autowired
	Person person;

	@Autowired
	PersonConfigurationProperties personConfigurationProperties;

	@Autowired
	PersonValue personValue;

	@Autowired
	ApplicationContext ioc;

	@Test
	public void contextLoads() {
		System.out.println(person);
	}

	@Test
	public void contextLoads2() {
		System.out.println(personConfigurationProperties);
	}

	@Test
	public void contextLoads3() {
		System.out.println(personValue);
	}

	@Test
	public void testHelloService(){
		boolean b = ioc.containsBean("helloService02");
		System.out.println(b);
	}
}
