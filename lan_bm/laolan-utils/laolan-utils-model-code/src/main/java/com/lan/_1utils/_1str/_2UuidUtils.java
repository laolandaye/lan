package com.lan._1utils._1str;

import java.util.UUID;

public class _2UuidUtils {

    /**
     * 返回去除"-" uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
