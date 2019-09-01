package com.lan._14jpql;

import com.lan.po.Customer;
import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

public class JpaOrderByTest {

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
	public void testOrderBy(){
		String jpql = "FROM Customer c WHERE c.age > ? ORDER BY c.age DESC";
		Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
		
		//占位符的索引是从 1 开始
		query.setParameter(1, 4);
		List<Customer> customers = query.getResultList();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
	
}
