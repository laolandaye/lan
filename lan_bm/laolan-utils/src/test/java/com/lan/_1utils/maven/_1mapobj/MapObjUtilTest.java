package com.lan._1utils.maven._1mapobj;

import com.lan._1utils._2collectionmap.*;
import com.lan._1utils._2collectionmap.PhoneVO;
import com.lan._1utils._2collectionmap.Po;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MapObjUtilTest {

    @Test
    public void mapToObj() {
        String [] a = new String[]{"company", "type", "car_no", "car"};
        List<String> b = Arrays.asList(a);
        com.lan._1utils._2collectionmap.Po p = new Po(11, "map火车站", "2", "022",  1.365f,  2.0325, true, a,  b);

        Map map =  MapObjUtil.objToMap(p);

        System.out.println(map);
    }

    @Test
    public void objToMap() {
        //体现map无顺序
        String [] a = new String[]{"company", "type", "car_no", "car"};
        List<String> b = Arrays.asList(a);
        Map<String, Object> map = new HashMap(){{
            put("id", 11);
            put("name", "map火车站");
            put("no_age", "2");
            put("noAge", "022");
            put("flag",  true);
            put("no2",  1.365f);
            put("no3",  2.0325);
            put("strs",  a);
            put("strs2",  b);
        }};

        //1.map转换成对象
        System.out.println("---------map转换成对象----------");
        System.out.println("原map:" + map);
        Po poObj = MapObjUtil.mapToObj(Po.class, map);
        System.out.println("现对象：" + poObj);
    }

    @Test
    public void listMapToListObj() throws Exception {
        // 初始化
        Map<String, Object> map = new HashMap<>();
        map.put("plate", "hw的方式发送到");
        map.put("number", 13655555555L);
        map.put("memory", 4.0);
        map.put("size", 6.0);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("plate", "apple");
        map1.put("number", 13677777777L);
        map1.put("memory", 5.5);
        map1.put("size", 3.8);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        list.add(map1);

        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
        System.out.println("--------------------------");
        // List<Map<String, Object>>转List<T>
        List<com.lan._1utils._2collectionmap.PhoneVO> phoneList = MapObjUtil.listMapToListObj(list, com.lan._1utils._2collectionmap.PhoneVO.class);
        for(PhoneVO phone : phoneList) {
            System.out.println(phone);
        }
    }
}