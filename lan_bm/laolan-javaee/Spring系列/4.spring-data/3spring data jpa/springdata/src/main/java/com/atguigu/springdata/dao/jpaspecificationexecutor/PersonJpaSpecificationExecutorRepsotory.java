package com.atguigu.springdata.dao.jpaspecificationexecutor;

import com.atguigu.springdata.pojo.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonJpaSpecificationExecutorRepsotory extends JpaRepository<Person, Integer>,
        JpaSpecificationExecutor<Person> {

}
 