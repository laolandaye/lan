package com.lan.bmwai.utils;

import java.util.Random;

public class CommonUtils {

    /**
     * 4.产生数据整数 含头不含尾
     */
    public static int getIntRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

}
