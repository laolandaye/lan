package com.atguigu.JpaNamedQuerySql.dao;

import com.atguigu.JpqlNamedQuerySql.dao.CustomerDao;
import com.atguigu.JpqlNamedQuerySql.dao.OrderDao;
import com.atguigu.JpqlNamedQuerySql.pojo.Customer;
import com.atguigu.JpqlNamedQuerySql.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class JpqlTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderDao orderDao;

    //createNativeQuery 适用于本地 SQL
    @Test
    public void test() {
        // 先来批量插入些数据  先一再多
        for (int i = 0; i < 20; i++) {
            Customer customer = new Customer();
            customer.setAge(18 + i);
            customer.setBirth(new Date());
            customer.setCreatedTime(new Date());
            customer.setEmail(18 + i + "gg@163.com");
            customer.setLastName(18 + i + "顾客");

            Order order1 = new Order();
            order1.setOrderName(18 + i +"订单-1");
            Order order2 = new Order();
            order2.setOrderName(18 + i +"订单-2");

            //设置关联关系
            order1.setCustomer(customer);
            order2.setCustomer(customer);

            //执行保存操作(先一再多)
            customerDao.save(customer);

            orderDao.save(order1);
            orderDao.save(order2);
        }

    }

    // 查询所有列
    @Test
    public void testAllCol(){
        List<Customer> customers = customerDao.testAllCol();
        for (Customer customer : customers) {
              System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    // 查询部分列
    //默认情况下, 若只查询部分属性, 则将返回 Object[] 类型的结果. 或者 Object[] 类型的 List.
    //也可以在实体类中创建对应的构造器, 然后再 JPQL 语句中利用对应的构造器返回实体类的对象.
    @Test
    public void testSomeCol(){
        List<Customer> customers = customerDao.testSomeCol();
        for (Customer customer : customers) {
              System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName());
        }
    }

    // where 查询条件 1.4.1==， <>， >， >=， <， <=，
    @Test
    public void testWhere(){
        List<Customer> customers = customerDao.testWhere(25);
        for (Customer customer : customers) {
             System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    // where 查询条件 1.4.2 between…and
    @Test
    public void testWhereBetween(){
        List<Customer> customers = customerDao.testWhereBetween(26, 34);
        for (Customer customer : customers) {
              System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    // where 查询条件 1.4.2 Like
    @Test
    public void testWhereLike(){
        List<Customer> customers = customerDao.testWhereLike1("%" + "9g" + "%");
        for (Customer customer : customers) {
             System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }

        customers = customerDao.testWhereLike2("_" + "2gg@163.com");
        for (Customer customer : customers) {
            System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    // where 查询条件  in
    @Test
    public void testWhereIn(){
        List<Customer> customers = customerDao.testWhereIn1("'27顾客','30顾客'");
        for (Customer customer : customers) {
             System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }

        List<String> lastNames = Arrays.asList(new String[]{"27顾客", "30顾客"});
        customers = customerDao.testWhereIn2(lastNames);
        for (Customer customer : customers) {
            System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    // where 查询条件  is null
    @Test
    public void testWhereIsNull(){
        List<Customer> customers = customerDao.testWhereIsNull();
        for (Customer customer : customers) {
             System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    //使用 hibernate 的查询缓存.
    @Test
    public void testQueryCache(){

    }

    @Test
    public void testOrderBy(){
        List<Customer> customers = customerDao.testOrderBy(4);
        for (Customer customer : customers) {
              System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }

    //查询 order 数量大于 2 的那些 Customer
    @Test
    public void testGroupBy(){
        List<Customer> customers =customerDao.testGroupBy();

        for (Customer customer : customers) {
              System.out.println("岁数：" + customer.getAge() + " 姓：" + customer.getLastName() + " 生日：" + customer.getBirth() + "邮箱：" + customer.getEmail());
        }
    }
    /**
     * JPQL 的关联查询同 HQL 的关联查询.(会自动级联)
     */
    @Test
    public void testLeftOuterJoinFetch(){
        Customer customer = customerDao.testLeftOuterJoinFetch(11);
        System.out.println(customer.getLastName());
        System.out.println(customer.getOrders().size());
    }

    // 子查询
    @Test
    public void testSubQuery(){
        //查询所有 Customer 的 lastName 为 YY 的 Order
        List<Order> orders = orderDao.testSubQuery("YY");
        System.out.println(orders.size());
    }

    //使用 jpql 内建的函数
    @Test
    public void testJpqlFunction(){
        List<String> emails = customerDao.testJpqlFunction();
        System.out.println(emails);
    }

    //可以使用 JPQL 完成 UPDATE 和 DELETE 操作.
    @Test
    @Transactional
    public void testExecuteUpdate(){
        customerDao.testExecuteUpdate("YY你好", 11);
    }

    // 分页
    @Test
    public void testPageLeftWhere(){
        Pageable pageable = new PageRequest(0, 15);
        Page<Customer> pag =  customerDao.testPageLeftWhere(pageable);
        System.out.println(pag.getTotalElements());
        for (int i = 0; i < pag.getContent().size(); i++) {
            System.out.println(pag.getContent().get(i).getId() + "---------" + pag.getContent().get(i).getBirth());
        }

        Page<Order> pag2 =  orderDao.testPageLeftWhere(pageable);
        System.out.println(pag2.getTotalElements());
        for (int i = 0; i < pag2.getContent().size(); i++) {
            System.out.println(pag2.getContent().get(i).getId() + "---------" + pag2.getContent().get(i).getOrderName());
        }
    }


}
