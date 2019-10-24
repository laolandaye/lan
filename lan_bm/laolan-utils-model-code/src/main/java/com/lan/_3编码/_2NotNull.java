package com.lan._3编码;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 各种类型非空
public class _2NotNull {


    public static void main(String[] args) {
        // 字符串
        String key = "";
        if (StringUtils.isNotBlank(key)) {
        }

        //list
        List items = new ArrayList();
        if (CollectionUtils.isEmpty(items)){
        }

        // Map对象list
        Map extendCfgMap = new HashMap();
        getIfObjList(extendCfgMap.get("usageRateDateDif"));
        // if(null == extendCfgMap.get("userTimesUnit") || StringUtils.isEmpty(extendCfgMap.get("userTimesUnit").toString())) { extendCfgMap.put("userTimesUnit", "-1"); }



    }

    public static boolean getIfObjStr(Object str) {
        if(null == str || StringUtils.isEmpty(str.toString())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean getIfObjList(Object list) {
        if(null != list && CollectionUtils.isNotEmpty((List) list)) {
            return true;
        } else {
            return false;
        }
    }


}
