package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoO2mCustomer;
import com.atguigu.stardar.pojo.DuoO2mOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class DuoOne2ManyTest {

	@Autowired
	private DuoO2mCustomerDao duoO2mCustomerDao;

	@Autowired
	private DuoO2mOrderDao duoO2mOrderDao;


	//若是双向 1-n 的关联关系, 执行保存时
	//1.若先保存 n 的一端, 再保存 1 的一端, 默认情况下, 会多出 n 条 UPDATE 语句.
	//2.若先保存 1 的一端,再保存 n 的一端, 则会多出 n*2 条 UPDATE 语句
	//3.在进行双向 1-n 关联关系时, 建议使用 n 的一方来维护关联关系, 而 1 的一方不维护关联系, 这样会有效的减少 SQL 语句. 
	//注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 
	
	//若是双向 1-n 的关联关系, 执行保存时
	//1.若先保存 n 的一端, 再保存 1 的一端, 默认情况下, 会多出 n 条 UPDATE 语句.
	//	1.1 先插入customer，得到id，此时customer_id有值，插入order
	//	1.2 只需要维护多方外键关联关系，
	//先一再n
	@Test
	public void testOneToManyPersist1(){
		DuoO2mCustomer customer = new DuoO2mCustomer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");

		DuoO2mOrder order1 = new DuoO2mOrder();
		order1.setOrderName("O-MM-1");

		DuoO2mOrder order2 = new DuoO2mOrder();
		order2.setOrderName("O-MM-2");

		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作
		duoO2mCustomerDao.save(customer);

		duoO2mOrderDao.save(order1);
		duoO2mOrderDao.save(order2);
	}

	// //先n再一
	//2.若先保存 1 的一端,再保存 n 的一端, 则会多出 n 条 UPDATE 语句
	//	2.1 先插入order，此时customer_id为空，再插入customer，得到id
	//	2.2先维护多方外键关联关系，
	//	2.3由于双向维护，再维护一方外键关系，实际是不需要的
	@Test
	@Deprecated
	public void testOneToManyPersist2(){

	}
	
	//3.在进行双向 1-n 关联关系时, 建议使用 n 的一方来维护关联关系, 而 1 的一方不维护关联系, 这样会有效的减少 SQL 语句. 
	//	注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 
	// @OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE},mappedBy="customer")
	@Test
	public void testOneToManyPersist3(){
		DuoO2mCustomer customer = new DuoO2mCustomer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");

		DuoO2mOrder order1 = new DuoO2mOrder();
		order1.setOrderName("O-MM-1");

		DuoO2mOrder order2 = new DuoO2mOrder();
		order2.setOrderName("O-MM-2");

		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);

		order1.setCustomer(customer);
		order2.setCustomer(customer);

		//执行保存操作
		duoO2mCustomerDao.save(customer);

		duoO2mOrderDao.save(order1);
		duoO2mOrderDao.save(order2);
	}

	//默认对关联的多的一方使用懒加载的加载策略. 
	//可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	public void testOneToManyFind(){
		DuoO2mCustomer customer = duoO2mCustomerDao.findOne(3);
		System.out.println(customer.getLastName());

		System.out.println(customer.getOrders().size());
	}

	//默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除. 
	//可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略. 
	@Test
	public void testOneToManyRemove(){
		DuoO2mCustomer customer = duoO2mCustomerDao.findOne(80);
		duoO2mCustomerDao.delete(customer);
	}

	@Test
	public void testUpdate(){
		DuoO2mCustomer customer = duoO2mCustomerDao.findOne( 3);

		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}
}
