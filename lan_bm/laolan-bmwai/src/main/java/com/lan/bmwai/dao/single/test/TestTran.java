package com.lan.bmwai.dao.single.test;

import com.lan.bmwai.dao.single.BaseDao;
import com.lan.bmwai.dao.single.BaseDaoImpl;
import com.lan.bmwai.dao.single.DruidUtil;

public class TestTran {

    public static void main(String[] args) {
        try {
            DruidUtil.openTransaction();

            BaseDao baseDao = BaseDaoImpl.getInstance();
            String sql = "INSERT INTO jd_sc(id, name) VALUES (?, ?)";
            int a = baseDao.executeUpdate(sql, new Object[]{1, "22"});
            System.out.println(a);
            int oooa = 5 / 0;
            String sql2 = "INSERT INTO jd_sc2(id, name) VALUES (?, ?)";
            int a2 = baseDao.executeUpdate(sql2, new Object[]{1, "22"});
            System.out.println(a2);

            DruidUtil.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            DruidUtil.rollbackTransaction();
        }
    }


}
