package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.bean.Red;
import com.atguigu.config.MainConifgOfAutowired;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;

public class IOCTest_Autowired {
	
	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);
		
		BookService bookService = applicationContext.getBean(BookService.class);
		System.out.println(bookService);
		
//		BookDao bean = applicationContext.getBean(BookDao.class);
//		System.out.println(bean);
		
//		Boss boss = applicationContext.getBean(Boss.class);
//		System.out.println(boss);
//		Car car = applicationContext.getBean(Car.class);
//		System.out.println(car);
//
//		Color color = applicationContext.getBean(Color.class);
//		System.out.println(color);
//		System.out.println(applicationContext);
//		applicationContext.close();
	}

	// testAutowired, testQualifier, testRequired, testPrimary, testResource, testName �����������
	@Test
	public void testAutowired(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);

		BookService bookService = applicationContext.getBean(BookService.class);
		System.out.println(bookService);
	}

	// testFangfa�� testDouZhaoQi
	@Test
	public void testFangfa(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);

		Boss boss = applicationContext.getBean(Boss.class);
		System.out.println(boss);
		Car car = applicationContext.getBean(Car.class);
		System.out.println(car);
	}


	@Test
	public void testCanShu(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConifgOfAutowired.class);

		Boss boss = applicationContext.getBean(Boss.class);
		System.out.println(boss);

		Color color = applicationContext.getBean(Color.class);
		System.out.println(color);
	}
}
