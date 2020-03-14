package com.lan._1utils.maven._1对象和Map互转;

import org.junit.Test;

import java.util.*;

public class ConvertMapObjUtilTest {

    @Test
    public void beanToMap() {
        String [] a = new String[]{"company", "type", "car_no", "car"};
        List<String> b = Arrays.asList(a);
        Po p = new Po(11, "map火车站", "2", "022",  1.365f,  2.0325, true, a,  b);

        Map map =  ConvertMapObjUtil.beanToMap(p);

        System.out.println(map);

    }

    @Test
    public void mapToBean() {
        //体现map无顺序
        final String [] a = new String[]{"company", "type", "car_no", "car"};
        final List<String> b = Arrays.asList(a);
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
        Po poObj = ConvertMapObjUtil.mapToBean(map, new Po());
        System.out.println("现对象：" + poObj);
    }

    @Test
    public void objectsToMaps() {
        List<PhoneVO> list = Arrays.asList(
                new PhoneVO("hw",  13655555555L, 4.0, 6.0),
                new PhoneVO("hw2",  136552L, 4.02, 6.8),
                new PhoneVO("hw2",  1555L, 4.06, 6.9)
        );
        List<Map<String, Object>> list2 = ConvertMapObjUtil.objectsToMaps(list);
        for (Map<String, Object> stringObjectMap : list2) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void mapsToObjects() throws Exception{
        // 初始化
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("plate", "hw");
        map.put("number", 13655555555L);
        map.put("memory", 4.0);
        map.put("size", 6.0);

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("plate", "apple");
        map1.put("number", 13677777777L);
        map1.put("memory", 5.5);
        map1.put("size", 3.8);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        list.add(map);
        list.add(map1);

        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
        System.out.println("--------------------------");
        // List<Map<String, Object>>转List<T>
        List<PhoneVO> phoneList = ConvertMapObjUtil.mapsToObjects(list, PhoneVO.class);
        for(PhoneVO phone : phoneList) {
            System.out.println(phone);
        }
    }
}
