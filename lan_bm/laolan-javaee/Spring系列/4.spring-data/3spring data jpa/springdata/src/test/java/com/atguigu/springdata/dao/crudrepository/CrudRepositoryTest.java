package com.atguigu.springdata.dao.crudrepository;

import com.atguigu.springdata.pojo.Person;
import com.atguigu.springdata.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrudRepositoryTest {

	private ApplicationContext ctx = null;
	private PersonCrudRepsotory personCrudRepsotory = null;
	private PersonService personService;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personCrudRepsotory = ctx.getBean(PersonCrudRepsotory.class);
		personService = ctx.getBean(PersonService.class);
	}


	@Test
	public void testCrudReposiory(){
		List<Person> persons = new ArrayList<>();

		for(int i = 'a'; i <= 'z'; i++){
			Person person = new Person();
			person.setAddressId(i + 1);
			person.setBirth(new Date());
			person.setEmail((char)i + "" + (char)i + "@atguigu.com");
			person.setLastName((char)i + "" + (char)i);

			persons.add(person);
		}

		personService.savePersons(persons);
	}

}
