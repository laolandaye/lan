package com.lan._1utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class _3DateStrUtilTest {

    @Test
    public void getNowDateTest() {
        System.out.println(_3DateStrUtil.getNowDate());
    }

    @Test
    public void getParseDateTest() {
        String str = "1997-12-03 00:00:00";
        System.out.println(_3DateStrUtil.getParseDate(str));
    }

    @Test
    public void daysBetweenTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = sdf.parse("2012-09-08 10:10:10");
        Date d2 = sdf.parse("2012-09-15 00:00:00");
        System.out.println(_3DateStrUtil.daysBetween(d1, d2));

        System.out.println(_3DateStrUtil.daysBetween("2012-09-08 10:10:10", "2012-09-15 00:00:00"));

    }

    @Test
    public void getNumByTypeTest() {
//        System.out.println(_IntervalUtil.getNumByType(""));
        System.out.println(_3DateStrUtil.getNumByType("day"));
//        System.out.println(_IntervalUtil.getNumByType(""));
        System.out.println(_3DateStrUtil.getNumByType("month"));
        System.out.println(_3DateStrUtil.getNumByType("year"));
    }


    @Test
    public void getWeekOfDateTest() {
        String dateStr = _3DateStrUtil.getWeekOfDate("2019-12-16 00:00:00");
        System.out.println(dateStr);
    }
}