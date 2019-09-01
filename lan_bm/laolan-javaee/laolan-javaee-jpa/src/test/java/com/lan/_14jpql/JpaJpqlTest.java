package com.lan._14jpql;

import com.lan.po.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class JpaJpqlTest {

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

	@Test
	public void testHelloJPQL(){
		String jpql = "FROM Customer c WHERE c.age > ?";
		Query query = entityManager.createQuery(jpql);

		//占位符的索引是从 1 开始
		query.setParameter(1, 1);
		List<Customer> customers = query.getResultList();
		System.out.println(customers.size());
	}

	//默认情况下, 若只查询部分属性, 则将返回 Object[] 类型的结果. 或者 Object[] 类型的 List.
	//也可以在实体类中创建对应的构造器, 然后再 JPQL 语句中利用对应的构造器返回实体类的对象.
	@Test
	public void testPartlyProperties(){
		String jpql = "SELECT new Customer(c.lastName, c.age) FROM Customer c WHERE c.id > ?";
		List result = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();

		System.out.println(result);
	}

	//createNamedQuery 适用于在实体类前使用 @NamedQuery 标记的查询语句
	@Test
	public void testNamedQuery(){
		Query query = entityManager.createNamedQuery("testNamedQuery").setParameter(1, 3);
		Customer customer = (Customer) query.getSingleResult();

		System.out.println(customer);
	}

	//createNativeQuery 适用于本地 SQL
	@Test
	public void testNativeQuery(){
		String sql = "SELECT age FROM jpa_cutomers WHERE id = ?";
		Query query = entityManager.createNativeQuery(sql).setParameter(1, 3);

		Object result = query.getSingleResult();
		System.out.println(result);
	}
}
