package com.lan.bmwai.dao.single.test;

import com.lan.bmwai.dao.single.BaseDao;
import com.lan.bmwai.dao.single.BaseDaoImpl;
import com.lan.bmwai.dao.single.DruidUtil;

import java.sql.Savepoint;

public class TestTranPoint {

    public static void main(String[] args) {
        Savepoint sp = null;
        try {
            DruidUtil.openTransaction();

            BaseDao baseDao = BaseDaoImpl.getInstance();
            int a0 = baseDao.executeUpdate("INSERT INTO jd_sc(id, name) VALUES (?, ?)", new Object[]{1, "111111111111111111"});
            System.out.println(a0);

            sp = DruidUtil.saveTransPoint();  // 事务保存节点

            int a1 = baseDao.executeUpdate("INSERT INTO jd_sc2(id, name) VALUES (?, ?)", new Object[]{2, "2222222222222222222"});
            System.out.println(a1);

            int aaaa = 5 / 0;   // 异常发生，理论上一条语句执行成功

            int a2 = baseDao.executeUpdate("INSERT INTO jd_sc3(id, name) VALUES (?, ?)", new Object[]{3, "3333333333333333333333"});
            System.out.println(a2);

            DruidUtil.commitTransaction();
        } catch (ArithmeticException npe){
            DruidUtil.rollbackTransPoint(sp);
        } catch (Exception e) {
            e.printStackTrace();
            DruidUtil.rollbackTransaction();
        }

    }


}
