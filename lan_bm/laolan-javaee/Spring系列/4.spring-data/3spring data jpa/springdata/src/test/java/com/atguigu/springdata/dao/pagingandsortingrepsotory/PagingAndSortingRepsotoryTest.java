package com.atguigu.springdata.dao.pagingandsortingrepsotory;

import com.atguigu.springdata.dao.pagingandsortingrepsotory.PersonPagingAndSortingRepsotory;
import com.atguigu.springdata.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

public class PagingAndSortingRepsotoryTest {

	private ApplicationContext ctx = null;
	private PersonPagingAndSortingRepsotory personPagingAndSortingRepsotory = null;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personPagingAndSortingRepsotory = ctx.getBean(PersonPagingAndSortingRepsotory.class);
	}

    //分页不排序
	@Test
	public void testPagingAndSortingRespository(){
		int pageNo = 6 - 1;
		int pageSize = 5;

		PageRequest pageable = new PageRequest(pageNo, pageSize);
		Page<Person> page = personPagingAndSortingRepsotory.findAll(pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面记录数: " + page.getNumberOfElements());
	}

	//分页排序
	@Test
	public void testPagingAndSortingRespository2(){
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		List<Person> page = (List<Person>) personPagingAndSortingRepsotory.findAll(sort);
		for (Person person : page) {
			System.out.println(person);
		}
	}

	//分页排序
	@Test
	public void testPagingAndSortingRespository3(){
		int pageNo = 6 - 1;
		int pageSize = 5;

		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = personPagingAndSortingRepsotory.findAll(pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面记录数: " + page.getNumberOfElements());
	}

}
