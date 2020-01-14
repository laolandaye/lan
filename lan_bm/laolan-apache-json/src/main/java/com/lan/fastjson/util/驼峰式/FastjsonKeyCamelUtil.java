package com.lan.fastjson.util.驼峰式;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 *
 * 对json数据key进行替换
 */
public class FastjsonKeyCamelUtil {

    public static JSONObject changeJsonObj(JSONObject jsonObj) {
        JSONObject resJson = new JSONObject();
        Set<String> keySet = jsonObj.keySet();
        for (String key : keySet) {
            String resKey = Uppercase4FirstLetter.convertToJava(key);
            try {
                JSONObject jsonobj1 = jsonObj.getJSONObject(key);
                resJson.put(resKey, changeJsonObj(jsonobj1));
            } catch (Exception e) {
                try {
                    JSONArray jsonArr = jsonObj.getJSONArray(key);
                    resJson.put(resKey, changeJsonArr(jsonArr));
                } catch (Exception x) {
                    resJson.put(resKey, jsonObj.get(key));
                }
            }
        }
        return resJson;
    }

    public static JSONArray changeJsonArr(JSONArray jsonArr) {
        JSONArray resJson = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            resJson.add(changeJsonObj(jsonObj));
        }
        return resJson;
    }

    public static JSON changeJson(String jsonStr) {
        try {
            return changeJsonArr(JSONArray.parseArray(jsonStr));
        } catch (JSONException je) {
            try {
                return changeJsonObj(JSONObject.parseObject(jsonStr));
            } catch (Exception e2) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static String changeJsonToStr(String jsonStr) {
        String res = null;
        try {
            res = changeJson(jsonStr).toJSONString();
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
        String jsonStr = "{\"USER_ID\":{\"name\":\"张三\",\"sex_hahah\":null,\"hobby_name\":[{\"game_lan_xixi\":null,\"desc\":\"任性\"},{\"game_lan_xixi\":\"英雄联盟\",\"desc\":\"就是这么任性\"}]}}";
        System.out.println("原值 》》 " + jsonStr);
        JSONObject jsonObj = FastjsonKeyCamelUtil.changeJsonObj(JSONObject.parseObject(jsonStr));
        System.out.println("换值结果 》》 " + jsonObj.toString());
        JSONObject jsonObj2 = (JSONObject) FastjsonKeyCamelUtil.changeJson(jsonStr);
        System.out.println("换值结果 jsonObj2 》》 " + jsonObj2.toString());
        String jsonObj3 = FastjsonKeyCamelUtil.changeJsonToStr(jsonStr);
        System.out.println("换值结果 jsonObj3 》》 " + jsonObj3);
    }

//    public static void main(String[] args) {
//         Map<String, Object> map = new HashMap<String,Object>();
//
//         String b = null;
//         Integer i = 1;
//
//         map.put("a", b);
//         map.put("b", i);
//
//         String listJson = JSON.toJSONString(map, SerializerFeature.WriteMapNullValue);
//        System.out.println(listJson);
//
//    }
}
