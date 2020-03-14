package com.lan._1utils.maven._3key转换;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 对json数据key进行替换
 *  // https://www.cnblogs.com/shihaiming/p/10208436.html （新建一个json）
 *  提供三种对外输出方式
 *      JSONObject changeJsonObj  JSONArray changeJsonArr
 *      JSON changeJson
 *      String changeJsonToStr
 */
public class FastjsonChangeNewKeyUtil {

    // 计算 filedFullPath
    private static String initFiledFullPath(String fliedFullPath, String key) {
        if("".equalsIgnoreCase(fliedFullPath)) {
            fliedFullPath = fliedFullPath + key;
        } else {
            if(fliedFullPath.startsWith("/->")) {
                fliedFullPath = fliedFullPath.substring(2, fliedFullPath.length());
            }
            if(fliedFullPath.endsWith("/->")) {
                fliedFullPath = fliedFullPath.substring(0, fliedFullPath.length()-2);
            }
            fliedFullPath = fliedFullPath + "->"+ key;
        }
        return fliedFullPath;
    }

    // 销毁 filedFullPath， 结果是 parentFiled
    private static String deployFiledFullPath(String fliedFullPath) {
        List<String> pids= Arrays.asList(fliedFullPath.split("->")).stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        String pidTemp = "";
        if(pids.size() == 0 || pids.size() == 1) {
            fliedFullPath = pidTemp;
        } else {
            for (int i = 0; i < pids.size() -1; i++) {
                if(i == pids.size() - 2) {
                    pidTemp += pids.get(i);
                } else {
                    pidTemp += pids.get(i) + "->";
                }
            }
            fliedFullPath = pidTemp;
        }
        return fliedFullPath;
    }

    public static JSONObject changeJsonObj(String fliedFullPath, JSONObject jsonObj,Map<String, String> fliedFullPathMap) {
        JSONObject resJson = new JSONObject();
        Set<String> keySet = jsonObj.keySet();
        for (String key : keySet) {
            fliedFullPath = initFiledFullPath(fliedFullPath, key);

//            String resKey = keyMap.get(key) == null ? key : keyMap.get(key);
            String resKey = fliedFullPathMap.get(fliedFullPath) == null ? key : fliedFullPathMap.get(fliedFullPath);// 使用全路径当key 替换
            try {
                JSONObject jsonobj1 = jsonObj.getJSONObject(key);
                resJson.put(resKey, changeJsonObj(fliedFullPath, jsonobj1, fliedFullPathMap));
            } catch (Exception e) {
                try {
                    JSONArray jsonArr = jsonObj.getJSONArray(key);
                    resJson.put(resKey, changeJsonArr(fliedFullPath, jsonArr, fliedFullPathMap));
                } catch (Exception x) {
                    resJson.put(resKey, jsonObj.get(key));
                }
            }
            fliedFullPath = deployFiledFullPath(fliedFullPath);
        }
        return resJson;
    }

    public static JSONArray changeJsonArr(String fliedFullPath, JSONArray jsonArr, Map<String, String> fliedFullPathMap) {
        JSONArray resJson = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            resJson.add(changeJsonObj(fliedFullPath,  jsonObj, fliedFullPathMap));
        }
        return resJson;
    }
    
    public static JSON changeJson(String jsonStr, Map<String, String> fliedFullPathMap) {
        try {
            return changeJsonArr("", JSONArray.parseArray(jsonStr),fliedFullPathMap);
        } catch (JSONException je) {
            try {
                return changeJsonObj("", JSONObject.parseObject(jsonStr),fliedFullPathMap);
            } catch (Exception e2) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String changeJsonToStr(String jsonStr, Map<String, String> fliedFullPathMap) {
        String res = null;
        try {
            res = changeJson(jsonStr, fliedFullPathMap).toJSONString();
            return res;
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        Map<String, String> fliedFullPathMap = new HashMap<String, String>();
        fliedFullPathMap.put("motion->user->name", "XingMing_name");
        fliedFullPathMap.put("user->name", "XingMing_name");
        fliedFullPathMap.put("motion->user", "YongHu_user");
        fliedFullPathMap.put("motion->user->hobby->desc", "MiaoShu_desc");

        String jsonStr = "{\"user\":{\"name\":\"张三\",\"sex\":\"男\",\"hobby\":[{\"game\":\"足球\",\"desc\":\"任性\"},{\"game\":\"英雄联盟\",\"desc\":\"就是这么任性\"}]}}";
        JSONObject jsonObj = changeJsonObj("", JSONObject.parseObject(jsonStr),fliedFullPathMap);
        JSONObject jsonObj2 = (JSONObject) changeJson(jsonStr,fliedFullPathMap);
        String jsonObj3 = changeJsonToStr(jsonStr,fliedFullPathMap);
//        System.out.println("换值之前 》》 " + jsonStr);
//        System.out.println("换值结果 》》 " + jsonObj.toString());
//        System.out.println("换值结果 》》 " + jsonObj2.toString());
//        System.out.println("换值结果 》》 " + jsonObj3);

        String jsonStr2 ="[{\"motion\":\"足球\",\"desc\":\"任性\",\"user\":{\"name\":\"张三\",\"sex\":\"男\",\"hobby\":[{\"game\":\"足球\",\"desc\":\"任性\"},{\"game\":\"英雄联盟\",\"desc\":\"就是这么任性\"}]}},{\"motion\":\"英雄联盟\",\"desc\":\"就是这么任性\",\"user\":{\"name\":\"张三\",\"sex\":\"男\",\"hobby\":[{\"game\":\"足球\",\"desc\":\"任性\"},{\"game\":\"英雄联盟\",\"desc\":\"就是这么任性\"}]}}]";
//        JSONArray jsonArr = changeJsonArr("", JSONArray.parseArray(jsonStr2),fliedFullPathMap);
        JSONArray jsonArr2 = (JSONArray) changeJson(jsonStr2,fliedFullPathMap);
        String jsonArr3 = changeJsonToStr(jsonStr2,fliedFullPathMap);
        System.out.println("换值之前 Arr 》》 " + jsonStr2);
//        System.out.println("换值结果 Arr 》》 " + jsonArr.toString());
        System.out.println("换值结果 Arr 》》 " + jsonArr2.toString());
        System.out.println("换值结果 Arr 》》 " + jsonArr3);
    }
}
