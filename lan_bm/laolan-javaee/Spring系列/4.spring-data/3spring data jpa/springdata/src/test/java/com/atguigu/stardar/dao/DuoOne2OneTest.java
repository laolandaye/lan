package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoO2oDepartment;
import com.atguigu.stardar.pojo.DuoO2oManager;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class DuoOne2OneTest {
	
	@Autowired
	private DuoO2oDepartmentDao duoO2oDepartmentDao;

	@Autowired
	private DuoO2oManagerDao duoO2oManagerDao;

	//双向 1-1 的关联关系, 建议先保存不维护关联关系的一方, 即没有外键的一方, 这样不会多出 UPDATE 语句.
	@Test
	public void testOneToOnePersistence(){
		DuoO2oManager mgr = new DuoO2oManager();
		mgr.setMgrName("M-BB");

		DuoO2oDepartment dept = new DuoO2oDepartment();
		dept.setDeptName("D-BB");

		//设置关联关系
		mgr.setDept(dept);
		dept.setMgr(mgr);

		//执行保存操作
		duoO2oManagerDao.save(mgr);
		duoO2oDepartmentDao.save(dept);



	}

	//1.默认情况下, 若获取维护关联关系的一方, 则会通过左外连接获取其关联的对象. 
	//但可以通过 @OntToOne 的 fetch 属性来修改加载策略.
	@Test
	public void testOneToOneFind(){
		DuoO2oDepartment dept = duoO2oDepartmentDao.findOne(1);
		System.out.println(dept.getDeptName());
		System.out.println(dept.getMgr().getClass().getName());
	}

	//1. 默认情况下, 若获取不维护关联关系的一方, 则也会通过左外连接获取其关联的对象. 
	//可以通过 @OneToOne 的 fetch 属性来修改加载策略. 但依然会再发送 SQL 语句来初始化其关联的对象
	//这说明在不维护关联关系的一方, 不建议修改 fetch 属性. 
	@Test
	public void testOneToOneFind2(){
		DuoO2oManager mgr = duoO2oManagerDao.findOne(1);
		System.out.println(mgr.getMgrName());

		System.out.println(mgr.getDept().getClass().getName());
	}

}
