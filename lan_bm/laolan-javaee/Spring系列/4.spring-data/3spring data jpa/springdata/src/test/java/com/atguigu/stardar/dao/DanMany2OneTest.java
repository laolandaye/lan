package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DanM2oCustomer;
import com.atguigu.stardar.pojo.DanM2oOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class DanMany2OneTest {

    @Autowired
    private DanM2oCustomerDao danM2oCustomerDao;

    /**
     * 如果这里爆红，看看包扫描没得
     */
    @Autowired
    private DanM2oOrderDao danM2oOrderDao;

    /**
     * 先多再一
     * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
     * 先保存多，再一，对于自增主键，会报错
     */
    @Test
    @Deprecated
    public void testM2OSave() {
    }

    /**
     * 先一再多
     * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
     */
    @Test
    public void testO2mSave2() {
        DanM2oCustomer customer = new DanM2oCustomer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("gg@163.com");
        customer.setLastName("GG");
        customer.setXml("xml");

        DanM2oOrder order1 = new DanM2oOrder();
        order1.setOrderName("G-GG-1");
        DanM2oOrder order2 = new DanM2oOrder();
        order2.setOrderName("G-GG-2");

        //设置关联关系
        order1.setCustomer(customer);
        order2.setCustomer(customer);

        //执行保存操作(先一再多)
        danM2oCustomerDao.save(customer);

        danM2oOrderDao.save(order1);
        danM2oOrderDao.save(order2);

    }


    // 删除多端
    @Test
    public void testManyRemove(){
        danM2oOrderDao.delete(3);
    }

    //不能直接删除 1 的一端, 因为有外键约束.
    @Test
    @Deprecated
    public void testOneRemove(){
    }

    // 查询单向多，这里只有多方配置了，如果想查询，自己 变为双向关系
    @Test
    @Transactional
    public void testManySearch() {
        List<DanM2oOrder> a = danM2oOrderDao.findAll();
        for (int i = 0; i < a.size(); i++) {
            System.out.println("--------------" + i);
            System.out.println(a.get(i).getId());
            System.out.println(a.get(i).getCustomer().getId());
        }
    }
}
