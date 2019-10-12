package com.lan._1utils._3date;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

/**
 * 计算时间差
 */
public class DateTimeDifUtils {


    /**
     *  返回当前时间，与当天，当月
     * @param dateType
     * @return 秒数
     */
    public static Long getTimeDif(String dateType) {
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime endTime = null;
        switch (dateType) {
            case "month":
                // 获得当月结束时间
                endTime = nowTime.with(TemporalAdjusters.lastDayOfMonth());
                break;
            case "day":
                // 获得当天结束时间
                endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
                break;
            default:
                break;
        }
        Duration duration = Duration.between(nowTime, endTime);
        return duration.getSeconds();
    }

}
