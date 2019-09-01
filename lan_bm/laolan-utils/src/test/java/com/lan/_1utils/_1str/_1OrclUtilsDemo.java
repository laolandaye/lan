package com.lan._1utils._1str;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _1OrclUtilsDemo {

    @Test
    public void arrToOrclInStrDemo() {
        System.out.println("*************arrToOrclInStrDemo*********************************************");
        Integer [] a = {1, 2 ,3};
        String aStr = _1OrclUtils.arrToOrclInStr(a);
        System.out.println(aStr);
        Integer [] a1 = {1};
        String aStr1 = _1OrclUtils.arrToOrclInStr(a1);
        System.out.println(aStr1);
    }

    @Test
    public void strToOrclInStrDemo() {
        System.out.println("**********strToOrclInStrDemo************************************************");
        String a = "1, 2 ,3";
        String aStr = _1OrclUtils.strToOrclInStr(a);
        System.out.println(aStr);
        String a1 = "1   ";
        String aStr1 = _1OrclUtils.strToOrclInStr(a1);
        System.out.println(aStr1);
    }

    @Test
    public void listMapToOrclInStrDemo() {
        System.out.println("**********listMapToOrclInStrDemo************************************************");
        List<Map<String, Object>> listMap = Arrays.asList(
                new HashMap<String, Object>(2){{ put("id",1); put("grid_code","v2"); }},
                new HashMap<String, Object>(2){{ put("id",2); put("grid_code","v22"); }},
                new HashMap<String, Object>(2){{ put("id",3); put("grid_code","v23"); }},
                new HashMap<String, Object>(2){{ put("id",4); put("grid_code","v24"); }},
                new HashMap<String, Object>(2){{ put("id",5); put("grid_code","v25"); }}
        );
        String aStr = _1OrclUtils.listMapToOrclInStr(listMap, "grid_code");
        System.out.println(aStr);
    }

    @Test
    public void listObjToOrclInStrDemo() {
        System.out.println("**********listObjToOrclInStrDemo************************************************");
        List<User> list = Arrays.asList(
                new User("第一位","用户1"),
                new User("第二位","用户2"),
                new User("第二位","用户3"),
                new User("第二位","用户3")
        );
        List<String> tableNames= list.stream().map(User::getMessage).collect(Collectors.toList());
        System.out.println(tableNames);
        String a = _1OrclUtils.listToOrclInStr(tableNames);
        System.out.println(a);
    }
}
