package com.lan.bmwai;

import com.lan.bmwai.dao.BaseDao;
import com.lan.bmwai.dao.BaseDaoImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CommonJunitTest {

    protected static BaseDao baseDao;
    protected static BaseDao baseDao2;

    @Before
    public void setUp() throws Exception {
        baseDao = new BaseDaoImpl();
        baseDao2 = new BaseDaoImpl("two");
    }

    @After
    public void tearDown() throws Exception {
        baseDao.closeConnection();
        baseDao2.closeConnection();
    }

    @Test
    public void test() {

    }
}
