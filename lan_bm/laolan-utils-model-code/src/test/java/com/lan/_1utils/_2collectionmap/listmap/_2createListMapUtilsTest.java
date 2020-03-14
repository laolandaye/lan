package com.lan._1utils._2collectionmap.listmap;

import com.lan._1utils._2collectionmap.ListMapUtils;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class _2createListMapUtilsTest {

    @Test
    public void createListByTypeTest() {
        System.out.println("******************--------year----------------------");
        String [] keysMonth = {"collect_month", "totalnum", "num"};
        List<Map<String, Object>> listYear = _2createListMapUtils.createListByType("year", 1, keysMonth,true);
        for (Map<String, Object> map:listYear) {
            System.out.println(map);
        }
        System.out.println("------------------------------");
        List<Map<String, Object>> listYear2 = _2createListMapUtils.createListByType("year", 1, keysMonth,false);
        for (Map<String, Object> map:listYear2) {
            System.out.println(map);
        }

        System.out.println("******************--------month----------------------");
        String [] keysDate = {"collect_date", "totalnum", "num"};
        List<Map<String, Object>> listMonth = _2createListMapUtils.createListByType("month", 1, keysDate,true);
        for (Map<String, Object> map:listMonth) {
            System.out.println(map);
        }
        System.out.println("------------------------------");
        List<Map<String, Object>> listMonth2 = _2createListMapUtils.createListByType("month", 1, keysMonth,false);
        for (Map<String, Object> map:listMonth2) {
            System.out.println(map);
        }

        System.out.println("******************-------- week ----------------------");
        String [] keysDay = {"collect_date", "totalnum", "num"};
        List<Map<String, Object>> listWeek = _2createListMapUtils.createListByType("week", 1, keysDay,true);
        for (Map<String, Object> map:listWeek) {
            System.out.println(map);
        }
        System.out.println("------------------------------");
        List<Map<String, Object>> listWeek2 = _2createListMapUtils.createListByType("week", 1, keysDay,false);
        for (Map<String, Object> map:listWeek2) {
            System.out.println(map);
        }

        System.out.println("******************--------hour----------------------");
        String [] keysHour = {"collect_hour", "totalnum", "num"};
        List<Map<String, Object>> listHour = _2createListMapUtils.createListByType("hour", 0,keysHour,true);
        for (Map<String, Object> map:listHour) {
            System.out.println(map);
        }
        System.out.println("------------------------------");
        List<Map<String, Object>> listHour2 = _2createListMapUtils.createListByType("hour", 0, keysHour,false);
        for (Map<String, Object> map:listHour2) {
            System.out.println(map);
        }
    }

    @Test
    public void createListOtherTest() {
        System.out.println("******************-------- week ----------------------");
        String [] keysYear = {"apiErrTimes", "apiReqTimes", "apiSuccessTimes"};
        List<Map<String, Object>> listMapOtherYear = _2createListMapUtils.createListMapOther("week",0, keysYear, true);
        for (Map<String, Object> map:listMapOtherYear) {
            System.out.println(map);
        }
        System.out.println("******************--------hour----------------------");
//        String [] keysHour = {"apiErrTimes", "apiReqTimes", "apiSuccessTimes"};
//        List<Map<String, Object>> listMapOther = _2createListMapUtils.createListMapOther("hour",0, keysHour, true);
//        for (Map<String, Object> map:listMapOther) {
//            System.out.println(map);
//        }
    }

    @Test
    public void createDateTimeBetweenTest() {
        System.out.println("******************--------hour----------------------");
        String [][] timesSet = {
                {"timeId", "yyyy-MM-dd HH", ":00:00"},
                {"timeIdHour", "HH", ":00"}
        };
        List<Map<String, Object>> listHour = _2createListMapUtils.createDateTimeBetween(timesSet, "2019-01-01 00:00:00","2019-01-01 23:59:59","1");
        for (Map<String, Object> map:listHour) {
            System.out.println(map);
        }
    }

    @Test
    public void createListByTypeSpecifiedTest() {
        System.out.println("******************--------year----------------------");
        String [] keysMonth = {"collect_month", "totalnum", "num"};
        List<Map<String, Object>> listYear = _2createListMapUtils.createListByTypeSpecified("year",  keysMonth,"0");
        for (Map<String, Object> map:listYear) {
            System.out.println(map);
        }
        System.out.println("------------------------------");
    }

    @Test
    public void getListMapOfMonthTest() {
        String [] keysMonth = {"collect_month", "totalnum"};
        List<Map<String, Object>> listYear = _2createListMapUtils.getListMapOfMonth("2018-02", "2019-02", keysMonth,"0");
        for (int i = 0; i < listYear.size(); i++) {
            System.out.println(listYear.get(i));
        }
    }
}
