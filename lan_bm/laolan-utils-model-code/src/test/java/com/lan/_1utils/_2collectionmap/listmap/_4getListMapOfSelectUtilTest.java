package com.lan._1utils._2collectionmap.listmap;


import org.junit.Test;

import java.util.List;
import java.util.Map;

public class _4getListMapOfSelectUtilTest {

    @Test
    public void splitMayByKeys() {
        List<Map<String, Object>> listMapIn2 = TestStaticData.listMapIn2;
        String [] keys = {"company", "type"};
        List<Map<String, Object>> listMapResult =  _4getListMapOfSelectUtil.splitMayByKeys(listMapIn2, keys);
        for (Map<String, Object> map : listMapResult) {
            System.out.println(map);
        }
    }
}