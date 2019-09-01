package com.lan._13映射;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.lan.po.duo_many2many_Category;
import com.lan.po.duo_many2many_Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Duo_many2many_Test {

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

	//多对所的保存
	@Test
	public void testManyToManyPersist(){
		duo_many2many_Item i1 = new duo_many2many_Item();
		i1.setItemName("i-1");

		duo_many2many_Item i2 = new duo_many2many_Item();
		i2.setItemName("i-2");

		duo_many2many_Category c1 = new duo_many2many_Category();
		c1.setCategoryName("C-1");

		duo_many2many_Category c2 = new duo_many2many_Category();
		c2.setCategoryName("C-2");

		//设置关联关系
		i1.getCategories().add(c1);
		i1.getCategories().add(c2);

		i2.getCategories().add(c1);
		i2.getCategories().add(c2);

		c1.getItems().add(i1);
		c1.getItems().add(i2);

		c2.getItems().add(i1);
		c2.getItems().add(i2);

		//执行保存
		entityManager.persist(i1);
		entityManager.persist(i2);
		entityManager.persist(c1);
		entityManager.persist(c2);
	}
	
	//对于关联的集合对象, 默认使用懒加载的策略.
	//使用维护关联关系的一方获取, 还是使用不维护关联关系的一方获取, SQL 语句相同. 
	@Test
	public void testManyToManyFind(){
//		duo_many2many_Item item = entityManager.find(duo_many2many_Item.class, 1);
//		System.out.println(item.getItemName());
//		
//		System.out.println(item.getCategories().size());
		
		duo_many2many_Category category = entityManager.find(duo_many2many_Category.class, 2);
		System.out.println(category.getCategoryName());
		System.out.println(category.getItems().size());
	}
}
