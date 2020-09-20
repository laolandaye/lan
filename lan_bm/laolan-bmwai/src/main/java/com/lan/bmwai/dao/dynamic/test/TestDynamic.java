package com.lan.bmwai.dao.dynamic.test;

import com.lan.bmwai.dao.dynamic.BaseDruidDao;
import com.lan.bmwai.dao.dynamic.BaseDruidDaoImpl;
import com.lan.bmwai.dao.dynamic.DruidDbConf;

import java.sql.SQLException;

public class TestDynamic {

    public static void main(String[] args) {
        BaseDruidDao baseDruidDao = new BaseDruidDaoImpl();


        //线程1的ThreadLocalMap中存着key为threadLocalA，value为A1；key为threadLocalB，value为B1
        new Thread(){
            @Override
            public void run() {
                for (; ; ) {
                try {
                    String sql = "INSERT INTO jd_sc(id, name) VALUES (?, ?)";
                    int a = baseDruidDao.executeUpdate(DruidDbConf.DATA_SOURCE1, sql, new Object[]{1, "22"});
                    int s = (int)(Math.random()*10);
                    System.out.println(Thread.currentThread().getName() + " " + sql +" "  + a + " " + s + "s");
                    Thread.sleep(s);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }.start();

        //线程2的ThreadLocalMap中存着key为threadLocalA，value为A2；key为threadLocalB，value为B2
        new Thread(){
            @Override
            public void run(){
                for (; ; ) {

                try {
                    String sql = "INSERT INTO jd_sc2(id, name) VALUES (?, ?)";
                    int a = baseDruidDao.executeUpdate(DruidDbConf.DATA_SOURCE2, sql, new Object[]{1, "22"});
                    int s = (int)(Math.random()*20);
                    System.out.println(Thread.currentThread().getName() + " " + sql +" "  + a + " " + s + "ms");
                    Thread.sleep(s);
                }  catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }
        }.start();

        new Thread(){
            @Override
            public void run(){
                for (; ; ) {


                try {
                    String sql = "INSERT INTO jd_sc3(id, name) VALUES (?, ?)";
                    int a = baseDruidDao.executeUpdate(DruidDbConf.DATA_SOURCE3, sql, new Object[]{1, "22"});
                    int s = (int)(Math.random()*30);
                    System.out.println(Thread.currentThread().getName() + " " + sql +" "  + a + " " + s + "ms");
                    Thread.sleep(s);
                }  catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }
            }
        }.start();
    }


}
