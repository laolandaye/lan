package com.lan._1utils._2collectionmap;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListUtils {


    /**
     * set去重
     *
     * @param list
     * @return
     */
    public static List removeListDuplicate(List<String> list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        set.addAll(list);
        newList.addAll(set);
        return newList;
    }

    /**
     * set去重2：缩减为一行
     *
     * @param list
     * @return
     */
    public static List removeListDuplicate2(List<String> list) {
        return new ArrayList(new HashSet(list));
    }

    /**
     * set集合去重，不打乱顺序
     *
     * @param list
     * @return
     */
    public static List removeListDuplicate3(List<String> list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (String cd : list) {
            if (set.add(cd)) {
                newList.add(cd);
            }
        }
        return newList;
    }

    /**
     * 遍历后判断赋给另一个list集合
     *
     * @param list
     * @return
     */
    public static List removeListDuplicate4(List<String> list) {
        List newList = new ArrayList();
        for (String cd : list) {
            if (!newList.contains(cd)) {
                newList.add(cd);
            }
        }
        return newList;
    }

    /**
     * tree去重排序：缩减为一行
     *
     * @param list
     * @return
     */
    public static List removeListDuplicateOrder(List<String> list) {
        Set set = new TreeSet();
        List newList = new ArrayList();
        set.addAll(list);
        newList.addAll(set);
        return newList;
    }

    /**
     * tree去重排序：缩减为一行
     *
     * @param list
     * @return
     */
    public static List removeListDuplicateOrder2(List<String> list) {
        return new ArrayList(new TreeSet(list));
    }


    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate2(String end, int indexLength) {
        List<Map<String, Object>> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Map<String, Object> map = null;
        Calendar c = Calendar.getInstance();

        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            try {
                c.setTime(sdf.parse(end));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - i));// 月份减1
            Date resultDate = c.getTime(); // 结果

            c.setTime(new Date());
            map.put("fill_date", sdf.format(resultDate));

            list.add(map);
        }
        for (Map emp : list) {
            System.out.println(emp);
        }
        return list;
    }

    /**
     * 生成fill_date
     * @param minDate  2019-01  2019-01-26
     * @param maxDate  2019-06  2019-09-26
     * @param type  1(全部) 2 含头不含尾 3 含尾不含头
     * @return
     */
    public static List<String> getMonthBetween(String minDate, String maxDate, String type)  {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        try {
            min.setTime(sdf.parse(minDate));
            max.setTime(sdf.parse(maxDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        // 判断生成 日期类型
        switch (type) {
            case "1":
                break;
            case "2":
                result.remove(result.size() - 1);
                break;
            case "3":
                result.remove(0);
                break;
            default:
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> getMonthBetween = getMonthBetween("2019-01", "2019-02", "2");
        for (String s : getMonthBetween) {
            System.out.println(s);
        }
    }

}
