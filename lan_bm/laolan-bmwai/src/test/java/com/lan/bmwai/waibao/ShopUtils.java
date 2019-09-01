package waibao;

import net.sf.json.JSONArray;
import test.bm.htjd.utils.GPSUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ShopUtils {

    /**
     * 返回去除"-" uuid
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
    }

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

    /**
     * 4.产生数据整数 含头含尾
     */
    public static int getIntRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
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

    //数组中取取随机值
    public static Object getRandomList(List<Map<String, Object>> listMap, String key) {
        List<Object> list = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            list.add(map.get(key));
        }
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);

    }

    public static void main(String[] args) {
        String aa = "[[109.00016784667969,34.15784088992688],[108.9997386932373,34.15137742449933],[109.00059700012207,34.15173257280552],[109.00145530700684,34.1525138938225],[109.00231361389159,34.1525138938225],[109.00308609008789,34.15265595141268],[109.00420188903809,34.15258492264746],[109.00540351867676,34.15230080698924],[109.00729179382324,34.15201669037524],[109.01415824890135,34.150525062471694],[109.01510238647461,34.1496016605685],[109.01724815368652,34.147754826478064],[109.02119636535645,34.14526863990183],[109.02256965637207,34.145197604924505],[109.02420043945312,34.14690242789463],[109.02256965637207,34.148891344543],[109.02085304260254,34.150738153785014],[109.02291297912598,34.15222977792533],[109.02420043945312,34.15286903734995],[109.02557373046875,34.154005486602266],[109.02411460876465,34.154644732586455],[109.0220546722412,34.15535500022726],[109.01973724365233,34.15599423599647],[109.01673316955566,34.156349364888555],[109.01467323303223,34.156420390487774],[109.01424407958984,34.160255684132736],[109.01115417480469,34.15990057166893],[109.00823593139647,34.158977272273354],[109.00686264038086,34.15862215443291],[109.00540351867676,34.15826703509898],[109.00385856628418,34.15791191427159],[109.00016784667969,34.15784088992688]]";
        String bb = "[[108.99991035461426,34.14874928062113],[108.99836540222167,34.137809641178606],[109.00016784667969,34.137809641178606],[109.0034294128418,34.137738599930145],[109.0092658996582,34.13795172349637],[109.01115417480469,34.137809641178606],[109.01347160339354,34.137809641178606],[109.0154457092285,34.137809641178606],[109.01759147644043,34.137809641178606],[109.01922225952148,34.13802276456568],[109.02128219604492,34.13823588741527],[109.0228271484375,34.138520050378574],[109.02411460876465,34.139301493600534],[109.02308464050293,34.14150370380029],[109.02179718017578,34.14285341716618],[109.02033805847168,34.14391896511953],[109.01853561401367,34.144913464418],[109.01604652404785,34.146405191415575],[109.01424407958984,34.14732862826149],[109.01201248168945,34.148607216460306],[109.00368690490723,34.14882031261194],[108.99991035461426,34.14874928062113]]]";
        List<double []>  center = getGaodeLnglat2(bb);
        String gaodePath = "[";
        for (int i =0; i < center.size(); i++) {
            gaodePath += "[";
            gaodePath += center.get(i)[1];
            gaodePath += ",";
            gaodePath += center.get(i)[0];
            gaodePath += "]";
            if(i < center.size() - 1) {
                gaodePath += ",";
            } else {
            }
        }
        gaodePath += "]";
        System.out.println(gaodePath);
    }
}
