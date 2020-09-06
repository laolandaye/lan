package com.lan._1utils._2collectionmap;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilTest {

    @Test
    public void removeListDuplicateTest() {
        List<String> list  =   new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList = ListUtil.removeListDuplicate(list);
        System.out.println( "去重后的集合： " + newList);
    }

    @Test
    public void removeListDuplicate2() {
        List<String> list  =   new  ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List<String> newList = ListUtil.removeListDuplicate2(list);
        System.out.println( "去重后的集合： " + newList);
    }

    @Test
    public void removeListDuplicate3() {
        List<String> list  =   new  ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList =  ListUtil.removeListDuplicate3(list);

        System.out.println( "去重后的集合： " + newList);
    }

    @Test
    public void removeListDuplicate4() {
        List<String> list  =   new  ArrayList<String>();
        list.add("ccc");
        list.add("bbb");
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList =  ListUtil.removeListDuplicate4(list);

        System.out.println( "去重后的集合： " + newList);
    }

    @Test
    public void removeListDuplicateOrder() {
        List<String> list  =   new  ArrayList<String>();
        list.add("ccc");
        list.add("bbb");
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList =  ListUtil.removeListDuplicateOrder(list);

        System.out.println( "去重排序后的集合： " + newList);
    }

    @Test
    public void removeListDuplicateOrder2() {
        List<String> list  =   new  ArrayList<String>();
        list.add("ccc");
        list.add("bbb");
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList =  ListUtil.removeListDuplicateOrder2(list);

        System.out.println( "去重排序后的集合： " + newList);
    }

    @Test
    public void createMonthBetweenTest() {
        List<String> getMonthBetween = ListUtil.createMonthBetween("2018-01", "2019-02", "3");
        for (String s : getMonthBetween) {
            System.out.println(s);
        }
    }
    @Test
    public void createDateTimeBetweenTest() {
        List<String> getMonthBetween = ListUtil.createDateTimeBetween("2019-01-01 00:00:00", "2019-01-03 00:00:00", "1");
        for (String s : getMonthBetween) {
            System.out.println(s);
        }
    }

}
