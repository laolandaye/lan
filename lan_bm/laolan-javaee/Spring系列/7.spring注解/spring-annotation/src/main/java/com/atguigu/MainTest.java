package com.atguigu;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.Person;
import com.atguigu.config.MainConfig;

public class MainTest {

	@Test
	public void xmlTest() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
		// 获得bean 是实例
		Person bean = (Person) applicationContext.getBean("person");
		System.out.println(bean);
	}
	@Test
	public void annotationTest() {
		//1.获得注解配置文件
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		// 2.获得bean 是实例（通过类型获取）
		Person bean = applicationContext.getBean(Person.class);
		System.out.println(bean);

		//2.获得bean 是实例（通过类型的名字获取）
		String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
		for (String name : namesForType) {
			System.out.println(name);
		}
	}


}
