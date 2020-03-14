package com.lan._1utils._2collectionmap;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListUtilsTest {

    @Test
    public void removeListDuplicateTest() {
        List<String> list  =   new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("aaa");
        list.add("aba");
        list.add("aaa");

        List newList = ListUtils.removeListDuplicate(list);
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

        List<String> newList = ListUtils.removeListDuplicate2(list);
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

        List newList =  ListUtils.removeListDuplicate3(list);

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

        List newList =  ListUtils.removeListDuplicate4(list);

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

        List newList =  ListUtils.removeListDuplicateOrder(list);

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

        List newList =  ListUtils.removeListDuplicateOrder2(list);

        System.out.println( "去重排序后的集合： " + newList);
    }

    @Test
    public void getMonthBetweenTest() {
        List<String> getMonthBetween = ListUtils.getMonthBetween("2018-01", "2019-02", "3");
        for (String s : getMonthBetween) {
            System.out.println(s);
        }
    }
    @Test
    public void getDateTimeBetweenTest() {
        List<String> getMonthBetween = ListUtils.getDateTimeBetween("2019-01-01 00:00:00", "2019-01-03 00:00:00", "1");
        for (String s : getMonthBetween) {
            System.out.println(s);
        }
    }

}
