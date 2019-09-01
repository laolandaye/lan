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
public class EntityManagerSql {

    @Autowired
    private EntityManager entityManager;

    //createNativeQuery 适用于本地 SQL
    @Test
    public void test() {

    }

    //查询参数 ？或者 ：
    @Test
    public void testWenHao() {
        String sql = "SELECT age FROM jpa_cutomers WHERE id = ?1 ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, 3);

        Object result = query.getSingleResult();
        System.out.println(result);
    }

    @Test
    public void testMaoHao() {
        String sql = "SELECT * FROM jpa_cutomers WHERE id = :id ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", 3);

        Object result = query.getSingleResult();
        System.out.println(result);
    }

    //返回结果 单个， 一组，  多组
    //返回结果 单个
    @Test
    public void testGetSimpleOne() {
        String sql = "SELECT age FROM jpa_cutomers WHERE id = :id ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", 3);

        Object result = query.getSingleResult();
        System.out.println(result);
    }

    @Test
    public void testGetObjone() {
        String sql = "SELECT * FROM jpa_cutomers WHERE id = :id ";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("id", 3);

        Object result = query.getSingleResult();
        System.out.println(result);
    }

    @Test
    @Transactional
    public void testGetObjs() {
        String sql = "SELECT id, last_name as lastName FROM jpa_cutomers ";
        Query query = entityManager.createNativeQuery(sql);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(Customer.class));
        List<Customer> result2 = query.getResultList();
        for (Customer customer : result2) {
            System.out.println(customer);
        }
    }

    @Test
    @Transactional
    public void testGetMaps() {
        Query query = null;
        String sql = "SELECT last_name AS lastName FROM jpa_cutomers";
        sql += "  where id = ?1  ";

        query = entityManager.createNativeQuery(sql);
        query.setParameter(1, 1);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> result2 = query.getResultList();
        for (Map<String, Object> customer : result2) {
            System.out.println(customer);
        }
    }

    // 测试是否能级联查询，类似jpql
    @Test
    @Transactional
    public void testGetMaps2() {
        Query query = null;
        String sql = "SELECT * FROM jpa_cutomers";

        query = entityManager.createNativeQuery(sql);

        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String, Object>> result2 = query.getResultList();
        for (Map<String, Object> customer : result2) {
            System.out.println(customer);
        }
    }

    // 多表，分页，where （此方式实现分页跨平台）
    @Test
    @Transactional
    public void testPageLeftWhere() {
        Pageable pageable = new PageRequest(2, 5);

        StringBuffer sqlCount = new StringBuffer(" SELECT count(1) ");
        StringBuffer sqlList = new StringBuffer(" SELECT id,order_name as orderName ");


        StringBuffer sql = new StringBuffer(" FROM jpa_orders ");

        //1. 计算总条数 和 list 的where
        if(1 == 1){
            sql.append("  where CUSTOMER_ID like concat('%', ?1, '%')  ");
        }
        sqlCount.append(sql);
        sqlList.append(sql);

        //2. 计算list的order


        //3. 处理where查询条件
        Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
        Query queryList = entityManager.createNativeQuery(sqlList.toString());
        queryList.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(Order.class));
        queryList.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        queryList.setMaxResults(pageable.getPageSize());

        if(1 == 1){
            queryCount.setParameter(1, "3");
            queryList.setParameter(1, "3");
        }

        //4.封装spring 分页
        Long count = Long.parseLong(queryCount.getSingleResult().toString());
        List<Order> list= queryList.getResultList();

        Page<Order> pag = new PageImpl(list, pageable , count);
        System.out.println(pag.getNumber());
        for (int i = 0; i < pag.getContent().size(); i++) {
            System.out.println(pag.getContent().get(i));
        }
    }

    // 多表，分页，where （此方式实现分页跨平台）
    @Test
    @Transactional
    public void testPageLeftWhere2() {
        Pageable pageable = new PageRequest(2, 5);

        StringBuffer sqlCount = new StringBuffer(" SELECT count(1) ");
        StringBuffer sqlList = new StringBuffer(" SELECT id,order_name as orderName ");


        StringBuffer sql = new StringBuffer(" FROM jpa_orders ");

        //1. 计算总条数 和 list 的where
        if(1 == 1){
            sql.append("  where CUSTOMER_ID like concat('%', ?1, '%')  ");
        }
        sqlCount.append(sql);
        sqlList.append(sql);

        //2. 计算list的order


        //3. 处理where查询条件
        Query queryCount = entityManager.createNativeQuery(sqlCount.toString());
        Query queryList = entityManager.createNativeQuery(sqlList.toString());
        queryList.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        queryList.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
        queryList.setMaxResults(pageable.getPageSize());

        if(1 == 1){
            queryCount.setParameter(1, "3");
            queryList.setParameter(1, "3");
        }

        //4.封装spring 分页
        Long count = Long.parseLong(queryCount.getSingleResult().toString());
        List<Map<String, Object>> list= queryList.getResultList();

        Page<Map<String, Object>> pag = new PageImpl(list, pageable , count);
        System.out.println(pag.getNumber());
        for (int i = 0; i < pag.getContent().size(); i++) {
            System.out.println(pag.getContent().get(i));
        }
    }

}
