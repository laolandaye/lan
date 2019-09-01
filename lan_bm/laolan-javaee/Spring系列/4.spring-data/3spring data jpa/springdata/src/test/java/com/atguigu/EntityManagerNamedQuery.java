package com.atguigu;

import com.atguigu.JpqlNamedQuerySql.pojo.Customer;
import com.atguigu.JpqlNamedQuerySql.pojo.Order;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class EntityManagerNamedQuery {

    @Autowired
    private EntityManager entityManager;

    //类jpa
    @Test
    @Transactional
    public void test() {
        Query query = entityManager.createNamedQuery("testNamedQuery");
        query.setParameter(1, 3);
        Customer customer = (Customer) query.getSingleResult();

        System.out.println(customer);
    }

}
