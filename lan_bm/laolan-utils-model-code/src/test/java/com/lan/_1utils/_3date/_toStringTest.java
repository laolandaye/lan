package com.lan._1utils._3date;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class _toStringTest {

    @Test
    public void getStartEndDateByNowWeekTest() {
        Pair<String, String> p = _toString.getStartEndDateByNowWeek(0);
        System.out.println(p.getLeft());
        System.out.println(p.getRight());
    }
    @Test
    public void getNowDateTest() {
        String dateStr = _toString.getNowDate();
        System.out.println(dateStr);
    }

    @Test
    public void getWeekOfDateTest() {
        String dateStr = _toString.getWeekOfDate("2019-12-16 00:00:00");
        System.out.println(dateStr);
    }
}