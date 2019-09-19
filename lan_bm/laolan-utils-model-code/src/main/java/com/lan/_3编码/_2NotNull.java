package com.lan._3编码;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
    }

}
