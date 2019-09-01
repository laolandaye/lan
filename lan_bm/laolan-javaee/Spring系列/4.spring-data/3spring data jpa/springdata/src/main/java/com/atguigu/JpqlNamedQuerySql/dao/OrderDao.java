package com.atguigu.JpqlNamedQuerySql.dao;

import com.atguigu.JpqlNamedQuerySql.pojo.Customer;
import com.atguigu.JpqlNamedQuerySql.pojo.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {

    // jpql 查询
    @Query(value = " SELECT o FROM Order o  WHERE o.customer = (SELECT c FROM Customer c WHERE c.lastName = ?1 ) ")
    public List<Order> testSubQuery(String lastName);

    @Query(value = " FROM Order o ")
    public Page<Order> testPageLeftWhere(Pageable pageable);


    //sql 分页(不推荐)
    @Deprecated
    @Query(value = " SELECT * FROM jpa_orders where CUSTOMER_ID like concat('%', ?1, '%') ORDER BY ?#{#pageable} ",
            countQuery = " SELECT count(1) FROM jpa_orders where CUSTOMER_ID like concat('%', ?1, '%') "
            ,nativeQuery = true)
    public Page<Order> testPageLeftWhere(String customerId, Pageable pageable);

    //sql 分页(不推荐)
    @Deprecated
    @Query(value = " SELECT id,order_name as orderName FROM jpa_orders where CUSTOMER_ID like concat('%', ?1, '%') ORDER BY ?#{#pageable} ",
            countQuery = " SELECT count(1) FROM jpa_orders where CUSTOMER_ID like concat('%', ?1, '%') "
            ,nativeQuery = true)
    public Page<Map<String, Object>> testPageLeftWhere2(String customerId, Pageable pageable);

}
 