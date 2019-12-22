package com.atguigu.mp.test;

import com.atguigu.mp.beans.Employee;
import com.atguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDaoCrud {
	private ApplicationContext ioc = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmployeeMapper employeeMapper = 
			ioc.getBean("employeeMapper",EmployeeMapper.class);
	
	/**
	 * 通用 删除操作
	 */
	@Test
	public void testCommonDelete() {
		deleteById();	//1 .根据id进行删除
		deleteByMap();	//2. 根据 条件进行删除
		deleteBatchIds();	//3. 批量删除
	}
	private void deleteById() {
		Integer result = employeeMapper.deleteById(13);
		System.out.println("result: " + result );
	}
	private void deleteByMap() {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("last_name", "MP");
		columnMap.put("email", "mp@atguigu.com");
		Integer result = employeeMapper.deleteByMap(columnMap);
		System.out.println("result: " + result );
	}
	private void deleteBatchIds() {
		List<Integer> idList = new ArrayList<>();
		idList.add(3);
		idList.add(4);
		idList.add(5);
		Integer result = employeeMapper.deleteBatchIds(idList);
		System.out.println("result: " + result );
	}
	
	/**
	 * 通用 查询操作
	 */
	@Test
	public void  testCommonSelect() {
		selectById();	//1. 通过id查询
		selectOne();	//2. 通过多个列进行查询    id  +  lastName
		selectBatchIds();	//3. 通过多个id进行查询    <foreach>
		selectByMap();	//4. 通过Map封装条件查询
		selectPage();	//5. 分页查询
	}

	private void selectById() {
		Employee employee = employeeMapper.selectById(7);
		System.out.println(employee);
	}
	private void selectOne() {
		Employee  employee = new Employee();
		//employee.setId(7);
		employee.setLastName("小泽老师");
		employee.setGender(0);

		Employee result = employeeMapper.selectOne(employee);
		System.out.println("result: " +result );
	}
	private void selectBatchIds() {
		List<Integer> idList = new ArrayList<>();
		idList.add(4);
		idList.add(5);
		idList.add(6);
		idList.add(7);
		List<Employee> emps = employeeMapper.selectBatchIds(idList);
		System.out.println(emps);
	}
	private void selectByMap() {
		Map<String,Object> columnMap = new HashMap<>();
		columnMap.put("last_name", "Tom");
		columnMap.put("gender", 1);

		List<Employee> emps = employeeMapper.selectByMap(columnMap);
		System.out.println(emps);
	}
	private void selectPage() {
		List<Employee> emps = employeeMapper.selectPage(new Page<>(3, 2), null);
		System.out.println(emps);
	}
	
	/**
	 * 通用 更新操作
	 */
	@Test
	public void testCommonUpdate() {
//		updateById();
		updateAllColumnById();
	}

	private void updateById() {
		//初始化修改对象
		Employee employee = new Employee();
		employee.setId(7);
		employee.setLastName("小泽老师");
		employee.setEmail("xz@sina.com");
		employee.setGender(0);
		employee.setAge(33);

		Integer result = employeeMapper.updateById(employee);

		System.out.println("result: " + result );
	}

	private void updateAllColumnById() {
		//初始化修改对象
		Employee employee = new Employee();
		employee.setId(7);
		employee.setLastName("小泽老师");
		employee.setEmail("xz@sina.com");
		employee.setGender(0);
		//employee.setAge(33);

		//Integer result = employeeMapper.updateById(employee);
		Integer result = employeeMapper.updateAllColumnById(employee);

		System.out.println("result: " + result );
	}


	/**
	 * 通用 插入操作
	 */
	@Test
	public void testCommonInsert() {
//		insert();
		insertAllColumn();
	}

	private void insert() {
		//初始化Employee对象
		Employee employee  = new Employee();
		employee.setLastName("MP");
		employee.setEmail("mp@atguigu.com");
		employee.setGender(1);
		employee.setAge(22);
		//插入到数据库
		Integer result = employeeMapper.insert(employee);
		System.out.println("result: " + result );
		//获取当前数据在数据库中的主键值
		Integer key = employee.getId();
		System.out.println("key:" + key );
	}

	private void insertAllColumn() {
		//初始化Employee对象
		Employee employee  = new Employee();
		employee.setLastName("MP");
		employee.setEmail("mp@atguigu.com");
		//employee.setGender(1);
		//employee.setAge(22);
		employee.setSalary(20000.0);
		//插入到数据库
		// insert方法在插入时， 会根据实体类的每个属性进行非空判断，只有非空的属性对应的字段才会出现到SQL语句中

		//insertAllColumn方法在插入时， 不管属性是否非空， 属性所对应的字段都会出现到SQL语句中.
		Integer result = employeeMapper.insertAllColumn(employee);

		System.out.println("result: " + result );

		//获取当前数据在数据库中的主键值
		Integer key = employee.getId();
		System.out.println("key:" + key );
	}
	
	
}
