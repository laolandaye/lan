package com.atguigu.springdata.dao.repository;

import com.atguigu.springdata.dao.repository.PersonRepsotory;
import com.atguigu.springdata.pojo.Person;
import com.atguigu.springdata.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RepsotoryTest {

	private ApplicationContext ctx = null;
	private PersonRepsotory personRepsotory = null;
	private PersonService personService;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepsotory = ctx.getBean(PersonRepsotory.class);
		personService = ctx.getBean(PersonService.class);
	}

	//1.测试数据库连通性
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}


	//2.测试pojo
	@Test
	public void testJpaPojo(){

	}

	@Test
	public void testHelloWorldSpringData() {
//		PersonRepsotory personRepsotory = ctx.getBean(PersonRepsotory.class);
		Person person = personRepsotory.getByLastName("AA");
		System.out.println(person);
	}


	@Test
	public void testKeyWords(){
		List<Person> persons = personRepsotory.getByLastNameStartingWithAndIdLessThan("X", 10);
		System.out.println(persons);

		persons = personRepsotory.getByLastNameEndingWithAndIdLessThan("X", 10);
		System.out.println(persons);

		persons = personRepsotory.getByEmailInAndBirthLessThan(Arrays.asList("AA@atguigu.com", "FF@atguigu.com",
				"SS@atguigu.com"), new Date());
		System.out.println(persons.size());
	}

	@Test
	public void testKeyWords2(){
		List<Person> persons = personRepsotory.getByAddress_IdGreaterThan(1);
		System.out.println(persons);
	}

	//使用 @Query 注解可以自定义 JPQL 语句以实现更灵活的查询
	@Test
	public void testQueryAnnotation(){
		Person person = personRepsotory.getMaxIdPerson();
		System.out.println(person);
	}

	//为 @Query 注解传递参数的方式1: 使用占位符.
	@Test
	public void testQueryAnnotationParams1(){
		List<Person> persons = personRepsotory.testQueryAnnotationParams1("AA", "aa@atguigu.com");
		System.out.println(persons);
	}

	//为 @Query 注解传递参数的方式2: 命名参数的方式.
	@Test
	public void testQueryAnnotationParams2(){
		List<Person> persons = personRepsotory.testQueryAnnotationParams2("aa@atguigu.com", "AA");
		System.out.println(persons);
	}

	//SpringData 允许在占位符上添加 %%.
	@Test
	public void testQueryAnnotationLikeParam(){
//		List<Person> persons = personRepsotory.testQueryAnnotationLikeParam("%A%", "%bb%");
//		System.out.println(persons.size());

//		List<Person> persons = personRepsotory.testQueryAnnotationLikeParam("A", "bb");
//		System.out.println(persons.size());

		List<Person> persons = personRepsotory.testQueryAnnotationLikeParam2("bb", "A");
		System.out.println(persons.size());
	}

	//设置 nativeQuery=true 即可以使用原生的 SQL 查询
	@Test
	public void testNativeQuery(){
		long count = personRepsotory.getTotalCount();
		System.out.println(count);
	}

	@Test
	public void testModifying(){
		Integer i = personService.updatePersonEmail("mmmm@atguigu.com", 1);
		System.out.println(i);
	}

}
