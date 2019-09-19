package com.lan._1utils._3date;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilsTest {

    @Test
    public void dayComparePrecise() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        DayCompare a = DateUtils.dayCompare(sdf.parse("2017-02"), sdf.parse("2019-01"));
        System.out.println(a);
    }
}
