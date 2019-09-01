package com.atguigu.springdata.dao.zdy;

import com.atguigu.springdata.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonZdyRepsotory extends JpaRepository<Person, Integer>,
        JpaSpecificationExecutor<Person>,
        PersonDao {

}
 