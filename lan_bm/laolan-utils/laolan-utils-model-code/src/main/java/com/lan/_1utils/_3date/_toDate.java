package com.lan._1utils._3date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class _toDate {

    // 基础用法
    public static void main(String[] args) {
        getNowDate();
        getParseDate("1997-12-03 00:00:00");
    }


    // 获得当前时间
    public static Date getNowDate() {
        Date now = new Date();
        return now;
    }
    public static Date getNowDate2() {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        return now;
    }


    // 日期 转换
    public static Date getParseDate(String str) {
        //str表示yyyy年MM月dd HH:mm:ss格式字符串
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
            return date;
        } catch (ParseException e) {
            System.out.println("不能转换时间");
            e.printStackTrace();
            return date;
        }
    }

}
