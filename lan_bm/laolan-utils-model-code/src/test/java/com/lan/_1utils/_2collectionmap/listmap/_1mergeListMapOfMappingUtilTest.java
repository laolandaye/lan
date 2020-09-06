package com.lan._1utils._2collectionmap.listmap;

import org.junit.Test;

import java.util.List;
import java.util.Map;

public class _1mergeListMapOfMappingUtilTest {

    // listMapOut listMapIn5
    @Test
    public void mergeOfO2mMapping() {
        List<Map<String, Object>> listMapOut = TestStaticData.listMapOut;
        List<Map<String, Object>> listMapIn5 = TestStaticData.listMapIn5;

        String [] keys = { "id", "key_id", "inPos"};
        _1mergeListMapOfMappingUtil.mergeOfO2mMapping(listMapOut, listMapIn5, keys);

        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
        System.out.println("------------");
        // 综合
        List<Map<String, Object>> listMapIn6 = TestStaticData.listMapIn6;
        String [] keys2 = { "id", "key_id2", "inPo"};
        _1mergeListMapOfMappingUtil.mergeOfM2oO2oMapping(listMapOut, listMapIn6, keys2);

        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
    }

    // listMapOut listMapIn6
    @Test
    public void mergeOfM2oO2oMapping() {
        List<Map<String, Object>> listMapOut = TestStaticData.listMapOut;
        List<Map<String, Object>> listMapIn6 = TestStaticData.listMapIn6;
        String [] keys = { "id", "key_id2", "inPo"};
        _1mergeListMapOfMappingUtil.mergeOfM2oO2oMapping(listMapOut, listMapIn6, keys);

        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
    }

    // listMapOut listMapIn5
    @Test
    public void mergeOfO2mMappingUpper() {
        List<Map<String, Object>> listMapOut = TestStaticData.listMapOut;
        List<Map<String, Object>> listMapIn5 = TestStaticData.listMapIn5;

        String [] keys = { "id", "keyId", "inPos"};
        _1mergeListMapOfMappingUtil.mergeOfO2mMappingUpper(listMapOut, listMapIn5, keys);

        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
        System.out.println("------------");
        // 综合
        List<Map<String, Object>> listMapIn6 = TestStaticData.listMapIn6;
        String [] keys2 = { "id", "keyId2", "inPo"};
        _1mergeListMapOfMappingUtil.mergeOfM2oO2oMappingUpper(listMapOut, listMapIn6, keys2);

        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
    }

    // listMapOut listMapIn6
    @Test
    public void mergeOfM2oO2oMappingUpper() {
        List<Map<String, Object>> listMapOut = TestStaticData.listMapOut;
        List<Map<String, Object>> listMapIn6 = TestStaticData.listMapIn6;
        String [] keys = { "id", "keyId2", "inPo"};
        _1mergeListMapOfMappingUtil.mergeOfM2oO2oMappingUpper(listMapOut, listMapIn6, keys);

        for (Map<String, Object> stringObjectMap : listMapOut) {
            System.out.println(stringObjectMap);
        }
    }
}