package com.lan;

import com.lan.po.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2.持久化操作.docx
 */
public class _2Main {
	
	public static void main(String[] args) {
		
		//1. 创建 EntitymanagerFactory
		String persistenceUnitName = "jpa_test1";
		
//		EntityManagerFactory entityManagerFactory = 
//				Persistence.createEntityManagerFactory(persistenceUnitName);
			
		Map<String, Object> properites = new HashMap<>();
		properites.put("hibernate.show_sql", true);
		
		EntityManagerFactory entityManagerFactory = 
				Persistence.createEntityManagerFactory(persistenceUnitName, properites);
		
		//2. 创建 EntityManager. 类似于 Hibernate 的 SessionFactory
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		//3. 开启事务
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		//4. 进行持久化操作
		Customer customer = new Customer();
		customer.setAge(12);
		customer.setEmail("tom@atguigu.com");
		customer.setLastName("Tom");
		customer.setCreatedTime(new Date());
		customer.setBirth(new Date());
		
		entityManager.persist(customer);
		
		//5. 提交事务
		transaction.commit();
		
		//6. 关闭 EntityManager
		entityManager.close();
		
		//7. 关闭 EntityManagerFactory
		entityManagerFactory.close();
	}
	
}
