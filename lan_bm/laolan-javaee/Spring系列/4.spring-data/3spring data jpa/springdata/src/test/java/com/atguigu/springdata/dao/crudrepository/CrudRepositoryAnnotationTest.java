package com.atguigu.springdata.dao.crudrepository;

import com.atguigu.springdata.pojo.Person;
import com.atguigu.springdata.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class CrudRepositoryAnnotationTest {

	@Autowired
	private PersonService personService;

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
