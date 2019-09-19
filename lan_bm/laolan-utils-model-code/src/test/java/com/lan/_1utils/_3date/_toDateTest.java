package com.lan._1utils._3date;

import org.junit.Test;

import static org.junit.Assert.*;

public class _toDateTest {

    @Test
    public void getNowDate() {
        System.out.println(_toDate.getNowDate());
    }

    @Test
    public void getParseDate() {
        String str = "1997-12-03 00:00:00";
        System.out.println(_toDate.getParseDate(str));
    }
}