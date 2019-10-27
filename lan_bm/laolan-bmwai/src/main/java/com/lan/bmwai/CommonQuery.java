package com.lan.bmwai;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonQuery extends CommonJunitTest {

    // 所有
    public static List<Map<String, Object>> queryAll(String tab) {
        String sql = "  SELECT * FROM " + tab;
        List<Map<String, Object>> list = baseDao.queryForList(sql);
        return list;
    }


}
