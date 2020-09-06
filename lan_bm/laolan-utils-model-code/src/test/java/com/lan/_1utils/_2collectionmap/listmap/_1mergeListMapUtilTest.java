package com.lan._1utils._2collectionmap.listmap;

import org.junit.Test;

import java.util.*;

public class _1mergeListMapUtilTest {

    @Test
    public void mergeListMapLeft() {
        List<Map<String, Object>> listMapOut = TestStaticData.listMapOut;
        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
        System.out.println("---------");
        List<Map<String, Object>> listMapIn = TestStaticData.listMapIn3;
        for (Map<String, Object> stringObjectMap : listMapIn) {
            System.out.println(stringObjectMap);
        }
        System.out.println("---------");
        _1mergeListMapUtil.mergeListMapLeft(listMapOut, listMapIn, "id", new HashMap<String, Object>(1){{ put("grid_path","0"); }});
        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }

        List<Map<String, Object>> listMapIn2 = TestStaticData.listMapIn;
        String [] keysMonth = {"collect_month", "totalnum"};
        List<Map<String, Object>> listYear = null;
//                ListMapUtils.getListMapByDateTime("year", 1, keysMonth,true);
        _1mergeListMapUtil.mergeListMapLeft(listYear, listMapIn2, "collect_month");
        for (Map<String, Object> stringObjectMap : listYear) {
            System.out.println("---------" + stringObjectMap);
        }

        List<Map<String, Object>> listMapOut2 =  TestStaticData.listMapOut;
        for (Map<String, Object> stringObjectMap : listMapOut2) {
            System.out.println(stringObjectMap);
        }
        System.out.println("---------");
        List<Map<String, Object>> listMapIn22 = TestStaticData.listMapIn3;
        for (Map<String, Object> stringObjectMap : listMapIn22) {
            System.out.println(stringObjectMap);
        }
        System.out.println("---------");
        _1mergeListMapUtil.mergeListMapLeft(listMapOut2, listMapIn22, "id");
        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void mergeListMapLeftTest() {
        String [] keysHour = {"apiErrTimes", "apiReqTimes", "apiSuccessTimes"};
        List<Map<String, Object>> listMapOther = _2createListMapUtil.createListMapOther("hour",0, keysHour, true);
        String [][] timesSet = {
                {"timeId", "yyyy-MM-dd HH", ":00:00"},
                {"timeIdHour", "HH", ":00"}
        };
        List<Map<String, Object>> listHour = _2createListMapUtil.createDateTimeBetween(timesSet, "2019-01-01 00:00:00","2019-01-01 23:59:59","1");
        _1mergeListMapUtil.mergeListMapLeft(listHour, listMapOther, "timeOnlyId");
        for (Map<String, Object> map : listHour) {
            System.out.println(map);
        }
    }

}