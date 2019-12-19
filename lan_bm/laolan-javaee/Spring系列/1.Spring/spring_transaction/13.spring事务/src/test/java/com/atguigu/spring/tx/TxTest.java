package com.atguigu.spring.tx;

import com.atguigu.spring.tx.dao.BookShopDao;
import com.atguigu.spring.tx.service.BookShopService;
import com.atguigu.spring.tx.service.Cashier;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TxTest {
	
	//创建IOC容器对象
	ApplicationContext ioc = new ClassPathXmlApplicationContext("beans-tx.xml");
	
	@Test
	public void testCashier() {
		Cashier cashier = (Cashier) ioc.getBean("cashier");
		//创建List
		List<String> isbns = new ArrayList<>();
		isbns.add("1001");
		isbns.add("1002");
		//去结账
		cashier.checkout(1, isbns);
	}

}
