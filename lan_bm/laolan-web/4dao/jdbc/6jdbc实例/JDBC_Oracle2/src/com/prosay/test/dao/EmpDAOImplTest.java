package com.prosay.test.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.prosay.dao.EmpDAO;
import com.prosay.dao.impl.EmpDAOImpl;
import com.prosay.po.Emp;

public class EmpDAOImplTest {
	EmpDAO ed;
	Emp emp;
	int updateRow;
	// 每一个@Test注解对应的方法执行前，都会执行@Before一次；@After
	@Before
	public void init(){
		ed = new EmpDAOImpl();
		emp = new Emp(8888, "张振武", "developer", 0, new Date(), 9999, 1111, 50);
		updateRow = -1;
	}

	@Test
	public void testAddEmp() {
		try {
			updateRow = ed.addEmp(emp);
			System.out.println("插入成功记录条数" + updateRow);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddBatch() {
		List<Emp> emps = new ArrayList<Emp>();
		emps.add(new Emp(8888, "Andrew", "developer", 0, new Date(), 9999, 1111, 50));
		emps.add(new Emp(9999, "臭猪", "developer", 0, new Date(), 9999, 1111, 50));
		emps.add(new Emp(6666, "长相思", "student", 0, new Date(), 9999, 1111, 50));
		/*emps.add(emp);
		emp.setEname("臭猪");
		emps.add(emp);
		emp.setEname("长相思");
		emps.add(emp);*/
		try {
			updateRow = ed.addBatch(emps);
			System.out.println("批量插入成功记录条数：" + updateRow);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModifyEmp() {
		emp.setSal(10000);
		try {
			updateRow = ed.modifyEmp(emp);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRemoveEmp() {
		try {
			updateRow = ed.removeEmp(8888);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryAll() {
		try {
			List<Emp> list = ed.queryAll();
			System.out.println("==查询所有==");
			showEmps(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示所有员工
	 * @param list	员工集合
	 */
	private void showEmps(List<Emp> list) {
		if (list == null) {
			System.out.println("没有查到任何记录！");
			return;
		}
		for(Emp emp : list){
			System.out.println(emp);
		}
	}

	@Test
	public void testQueryLike() {
		try {
			List<Emp> list = ed.queryLike("S");
			System.out.println("==查询所有名字中含有S的员工==");
			showEmps(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testQueryCombo() {
		try {
			emp.setEname("SMITH");
			emp.setJob("CLERK");
			//emp.setJob("SALESMAN");
			List<Emp> list = ed.queryCombo(emp);
			System.out.println("==组合查询==");
			showEmps(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
