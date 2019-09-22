package com.lan._13映射;

import com.lan.po.dan_one2many_Customer;
import com.lan.po.dan_one2many_Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class Dan_one2many_Test {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	@Before
	public void init(){
		entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test1");
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		transaction.begin();
	}

	@After
	public void destroy(){
		transaction.commit();
		entityManager.close();
		entityManagerFactory.close();
	}

	//单向 1-n 关联关系执行保存时, 一定会多出 UPDATE 语句.
	//因为 n 的一端在插入时不会同时插入外键列. 
	@Test
	public void testOneToManyPersist(){
		dan_one2many_Customer customer = new dan_one2many_Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");

		dan_one2many_Order order1 = new dan_one2many_Order();
		order1.setOrderName("O-MM-1");

		dan_one2many_Order order2 = new dan_one2many_Order();
		order2.setOrderName("O-MM-2");

		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		//执行保存操作
		entityManager.persist(customer);

		entityManager.persist(order1);
		entityManager.persist(order2);
	}

	//默认对关联的多的一方使用懒加载的加载策略. 
	//可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	public void testOneToManyFind(){
		dan_one2many_Customer customer = entityManager.find(dan_one2many_Customer.class, 3);
		System.out.println(customer.getLastName());

		System.out.println(customer.getOrders().size());
	}

	//默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除. 
	//可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略. 
	@Test
	public void testOneToManyRemove(){
		dan_one2many_Customer customer = entityManager.find(dan_one2many_Customer.class, 1);
		entityManager.remove(customer);
	}
	
	@Test
	public void testUpdate(){
		dan_one2many_Customer customer = entityManager.find(dan_one2many_Customer.class, 3);
		
		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}
}
