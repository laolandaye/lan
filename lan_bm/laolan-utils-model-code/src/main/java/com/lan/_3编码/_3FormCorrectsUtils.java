package com.lan._3编码;

/**
 * 由于表单后台纠正
 */
public class _3FormCorrectsUtils {

    // 判断需求是否有，为否，就清空其他字段
    //     Patent Financing Labor Policy
    public static String getIfDemand(String strIf, String strDemand) {
        if("0".equals(strIf)) {
            strDemand =  "";
        }
        return strDemand;
    }
}
