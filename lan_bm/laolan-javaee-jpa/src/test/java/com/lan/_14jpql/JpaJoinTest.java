package com.lan._14jpql;

import com.lan.po.dan_one2many_Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaJoinTest {

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
	 * JPQL 的关联查询同 HQL 的关联查询. 
	 */
	@Test
	public void testLeftOuterJoinFetch(){
		String jpql = "FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = ?";
		
		dan_one2many_Customer customer =
				(dan_one2many_Customer) entityManager.createQuery(jpql).setParameter(1, 11).getSingleResult();
		System.out.println(customer.getLastName());
		System.out.println(customer.getOrders().size());
		
//		List<Object[]> result = entityManager.createQuery(jpql).setParameter(1, 12).getResultList();
//		System.out.println(result);
	}
}
