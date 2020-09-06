package com.lan._1utils._2collectionmap.listmap;

import org.junit.Test;

import java.util.List;
import java.util.Map;


public class _4getListMapOfCamelUtilTest {

    @Test
    public void changeMapKeysDemo() {
        List<Map<String, Object>> listMapIn2 = TestStaticData.listMapIn;
        String [] keysResult = {"collectMonth", "totalNum"};
        String [] keys = {"collect_month", "totalnum"};
//        List<Map<String, Object>> listMapResult = _4getListMapOfUpperUtils.changeMapKeys(listMapIn2, keys, keysResult);
        List<Map<String, Object>> listMapResult = _4getListMapOfCamelUtil.changeMapKeys(listMapIn2);
        for (Map<String, Object> stringObjectMap : listMapResult) {
            System.out.println(stringObjectMap);
        }
        System.out.println("-----------------------");
        for (Map<String, Object> stringObjectMap : listMapIn2) {
            System.out.println(stringObjectMap);
        }
    }


}