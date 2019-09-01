package com.atguigu.springdata.dao.zdy;

import com.atguigu.springdata.pojo.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonZdyRepsotoryImpl implements PersonDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void test() {
		Person person = entityManager.find(Person.class, 11);
		System.out.println("-->" + person);
	}

}
