package com.lan._4测试;

import java.sql.SQLException;

public class EfficTest {

    //一般在方法类，建立连接只有一次，不公平
    public static void main(String[] args) throws SQLException {
        long start = System.currentTimeMillis();
        // create();
        long end =  System.currentTimeMillis();
        System.out.println("create:"+(end - start));

        start = System.currentTimeMillis();
        // read();
        end =  System.currentTimeMillis();
        System.out.println("read:"+(end - start));

    }

}
