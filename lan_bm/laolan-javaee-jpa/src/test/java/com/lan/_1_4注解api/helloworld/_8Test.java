package com.lan._1_4注解api.helloworld;

import com.lan.po.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * 8.EntityManager实体类操作.docx
 */
public class _8Test {

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

	//类似于 hibernate 中 Session 的 get 方法. 
	@Test
	public void testFind() {
		Customer customer = entityManager.find(Customer.class, 1);
		System.out.println(customer);
	}

	//类似于 hibernate 中 Session 的 load 方法(懒加载)
	@Test
	public void testGetReference(){
		Customer customer = entityManager.getReference(Customer.class, 1);
		System.out.println("-------------------------------------");
		System.out.println(customer);
	}

	//类似于 hibernate 的 save 方法. 使对象由临时状态变为持久化状态. 
	//和 hibernate 的 save 方法的不同之处: 若对象有 id, 则不能执行 insert 操作, 而会抛出异常. 
	@Test
	public void testPersistence(){
		Customer customer = new Customer();
		customer.setAge(15);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("bb@163.com");
		customer.setLastName("BB");
//		customer.setId(100);

		System.out.println(customer);
		entityManager.persist(customer);
		System.out.println(customer.getId());
	}

	//类似于 hibernate 中 Session 的 delete 方法. 把对象对应的记录从数据库中移除
	//但注意: 该方法只能移除 持久化 对象. 而 hibernate 的 delete 方法实际上还可以移除 游离对象.
	@Test
	public void testRemove(){
		Customer customer = entityManager.find(Customer.class, 2);
		entityManager.remove(customer);
	}
}
