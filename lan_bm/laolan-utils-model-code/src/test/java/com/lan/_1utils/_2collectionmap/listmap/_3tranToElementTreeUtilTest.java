package com.lan._1utils._2collectionmap.listmap;

import org.junit.Test;

import java.util.*;

// 得到element需要的结构
public class _3tranToElementTreeUtilTest {

    @Test
    public void tranXiangtanTreeTest(){
        List<Map<String, Object>> results = _3tranToElementTreeUtil.tranXiangtanTree(TestStaticData.ls, new String[]{"company", "type", "car_no", "car"});

        for (Map<String,Object> result : results) {
            System.out.println(result);
        }
    }

    @Test
    public void tranTreeTest() {
        List<Map<String, Object>> res = new ArrayList<>();
        _3tranToElementTreeUtil.tranTree(TestStaticData.kunApiBodys, res, new String[]{"id", "parent_id"});
        System.out.println(res);
    }




    @Test
    public void add() {
        String id = "2c9849546e21bbe9016e3e81cf800052";
        List<Map<String, Object>> res = new ArrayList<>();
        _3tranToElementTreeUtil.getElementTreeById(id, TestStaticData.kunApiBodys, res,  new String[]{"id", "parent_id"});
        System.out.println(res);
    }


}
