package com.lan._13映射;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.lan.po.duo_one2many_Customer;
import com.lan.po.duo_one2many_Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Duo_one2many_Test {

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

	//若是双向 1-n 的关联关系, 执行保存时
	//1.若先保存 n 的一端, 再保存 1 的一端, 默认情况下, 会多出 n 条 UPDATE 语句.
	//2.若先保存 1 的一端,再保存 n 的一端, 则会多出 n*2 条 UPDATE 语句
	//3.在进行双向 1-n 关联关系时, 建议使用 n 的一方来维护关联关系, 而 1 的一方不维护关联系, 这样会有效的减少 SQL 语句. 
	//注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 
	
	//若是双向 1-n 的关联关系, 执行保存时
	//1.若先保存 n 的一端, 再保存 1 的一端, 默认情况下, 会多出 n 条 UPDATE 语句.
	//	1.1 先插入customer，得到id，此时customer_id有值，插入order
	//	1.2 只需要维护多方外键关联关系，
	@Test
	public void testOneToManyPersist1(){//先n再一
		duo_one2many_Customer customer = new duo_one2many_Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");

		duo_one2many_Order order1 = new duo_one2many_Order();
		order1.setOrderName("O-MM-1");

		duo_one2many_Order order2 = new duo_one2many_Order();
		order2.setOrderName("O-MM-2");

		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作
		entityManager.persist(customer);

		entityManager.persist(order1);
		entityManager.persist(order2);
	}
	
	//2.若先保存 1 的一端,再保存 n 的一端, 则会多出 n 条 UPDATE 语句
	//	2.1 先插入order，此时customer_id为空，再插入customer，得到id
	//	2.2先维护多方外键关联关系，
	//	2.3由于双向维护，再维护一方外键关系，实际是不需要的
	@Test
	public void testOneToManyPersist2(){//先n再一
		duo_one2many_Customer customer = new duo_one2many_Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");

		duo_one2many_Order order1 = new duo_one2many_Order();
		order1.setOrderName("O-MM-1");

		duo_one2many_Order order2 = new duo_one2many_Order();
		order2.setOrderName("O-MM-2");

		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作
		entityManager.persist(order1);
		entityManager.persist(order2);
		
		entityManager.persist(customer);
	}
	
	//3.在进行双向 1-n 关联关系时, 建议使用 n 的一方来维护关联关系, 而 1 的一方不维护关联系, 这样会有效的减少 SQL 语句. 
	//	注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 
	// @OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE},mappedBy="customer")
	@Test
	public void testOneToManyPersist3(){
		duo_one2many_Customer customer = new duo_one2many_Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");

		duo_one2many_Order order1 = new duo_one2many_Order();
		order1.setOrderName("O-MM-1");

		duo_one2many_Order order2 = new duo_one2many_Order();
		order2.setOrderName("O-MM-2");

		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作
		entityManager.persist(customer);
		
		entityManager.persist(order1);
		entityManager.persist(order2);
	}

	//默认对关联的多的一方使用懒加载的加载策略. 
	//可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	public void testOneToManyFind(){
		duo_one2many_Customer customer = entityManager.find(duo_one2many_Customer.class, 3);
		System.out.println(customer.getLastName());

		System.out.println(customer.getOrders().size());
	}

	//默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除. 
	//可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略. 
	@Test
	public void testOneToManyRemove(){
		duo_one2many_Customer customer = entityManager.find(duo_one2many_Customer.class, 1);
		entityManager.remove(customer);
	}

	@Test
	public void testUpdate(){
		duo_one2many_Customer customer = entityManager.find(duo_one2many_Customer.class, 3);

		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}
}
