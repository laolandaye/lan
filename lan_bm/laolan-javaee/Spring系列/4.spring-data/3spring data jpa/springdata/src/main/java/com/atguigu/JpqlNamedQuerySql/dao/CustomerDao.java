package com.atguigu.JpqlNamedQuerySql.dao;

import com.atguigu.JpqlNamedQuerySql.pojo.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface CustomerDao extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    // JPQL 写法
    @Query(value = " FROM Customer c ")
    public List<Customer> testAllCol();

    @Query(value = " SELECT new Customer(c.lastName, c.age) FROM Customer c ")
    public List<Customer> testSomeCol();

    @Query(value = " FROM Customer c WHERE c.age > :age ")
    public List<Customer> testWhere(@Param(value = "age") int age);

    @Query(value = " FROM Customer c WHERE c.age BETWEEN :minAge AND :maxAge")
    public List<Customer> testWhereBetween(@Param(value = "minAge") int minAge, @Param(value = "maxAge") int maxAge);

    @Query(value = " FROM Customer c WHERE c.email LIKE :email")
    public List<Customer> testWhereLike1(@Param(value = "email") String email);

    @Query(value = " FROM Customer c WHERE c.email LIKE :email")
    public List<Customer> testWhereLike2(@Param(value = "email") String email);

    @Query(value = " FROM Customer c WHERE c.lastName IN (:lastNameStr)")
    public List<Customer> testWhereIn1(@Param(value = "lastNameStr") String lastNameStr);

    @Query(value = " FROM Customer c WHERE c.lastName IN (:lastNames)")
    public List<Customer> testWhereIn2(@Param(value = "lastNames") List<String> lastNames);

   @Query(value = " FROM Customer c WHERE c.email IS NULL")
    public List<Customer> testWhereIsNull();

    //testExecuteUpdate() 暂时写不来

    @Query(value = " FROM Customer c WHERE c.age > :age ORDER BY c.age DESC ")
    public List<Customer> testOrderBy(@Param(value = "age") int age);

    @Query(value = " SELECT o.customer FROM Order o  GROUP BY o.customer HAVING count(o.id) >= 2 ")
    public List<Customer> testGroupBy();

    @Query(value = " FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = :id ")
    public Customer testLeftOuterJoinFetch(@Param(value = "id") int id);

    @Query(value = " SELECT lower(c.email) FROM Customer c ")
    public List<String> testJpqlFunction();

    @Modifying
    @Query(value = " UPDATE Customer c SET c.lastName = ?1 WHERE c.id = ?2 ")
    public void testExecuteUpdate(String lastName, int id);

    @Query(value = " FROM Customer c  ")
    public Page<Customer> testPageLeftWhere(Pageable pageable);



    //sql 写法
    @Query(value = " SELECT age FROM jpa_cutomers WHERE id = ?1 ",nativeQuery = true)
    public Object testWenHao(int id);

    @Query(value = " SELECT age FROM jpa_cutomers WHERE id = :id ",nativeQuery = true)
    public Object testMaoHao(@Param(value = "id") int id);

    @Query(value = " SELECT age FROM jpa_cutomers WHERE id = :id ",nativeQuery = true)
    public Object testGetSimpleOne(@Param(value = "id") int id);

    @Query(value = " SELECT * FROM jpa_cutomers WHERE id = :id ",nativeQuery = true)
    public Object testGetObjone(@Param(value = "id") int id);

//    @Query(value = " SELECT id, last_name as lastName FROM jpa_cutomers ",nativeQuery = true)
//    这里注意使用 spring data写法，必须查询出全部字段
    @Query(value = " SELECT * FROM jpa_cutomers ",nativeQuery = true)
    public List<Customer> testGetObjs();

    @Query(value = " SELECT last_name AS lastName FROM jpa_cutomers ",nativeQuery = true)
    public List<Map<String, Object>> testGetMaps();
}
 