package com.lan.bmwai.dao.single.test;

import com.lan.bmwai.dao.single.SingleBaseDao;
import com.lan.bmwai.dao.single.SingleBaseDaoImpl;
import com.lan.bmwai.dao.single.SingleDruidUtil;

public class TestTran {

    public static void main(String[] args) {
        try {
            SingleDruidUtil.openTransaction();

            SingleBaseDao baseDao = SingleBaseDaoImpl.getInstance();
            String sql = "INSERT INTO jd_sc(id, name) VALUES (?, ?)";
            int a = baseDao.executeUpdate(sql, new Object[]{1, "22"});
            System.out.println(a);
            int oooa = 5 / 0;
            String sql2 = "INSERT INTO jd_sc2(id, name) VALUES (?, ?)";
            int a2 = baseDao.executeUpdate(sql2, new Object[]{1, "22"});
            System.out.println(a2);

            SingleDruidUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            SingleDruidUtil.rollbackTransaction();
        }

    }


}
