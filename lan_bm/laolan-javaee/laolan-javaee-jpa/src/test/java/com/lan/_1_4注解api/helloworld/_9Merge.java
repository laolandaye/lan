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
 * 9.EntityManager的Merge.docx
 */
public class _9Merge {

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
	 * 总的来说: 类似于 hibernate Session 的 saveOrUpdate 方法.
	 */
	//1. 若传入的是一个临时对象
	//会创建一个新的对象, 把临时对象的属性复制到新的对象中, 然后对新的对象执行持久化操作. 所以
	//新的对象中有 id, 但以前的临时对象中没有 id. 
	@Test
	public void testMerge1(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("cc@163.com");
		customer.setLastName("CC");

		Customer customer2 = entityManager.merge(customer);

		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}

	//若传入的是一个游离对象, 即传入的对象有 OID. 
	//1. 若在 EntityManager 缓存中没有该对象
	//2. 若在数据库中也没有对应的记录
	//3. JPA 会创建一个新的对象, 然后把当前游离对象的属性复制到新创建的对象中
	//4. 对新创建的对象执行 insert 操作. 
	@Test
	public void testMerge2(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD");

		customer.setId(100);//oid

		Customer customer2 = entityManager.merge(customer);

		System.out.println("customer#id:" + customer.getId());
		System.out.println("customer2#id:" + customer2.getId());
	}

	//若传入的是一个游离对象, 即传入的对象有 OID. 
	//1. 若在 EntityManager 缓存中没有该对象
	//2. 若在数据库中也有对应的记录
	//3. JPA 会查询对应的记录, 然后返回该记录对一个的对象, 再然后会把游离对象的属性复制到查询到的对象中.
	//4. 对查询到的对象执行 update 操作. 
	@Test
	public void testMerge3(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("ee@163.com");
		customer.setLastName("EE");

		customer.setId(5);

		Customer customer2 = entityManager.merge(customer);

		System.out.println(customer == customer2); //false
	}

	//若传入的是一个游离对象, 即传入的对象有 OID. 
	//1. 若在 EntityManager 缓存中有对应的对象
	//2. JPA 会把游离对象的属性复制到查询到EntityManager 缓存中的对象中.
	//3. EntityManager 缓存中的对象执行 UPDATE. 
	@Test
	public void testMerge4(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("dd@163.com");
		customer.setLastName("DD");

		customer.setId(5);
//		dan_many2one_Customer customer2 = entityManager.find(dan_many2one_Customer.class, 5);

		entityManager.merge(customer);

//		System.out.println(customer == customer2); //false
	}
}
