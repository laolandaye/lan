package com.kun.utils;

import java.util.UUID;

public class UuidUtils {

    /**
     * 返回去除"-" uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
