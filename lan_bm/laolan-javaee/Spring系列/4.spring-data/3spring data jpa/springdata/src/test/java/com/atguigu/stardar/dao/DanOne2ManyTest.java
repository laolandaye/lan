package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DanO2mCustomer;
import com.atguigu.stardar.pojo.DanO2mOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class DanOne2ManyTest {

    @Autowired
    private DanO2mCustomerDao customerDao2;

    @Autowired
    private DanO2mOrderDao orderDao2;

    /**
     * 先一再多
     * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
     * 先保存多，再一，对于自增主键，会报错
     */
    @Test
    public void testOneToManySave() {
        DanO2mCustomer customer = new DanO2mCustomer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("mm@163.com");
        customer.setLastName("MM");

        DanO2mOrder order1 = new DanO2mOrder();
        order1.setOrderName("O-MM-1");

        DanO2mOrder order2 = new DanO2mOrder();
        order2.setOrderName("O-MM-2");

        //建立关联关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        //执行保存操作
        customerDao2.save(customer);

        orderDao2.save(order1);
        orderDao2.save(order2);
    }

    /**
     * 先多再一，无级联策略
     */
    @Test
    @Deprecated
    public void testOneToManySave2() {

    }


    //默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除.
    //可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
    @Test
    public void testOneRemove(){
        customerDao2.delete(5);
    }
}
