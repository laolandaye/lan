package com.lan.bmwai.utils;

import java.util.*;

public class BjghUtils {

    /**
     * 返回去除"-" uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }


}
