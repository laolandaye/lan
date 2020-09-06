package com.lan._1utils._2collectionmap.listmap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 实际是 left join
 */
public class _1mergeListMapOfMappingUtil {

    // 目前 用于一对多（推荐），多对一，一对一

    /**
     * 用于一对多（推荐）
     * @param listMapOut
     * @param listMapIn
     * @param keys  { "id", "key_id", "inPos"}; 外层id, 内层外键id， 实体命名
     */
    public static void mergeOfO2mMapping(List<Map<String, Object>> listMapOut, List<Map<String, Object>> listMapIn, String [] keys) {
        String outId = "";  // 外层id
        Set<Map<String, Object>> inPos = null;  // 外层多实体
        for (Map<String, Object> outMap : listMapOut) {
            outId = outMap.get(keys[0]).toString();
            inPos = new HashSet<>();
            String inId = "";   // 内层外键id
            for (Map<String, Object> inMap : listMapIn) {
                inId = inMap.get(keys[1]).toString();
                if(outId.equals(inId)) {
                    inPos.add(inMap);
                }
            }
            outMap.put(keys[2], inPos);
        }
    }

    /**
     * 用于一对多（推荐） 驼峰式
     * @param listMapOut
     * @param listMapIn
     * @param keys  { "id", "keyId", "inPos"}; 外层id, 内层外键id， 实体命名
     */
    public static void mergeOfO2mMappingUpper(List<Map<String, Object>> listMapOut, List<Map<String, Object>> listMapIn, String [] keys) {
        // 实质就是在分组之前将两个数据驼峰式
        _4getListMapOfCamelUtil.getListMapOfUpper(listMapOut);
        _4getListMapOfCamelUtil.getListMapOfUpper(listMapIn);

        String outId = "";  // 外层id
        Set<Map<String, Object>> inPos = null;  // 外层多实体
        for (Map<String, Object> outMap : listMapOut) {
            outId = outMap.get(keys[0]).toString();
            inPos = new HashSet<>();
            String inId = "";   // 内层外键id
            for (Map<String, Object> inMap : listMapIn) {
                inId = inMap.get(keys[1]).toString();
                if(outId.equals(inId)) {
                    inPos.add(inMap);
                }
            }
            outMap.put(keys[2], inPos);
        }
    }

    /**
     * 多对一，一对一
     * @param listMapOut
     * @param listMapIn
     * @param keys  { "id", "key_id", "inPo"}; 外层id, 内层外键id， 实体命名
     */
    public static void mergeOfM2oO2oMapping(List<Map<String, Object>> listMapOut, List<Map<String, Object>> listMapIn, String [] keys) {
        String outId = "";  // 外层id
        lableB:	for (Map<String, Object> outMap : listMapOut) {
            outId = outMap.get(keys[0]).toString();
            String inId = "";   // 内层外键id
            for (Map<String, Object> inMap : listMapIn) {
                inId = inMap.get(keys[1]).toString();
                if(outId.equals(inId)) {
                    outMap.put(keys[2], inMap);
                    continue lableB;//满足条件后跳出双层循环进入下一次
                }
            }

        }
    }

    /**
     * 多对一，一对一 （驼峰式）
     * @param listMapOut
     * @param listMapIn
     * @param keys  { "id", "keyId2", "inPo"}; 外层id, 内层外键id， 实体命名
     */
    public static void mergeOfM2oO2oMappingUpper(List<Map<String, Object>> listMapOut, List<Map<String, Object>> listMapIn, String [] keys) {
        // 实质就是在分组之前将两个数据驼峰式
        _4getListMapOfCamelUtil.getListMapOfUpper(listMapOut);
        _4getListMapOfCamelUtil.getListMapOfUpper(listMapIn);

        String outId = "";  // 外层id
        lableB:	for (Map<String, Object> outMap : listMapOut) {
            outId = outMap.get(keys[0]).toString();
            String inId = "";   // 内层外键id
            for (Map<String, Object> inMap : listMapIn) {
                inId = inMap.get(keys[1]).toString();
                if(outId.equals(inId)) {
                    outMap.put(keys[2], inMap);
                    continue lableB;//满足条件后跳出双层循环进入下一次
                }
            }

        }
    }

}
