package com.lan.bmwai.dao.single.test;

import com.lan.bmwai.dao.single.SingleBaseDao;
import com.lan.bmwai.dao.single.SingleBaseDaoImpl;
import com.lan.bmwai.dao.single.SingleDruidUtil;

import java.sql.Savepoint;

public class TestTranPoint {

    public static void main(String[] args) {
        Savepoint sp = null;
        try {
            SingleDruidUtil.openTransaction();

            SingleBaseDao baseDao = SingleBaseDaoImpl.getInstance();
            int a0 = baseDao.executeUpdate("INSERT INTO jd_sc(id, name) VALUES (?, ?)", new Object[]{1, "111111111111111111"});
            System.out.println(a0);

            sp = SingleDruidUtil.saveTransPoint();  // 事务保存节点

            int a1 = baseDao.executeUpdate("INSERT INTO jd_sc2(id, name) VALUES (?, ?)", new Object[]{2, "2222222222222222222"});
            System.out.println(a1);

            int aaaa = 5 / 0;   // 异常发生，理论上一条语句执行成功

            int a2 = baseDao.executeUpdate("INSERT INTO jd_sc3(id, name) VALUES (?, ?)", new Object[]{3, "3333333333333333333333"});
            System.out.println(a2);

            SingleDruidUtil.commitTransaction();
        } catch (ArithmeticException npe){
            SingleDruidUtil.rollbackTransPoint(sp);
        } catch (Exception e) {
            e.printStackTrace();
            SingleDruidUtil.rollbackTransaction();
        }

    }


}
