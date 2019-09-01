package com.lan._1_4注解api.helloworld;

import com.lan.po.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class JpaTestOther {

	private EntityManagerFactory entityManagerFactory;//会话工厂
	private EntityManager entityManager;//会话
	private EntityTransaction entityTransaction;//事务

	@Before
	public void Init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test1");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
	}

	@After
	public void destory() {
		entityTransaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	/**
	 * 同 hibernate 中 Session 的 flush 方法. 
	 */
	@Test
	public void testFlush(){
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
		
		customer.setLastName("AA");
		
		entityManager.flush();
	}
	
	/**
	 * 同 hibernate 中 Session 的 refresh 方法.
	 * 会执行两次查询
	 */
	@Test
	public void testRefresh(){
		Customer customer = entityManager.find(Customer.class, 1);
		customer = entityManager.find(Customer.class, 1);
		
		entityManager.refresh(customer);
	}
}
