package com.lan._1utils._4amapbmap.gaode;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HtjdUtils {

    //获得多边形中心 经纬
    public static String getCenterLnglat(String paths) {
        //处理坐标
        Object [][] c = (Object [][]) JSONArray.toArray(JSONArray.fromObject(paths));
        List<Double> lngs = new ArrayList<>();
        List<Double> lats = new ArrayList<>();
        for (Object[] objects : c) {
            lngs.add(Double.valueOf(objects[0].toString()));
            lats.add(Double.valueOf(objects[1].toString()));
        }
        return "[" + (Collections.min(lngs) + Collections.max(lngs))/2 +
                "," +
                (Collections.min(lats) + Collections.max(lats))/2 + "]";
    }

    // GPs 转高德
    public static List<double []> getGaodeLnglat2(String paths) {
        List<double []> result = new ArrayList<>();
        //处理坐标
        Object [][] c = (Object [][]) JSONArray.toArray(JSONArray.fromObject(paths));
        for (Object[] objects : c) {
            result.add(GPSUtil.gps84_To_Gcj02(Double.valueOf(objects[1].toString()), Double.valueOf(objects[0].toString())));
        }

        return result;
    }


    public static void main(String[] args) {
    }
}
