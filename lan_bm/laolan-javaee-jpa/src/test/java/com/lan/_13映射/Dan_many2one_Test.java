package com.lan._13映射;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.lan.po.dan_many2one_Customer;
import com.lan.po.dan_many2one_Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Dan_many2one_Test {

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

	/**
	 * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
	 */
	@Test
	public void testManyToOnePersist(){
		dan_many2one_Customer customer = new dan_many2one_Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("gg@163.com");
		customer.setLastName("GG");

		dan_many2one_Order order1 = new dan_many2one_Order();
		order1.setOrderName("G-GG-1");
		dan_many2one_Order order2 = new dan_many2one_Order();
		order2.setOrderName("G-GG-2");

		//设置关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作
		entityManager.persist(order1);
		entityManager.persist(order2);

		entityManager.persist(customer);
	}

	/**
	 * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
	 */
	@Test
	public void testManyToOnePersist2(){
		dan_many2one_Customer customer = new dan_many2one_Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("gg@163.com");
		customer.setLastName("GG");

		dan_many2one_Order order1 = new dan_many2one_Order();
		order1.setOrderName("G-GG-1");
		dan_many2one_Order order2 = new dan_many2one_Order();
		order2.setOrderName("G-GG-2");

		//设置关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作(先一再多)
		entityManager.persist(customer);

		entityManager.persist(order1);
		entityManager.persist(order2);

	}

	//默认情况下, 使用左外连接的方式来获取 n 的一端的对象和其关联的 1 的一端的对象. 
	//可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@Test
	public void testManyToOneFind(){
		dan_many2one_Order order = entityManager.find(dan_many2one_Order.class, 1);
		System.out.println(order.getOrderName());

		System.out.println(order.getCustomer().getLastName());
	}

	//不能直接删除 1 的一端, 因为有外键约束. 
	@Test
	public void testManyToOneRemove(){

		dan_many2one_Order order = entityManager.find(dan_many2one_Order.class, 1);
		entityManager.remove(order);
		
//		不能删除1的一方
//		dan_one2many_Customer customer = entityManager.find(dan_one2many_Customer.class, 1);
//		entityManager.remove(customer);
	}
	
	@Test
	public void testManyToOneUpdate(){
		dan_many2one_Order order = entityManager.find(dan_many2one_Order.class, 2);
		order.getCustomer().setLastName("FFF");
	}
}
