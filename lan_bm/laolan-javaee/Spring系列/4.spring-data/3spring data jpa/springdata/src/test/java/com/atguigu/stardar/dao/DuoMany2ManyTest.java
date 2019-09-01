package com.atguigu.stardar.dao;

import com.atguigu.stardar.pojo.DuoM2mCategory;
import com.atguigu.stardar.pojo.DuoM2mItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles({"test"})
public class DuoMany2ManyTest {

    @Autowired
    private DuoM2mCategoryDao duoM2mCategoryDao;

    @Autowired
    private DuoM2mItemDao duoM2mItemDao;


    //多对所的保存
    @Test
    public void testManyToManysave() {
        DuoM2mItem i1 = new DuoM2mItem();
        i1.setItemName("i-1");

        DuoM2mItem i2 = new DuoM2mItem();
        i2.setItemName("i-2");

        DuoM2mCategory c1 = new DuoM2mCategory();
        c1.setCategoryName("C-1");

        DuoM2mCategory c2 = new DuoM2mCategory();
        c2.setCategoryName("C-2");

        //设置关联关系
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);

        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        c1.getItems().add(i1);
        c1.getItems().add(i2);

        c2.getItems().add(i1);
        c2.getItems().add(i2);

        //执行保存
        duoM2mCategoryDao.save(c1);
        duoM2mCategoryDao.save(c2);
        duoM2mItemDao.save(i1);
        duoM2mItemDao.save(i2);

    }

    //对于关联的集合对象, 默认使用懒加载的策略.
    //使用维护关联关系的一方获取, 还是使用不维护关联关系的一方获取, SQL 语句相同. 
    @Test
    public void testManyToManyFind() {
//		DuoM2mItem item = entityManager.find(DuoM2mItem.class, 1);
//		System.out.println(item.getItemName());
//		
//		System.out.println(item.getCategories().size());

        DuoM2mCategory category = duoM2mCategoryDao.findOne(2);
        System.out.println(category.getCategoryName());
        System.out.println(category.getItems().size());
    }
}