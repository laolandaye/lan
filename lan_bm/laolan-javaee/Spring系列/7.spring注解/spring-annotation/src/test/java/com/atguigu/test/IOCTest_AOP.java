package com.atguigu.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.atguigu.aop.MathCalculator;
import com.atguigu.bean.Boss;
import com.atguigu.bean.Car;
import com.atguigu.bean.Color;
import com.atguigu.bean.Red;
import com.atguigu.config.MainConfigOfAOP;
import com.atguigu.config.MainConifgOfAutowired;
import com.atguigu.dao.BookDao;
import com.atguigu.service.BookService;

public class IOCTest_AOP {

	@Test
	public void test01(){
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

		//1、不要自己创建对象
		MathCalculator mathCalculator0 = new MathCalculator();
		mathCalculator0.div(1, 1);
		System.out.println("*************************************************************************************************");
		//2. 使用 spring 容器
		MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
		mathCalculator.div(1, 1);
		System.out.println("*************************************************************************************************");
		MathCalculator mathCalculator2 = applicationContext.getBean(MathCalculator.class);
		mathCalculator2.div(1, 0);

		applicationContext.close();
	}

}
