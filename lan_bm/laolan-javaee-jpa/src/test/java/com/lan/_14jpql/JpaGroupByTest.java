package com.lan._14jpql;

import com.lan.po.dan_many2one_Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaGroupByTest {

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

	//查询 order 数量大于 2 的那些 Customer
	@Test
	public void testGroupBy(){
		String jpql = "SELECT o.customer FROM Order o "
				+ "GROUP BY o.customer "
				+ "HAVING count(o.id) >= 2";
		List<dan_many2one_Customer> customers = entityManager.createQuery(jpql).getResultList();

		System.out.println(customers);
	}
}
