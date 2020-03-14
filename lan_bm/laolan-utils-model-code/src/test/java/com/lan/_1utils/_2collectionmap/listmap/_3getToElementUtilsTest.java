package com.lan._1utils._2collectionmap.listmap;

import com.lan._1utils._2collectionmap.ListMapUtils;
import net.sf.json.JSON;
import org.junit.Test;

import java.util.*;

// 得到element需要的结构
public class _3getToElementUtilsTest {

    @Test
    public void getElementTreeTest() {
        List<Map<String, Object>> res = new ArrayList<>();
        _3getToElementUtils.getElementTree(TestStaticData.kunApiBodys, res, new String[]{"id", "parent_id"});
        System.out.println(res);
    }

    @Test
    public void getElementTreeDemo(){
        List<Map<String, Object>> ls = TestStaticData.ls;
        List<Map<String, Object>> results =
                _3getToElementUtils.getElementTree(ls, new String[]{"company", "type", "car_no", "car"});

        for (Map<String,Object> result : results) {
            System.out.println(result);
        }
    }


    @Test
    public void add() {
        String id = "2c9849546e21bbe9016e3e81cf800052";
        List<Map<String, Object>> res = new ArrayList<>();
        _3getToElementUtils.getElementTreeById(id, TestStaticData.kunApiBodys, res,  new String[]{"id", "parent_id"});
        System.out.println(res);
    }


}
