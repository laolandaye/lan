package com.lan._1utils._2collectionmap.listmap;

import com.lan._1utils._2collectionmap.ListMapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class _4getListMapOfUpperUtilsTest {

    @Test
    public void changeMapKeys() {
        List<Map<String, Object>> listMapIn2 = TestStaticData.listMapIn;
        String [] keysResult = {"collectMonth", "totalNum"};
        String [] keys = {"collect_month", "totalnum"};
//        List<Map<String, Object>> listMapResult = _4getListMapOfUpperUtils.changeMapKeys(listMapIn2, keys, keysResult);
        List<Map<String, Object>> listMapResult = _4getListMapOfUpperUtils.changeMapKeys(listMapIn2);
        for (Map<String, Object> stringObjectMap : listMapResult) {
            System.out.println(stringObjectMap);
        }
        System.out.println("-----------------------");
        for (Map<String, Object> stringObjectMap : listMapIn2) {
            System.out.println(stringObjectMap);
        }
        System.out.println(keysResult);
        System.out.println(keys);
    }


    @Test
    public void getListMapOfUpper() {
        List<Map<String, Object>> listMapIn2 = TestStaticData.listMapIn6;
//        List<Map<String, Object>> listMapResult = ListMapUtils.changeMapKeys(listMapIn2, keys, keysResult);
        _4getListMapOfUpperUtils.getListMapOfUpper(listMapIn2);
        for (Map<String, Object> stringObjectMap : listMapIn2) {
            System.out.println(stringObjectMap);
        }

    }
}