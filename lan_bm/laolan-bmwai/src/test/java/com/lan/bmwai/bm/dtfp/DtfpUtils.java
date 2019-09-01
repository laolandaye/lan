package test.bm.dtfp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DtfpUtils {

    /**
     * 返回去除"-" uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate(int indexLength) {
        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> map = null;
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < indexLength; i++) {
            map = new HashMap<>();
            // 第一个是 增量(时间)
            c.add(Calendar.MONTH, -(indexLength - 1 - i));// 月份减1
            Date resultDate = c.getTime(); // 结果
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            c.setTime(new Date());
            map.put("fill_date", sdf.format(resultDate));

            list.add(map);
        }
        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }

    /**
     * 生产填报时间2
     */
    public static List<Map<String, Object>> createFillDate2(String end,int indexLength) {
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
        for(Map emp:list){
            System.out.println(emp);
        }
        return list;
    }



    /**
     * 4.产生数据整数 含头不含尾
     */
    public static int getIntRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

}
