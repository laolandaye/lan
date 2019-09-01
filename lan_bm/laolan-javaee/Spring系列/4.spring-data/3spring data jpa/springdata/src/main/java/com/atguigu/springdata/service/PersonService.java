package com.atguigu.springdata.service;

import com.atguigu.springdata.dao.crudrepository.PersonCrudRepsotory;
import com.atguigu.springdata.dao.repository.PersonRepsotory;
import com.atguigu.springdata.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

	@Autowired
	private PersonRepsotory personRepsotory;

	@Autowired
	private PersonCrudRepsotory personCrudRepsotory;
	
	@Transactional
	public Integer updatePersonEmail(String email, Integer id){
		return personRepsotory.updatePersonEmail(id, email);
	}

	@Transactional
	public void savePersons(List<Person> persons){
		personCrudRepsotory.save(persons);
	}
}
