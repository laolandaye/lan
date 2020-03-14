package com.lan._1utils._3date;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class _toString {

    /**
     * 获得开始日期和结束日期
     *  @param deltaWeeks 相对于当前位置的前几周或者后几周
     */
    public static Pair getStartEndDateByNowWeek(int deltaWeeks) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, deltaWeeks * 7);
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (currentDay == 1) {
            currentDay = 7;
        } else {
            currentDay = currentDay - 1;
        }
        calendar.add(Calendar.DAY_OF_YEAR, 1 - currentDay);
        Date date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = format.format(date);

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 6);
        String endDate = format.format(calendar.getTime());

        return new ImmutablePair<>(startDate, endDate);
    }

    // 获得当前日期
    // https://blog.csdn.net/topdeveloperr/article/details/91571311
    public static String getNowDate() {
        LocalDate date = LocalDate.now(); // get the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    /**
     * 功能描述: 根据日期获取周几
     * https://blog.csdn.net/qq_37511501/article/details/80193097
     */
    public static String getWeekOfDate(String date) {
        return getWeekOfDate(_toDate.getParseDate(date));
    }

    public static String getWeekOfDate(Date date) {

        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }
}
