package com.atguigu.springdata.dao.repository;

import com.atguigu.springdata.dao.repository.PersonRepsotory;
import com.atguigu.springdata.pojo.Person;
import com.atguigu.springdata.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class RepsotoryAnnotationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepsotory personRepsotory;

    //1.测试数据库连通性
    @Test
    public void testDataSource(){
    }

    //2.测试pojo
    @Test
    public void testJpaPojo(){
    }

    //为 @Query 注解传递参数的方式1: 使用占位符.
    @Test
    public void testQueryAnnotationParams1(){
        List<Person> persons = personRepsotory.testQueryAnnotationParams1("AA", "aa@atguigu.com");
        System.out.println(persons);
    }

    @Test
    public void testModifying(){
        Integer i = personService.updatePersonEmail("mmmm@atguigu.com", 1);
        System.out.println(i);
    }

}
