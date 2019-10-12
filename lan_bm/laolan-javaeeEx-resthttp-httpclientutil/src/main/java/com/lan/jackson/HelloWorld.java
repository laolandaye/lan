package com.lan.jackson;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @company
 * @author superboo
 * @version 3.0
 * @date 2014-5-21 上午09:45:51
 */
public class HelloWorld {
    public String getJsonStr1() {
        String jsonStr = "{"
                + "\"list\": ["
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463881.shtml\","
                + "\"title\": \"秦皇岛市城市管理局数字城管GPS车辆管理系统公开采购\","
                + "\"time\": \"2014-05-20 18:58:50\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463904.shtml\","
                + "\"title\": \"宁晋县公安局贾家口派出所工程\","
                + "\"time\": \"2014-05-20 18:52:10\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463903.shtml\","
                + "\"title\": \"宁晋县公安局耿庄桥派出所工程\","
                + "\"time\": \"2014-05-20 18:49:30\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463838.shtml\","
                + "\"title\": \"河北医科大学食堂油改气项目公开招标公告\","
                + "\"time\": \"2014-05-20 18:03:48\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463601.shtml\","
                + "\"title\": \"青龙满族自治县教育局2014年中小学校舍安全工程公开招标公告\","
                + "\"time\": \"2014-05-20 17:06:12\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463590.shtml\","
                + "\"title\": \"清东陵孝陵主神道一孔、五孔、七孔石桥修缮工程监理公开招标公告\","
                + "\"time\": \"2014-05-20 17:03:24\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463668.shtml\","
                + "\"title\": \"晋州市教育局中小学计算机采购项目公开招标公告\","
                + "\"time\": \"2014-05-20 16:58:58\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463666.shtml\","
                + "\"title\": \"固安县畜牧兽医局检测设备采购公开招标公告\","
                + "\"time\": \"2014-05-20 16:57:07\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463446.shtml\","
                + "\"title\": \"宁晋县2014年高标准基本农田建设项目勘查设计公开招标公告\","
                + "\"time\": \"2014-05-20 16:44:24\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463210.shtml\","
                + "\"title\": \"内丘县教育局幼儿园玩教具采购项目公开招标公告\","
                + "\"time\": \"2014-05-20 16:30:42\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463186.shtml\","
                + "\"title\": \"黄骅市2014年农桥重建工程（第一批）公开招标公告\","
                + "\"time\": \"2014-05-20 16:27:35\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463134.shtml\","
                + "\"title\": \"柏乡县2014年农业综合开发高标准农田建设项目公开招标公告\","
                + "\"time\": \"2014-05-20 16:22:06\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463048.shtml\","
                + "\"title\": \"石家庄市第一中学东校区校园数字广播系统改造工程预中标公告\","
                + "\"time\": \"2014-05-20 16:13:10\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462957.shtml\","
                + "\"title\": \"唐山市园林绿化管理局唐山植物园建成区景观提升工程一标段河北省政府采购公开招标公告\","
                + "\"time\": \"2014-05-20 15:42:20\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462954.shtml\","
                + "\"title\": \"唐山市园林绿化管理局唐山植物园建成区景观提升工程一标段监理河北省政府采购公开招标公告\","
                + "\"time\": \"2014-05-20 15:40:55\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462951.shtml\","
                + "\"title\": \"唐山市园林绿化管理局唐山市城市道路景观提升绿化工程设计河北省政府采购公开招标公告\","
                + "\"time\": \"2014-05-20 15:39:35\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462600.shtml\","
                + "\"title\": \"唐山市第二医院采购悬吊式数字放射成像系统公开招标公告\","
                + "\"time\": \"2014-05-20 14:48:55\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462528.shtml\","
                + "\"title\": \"石家庄市体育运动学校体育器材采购项目公开招标公告\","
                + "\"time\": \"2014-05-20 14:33:12\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462243.shtml\","
                + "\"title\": \"河北宏信招标有限公司河北师范大学生物制造中试平台设备采购（二次）公开招标公告\","
                + "\"time\": \"2014-05-20 12:03:08\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462156.shtml\","
                + "\"title\": \"河北化工医药职业技术学院电机与电气控制及物化实验仪器设备采购公开招标公告\","
                + "\"time\": \"2014-05-20 11:19:41\"" + "}" + "],"
                + "\"page\": \"总18163篇 共909页 \"" + "}";
        return jsonStr;
    }

    public String getJsonStr2() {
        String jsonStr = "{"
                + "\"list\": ["
                + "{"
                + "\"url\": \"http://www.ceshi.gov.cn\","
                + "\"title\": \"这是一个我测试加入的东西\","
                + "\"time\": \"2014-05-21 18:58:50\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463881.shtml\","
                + "\"title\": \"秦皇岛市城市管理局数字城管GPS车辆管理系统公开采购\","
                + "\"time\": \"2014-05-20 18:58:50\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463904.shtml\","
                + "\"title\": \"宁晋县公安局贾家口派出所工程\","
                + "\"time\": \"2014-05-20 18:52:10\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463903.shtml\","
                + "\"title\": \"宁晋县公安局耿庄桥派出所工程\","
                + "\"time\": \"2014-05-20 18:49:30\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463838.shtml\","
                + "\"title\": \"河北医科大学食堂油改气项目公开招标公告\","
                + "\"time\": \"2014-05-20 18:03:48\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463601.shtml\","
                + "\"title\": \"青龙满族自治县教育局2014年中小学校舍安全工程公开招标公告\","
                + "\"time\": \"2014-05-20 17:06:12\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463590.shtml\","
                + "\"title\": \"清东陵孝陵主神道一孔、五孔、七孔石桥修缮工程监理公开招标公告\","
                + "\"time\": \"2014-05-20 17:03:24\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463668.shtml\","
                + "\"title\": \"晋州市教育局中小学计算机采购项目公开招标公告\","
                + "\"time\": \"2014-05-20 16:58:58\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463666.shtml\","
                + "\"title\": \"固安县畜牧兽医局检测设备采购公开招标公告\","
                + "\"time\": \"2014-05-20 16:57:07\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463446.shtml\","
                + "\"title\": \"宁晋县2014年高标准基本农田建设项目勘查设计公开招标公告\","
                + "\"time\": \"2014-05-20 16:44:24\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463210.shtml\","
                + "\"title\": \"内丘县教育局幼儿园玩教具采购项目公开招标公告\","
                + "\"time\": \"2014-05-20 16:30:42\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463186.shtml\","
                + "\"title\": \"黄骅市2014年农桥重建工程（第一批）公开招标公告\","
                + "\"time\": \"2014-05-20 16:27:35\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463134.shtml\","
                + "\"title\": \"柏乡县2014年农业综合开发高标准农田建设项目公开招标公告\","
                + "\"time\": \"2014-05-20 16:22:06\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3463048.shtml\","
                + "\"title\": \"石家庄市第一中学东校区校园数字广播系统改造工程预中标公告\","
                + "\"time\": \"2014-05-20 16:13:10\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462957.shtml\","
                + "\"title\": \"唐山市园林绿化管理局唐山植物园建成区景观提升工程一标段河北省政府采购公开招标公告\","
                + "\"time\": \"2014-05-20 15:42:20\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462954.shtml\","
                + "\"title\": \"唐山市园林绿化管理局唐山植物园建成区景观提升工程一标段监理河北省政府采购公开招标公告\","
                + "\"time\": \"2014-05-20 15:40:55\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462951.shtml\","
                + "\"title\": \"唐山市园林绿化管理局唐山市城市道路景观提升绿化工程设计河北省政府采购公开招标公告\","
                + "\"time\": \"2014-05-20 15:39:35\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462600.shtml\","
                + "\"title\": \"唐山市第二医院采购悬吊式数字放射成像系统公开招标公告\","
                + "\"time\": \"2014-05-20 14:48:55\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462528.shtml\","
                + "\"title\": \"石家庄市体育运动学校体育器材采购项目公开招标公告\","
                + "\"time\": \"2014-05-20 14:33:12\""
                + "},"
                + "{"
                + "\"url\": \"http://www.ccgp.gov.cn/cggg/dfbx/gkzb/201405/t20140520_3462243.shtml\","
                + "\"title\": \"河北宏信招标有限公司河北师范大学生物制造中试平台设备采购（二次）公开招标公告\","
                + "\"time\": \"2014-05-20 12:03:08\"" + "}],"
                + "\"page\": \"总18164篇 共909页 \"" + "}";
        return jsonStr;
    }
    @SuppressWarnings("rawtypes")
    public void  analysisJson(Object objJson){
        //如果obj为json数组
        if(objJson instanceof JSONArray){
            JSONArray objArray = (JSONArray)objJson;
            for (int i = 0; i < objArray.size(); i++) {
                analysisJson(objArray.get(i));
            }
        }
        //如果为json对象
        else if(objJson instanceof JSONObject){
            JSONObject jsonObject = (JSONObject)objJson;
            Iterator it = jsonObject.keys();
            while(it.hasNext()){
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if(object instanceof JSONArray){
                    JSONArray objArray = (JSONArray)object;
                    analysisJson(objArray);
                }
                //如果key中是一个json对象
                else if(object instanceof JSONObject){
                    analysisJson((JSONObject)object);
                }
                //如果key中是其他
                else{
                    System.out.println("["+key+"]:"+object.toString()+" ");
                }
            }
        }
    }
    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld();
        Object returenObj = "{\"msg\":\"获取监测对象成功\",\"data\":[{\"type\":22,\"accountCode\":\"22_RMTWZ_BJDCOMCN_00_110000\",\"mediaName\":\"北京日报\",\"origId\":2,\"origName\":\"北京日报\"},{\"type\":1,\"accountCode\":\"1_WB_BJRBDKHD_00_110000\",\"mediaName\":\"北京日报\",\"origId\":2,\"origName\":\"北京日报\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGRIBAOKEHUDUAN_00_110000\",\"mediaName\":\"北京日报\",\"origId\":2,\"origName\":\"北京日报\"},{\"type\":2,\"accountCode\":\"2_WX_BJRB_00_110000\",\"mediaName\":\"北京日报\",\"origId\":2,\"origName\":\"北京日报\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGRIBAO_00_110000\",\"mediaName\":\"北京日报\",\"origId\":2,\"origName\":\"北京日报\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGRIBAO_00_110000\",\"mediaName\":\"北京日报\",\"origId\":2,\"origName\":\"北京日报\"},{\"type\":2,\"accountCode\":\"2_WX_XJB_00_110000\",\"mediaName\":\"新京报\",\"origId\":3,\"origName\":\"新京报\"},{\"type\":16,\"accountCode\":\"16_APP_XINJINGBAO_00_110000\",\"mediaName\":\"新京报\",\"origId\":3,\"origName\":\"新京报\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJNEWSCOMCN_00_110000\",\"mediaName\":\"新京报\",\"origId\":3,\"origName\":\"新京报\"},{\"type\":21,\"accountCode\":\"21_JRTT_XINJINGBAO_00_110000\",\"mediaName\":\"新京报\",\"origId\":3,\"origName\":\"新京报\"},{\"type\":1,\"accountCode\":\"1_WB_XJB_00_110000\",\"mediaName\":\"新京报\",\"origId\":3,\"origName\":\"新京报\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_XINJINGBAO_00_110000\",\"mediaName\":\"新京报\",\"origId\":3,\"origName\":\"新京报\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGQINGNIANBAO_00_110000\",\"mediaName\":\"北京青年报\",\"origId\":4,\"origName\":\"北青报\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIQING_00_110000\",\"mediaName\":\"北青\",\"origId\":4,\"origName\":\"北青报\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGTOUTIAO_00_110000\",\"mediaName\":\"北京头条\",\"origId\":4,\"origName\":\"北青报\"},{\"type\":2,\"accountCode\":\"2_WX_BJQNB_00_110000\",\"mediaName\":\"北京青年报\",\"origId\":4,\"origName\":\"北青报\"},{\"type\":1,\"accountCode\":\"1_WB_BJQNB_00_110000\",\"mediaName\":\"北京青年报\",\"origId\":4,\"origName\":\"北青报\"},{\"type\":1,\"accountCode\":\"1_WB_BJDST_00_110000\",\"mediaName\":\"北京电视台\",\"origId\":1,\"origName\":\"北京台\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BTVXINWEN_00_110000\",\"mediaName\":\"BTV新闻\",\"origId\":1,\"origName\":\"北京台\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGSHIJIAN_00_110000\",\"mediaName\":\"北京时间\",\"origId\":1,\"origName\":\"北京台\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BMNNETCN_00_110000\",\"mediaName\":\"北京广播电视台\",\"origId\":1,\"origName\":\"北京台\"},{\"type\":21,\"accountCode\":\"21_JRTT_BTVBEIJINGDIANSHITAI_00_110000\",\"mediaName\":\"BTV新闻\",\"origId\":1,\"origName\":\"北京台\"},{\"type\":2,\"accountCode\":\"2_WX_BJDST_00_110000\",\"mediaName\":\"北京电视台\",\"origId\":1,\"origName\":\"北京台\"},{\"type\":2,\"accountCode\":\"2_WX_PGCM_00_110000\",\"mediaName\":\"平广传媒\",\"origId\":19,\"origName\":\"平谷\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_PINGGURONGMEIZHONGXIN_00_110000\",\"mediaName\":\"平谷融媒中心\",\"origId\":19,\"origName\":\"平谷\"},{\"type\":1,\"accountCode\":\"1_WB_BJPG_00_110000\",\"mediaName\":\"北京平谷\",\"origId\":19,\"origName\":\"平谷\"},{\"type\":21,\"accountCode\":\"21_JRTT_PINGGURONGMEIZHONGXIN_00_110000\",\"mediaName\":\"平谷融媒中心\",\"origId\":19,\"origName\":\"平谷\"},{\"type\":16,\"accountCode\":\"16_APP_YUNSHANGPINGGU_00_110000\",\"mediaName\":\"云上平谷\",\"origId\":19,\"origName\":\"平谷\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJPGGOVCN_00_110000\",\"mediaName\":\"平谷区政府\",\"origId\":19,\"origName\":\"平谷\"},{\"type\":21,\"accountCode\":\"21_JRTT_JINGXIMENTOUGOU_00_110000\",\"mediaName\":\"京西门头沟\",\"origId\":15,\"origName\":\"门头沟\"},{\"type\":16,\"accountCode\":\"16_APP_MENTOUGOURONGMEI_00_110000\",\"mediaName\":\"门头沟融媒\",\"origId\":15,\"origName\":\"门头沟\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJMTGGOVCN_00_110000\",\"mediaName\":\"门头沟区政府\",\"origId\":15,\"origName\":\"门头沟\"},{\"type\":2,\"accountCode\":\"2_WX_MTGRM_00_110000\",\"mediaName\":\"门头沟融媒\",\"origId\":15,\"origName\":\"门头沟\"},{\"type\":1,\"accountCode\":\"1_WB_JXMTG_00_110000\",\"mediaName\":\"京西门头沟\",\"origId\":15,\"origName\":\"门头沟\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_JINGXIMENTOUGOU_00_110000\",\"mediaName\":\"京西门头沟\",\"origId\":15,\"origName\":\"门头沟\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGDONGCHENG_00_110000\",\"mediaName\":\"北京东城\",\"origId\":5,\"origName\":\"东城\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGDONGCHENG_00_110000\",\"mediaName\":\"北京东城\",\"origId\":5,\"origName\":\"东城\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGDONGCHENG_00_110000\",\"mediaName\":\"北京东城\",\"origId\":5,\"origName\":\"东城\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJDCHGOVCN_00_110000\",\"mediaName\":\"数字东城\",\"origId\":5,\"origName\":\"东城\"},{\"type\":2,\"accountCode\":\"2_WX_BJDC_00_110000\",\"mediaName\":\"北京东城\",\"origId\":5,\"origName\":\"东城\"},{\"type\":1,\"accountCode\":\"1_WB_BJDC_00_110000\",\"mediaName\":\"北京东城\",\"origId\":5,\"origName\":\"东城\"},{\"type\":1,\"accountCode\":\"1_WB_BJXC_00_110000\",\"mediaName\":\"北京西城\",\"origId\":6,\"origName\":\"西城\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGXICHENG_00_110000\",\"mediaName\":\"北京西城\",\"origId\":6,\"origName\":\"西城\"},{\"type\":2,\"accountCode\":\"2_WX_BJXC_00_110000\",\"mediaName\":\"北京西城\",\"origId\":6,\"origName\":\"西城\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJXCHGOVCN_00_110000\",\"mediaName\":\"西城区政府\",\"origId\":6,\"origName\":\"西城\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGXICHENG_00_110000\",\"mediaName\":\"北京西城\",\"origId\":6,\"origName\":\"西城\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGZHAOYANG_00_110000\",\"mediaName\":\"北京朝阳\",\"origId\":18,\"origName\":\"朝阳\"},{\"type\":1,\"accountCode\":\"1_WB_BJCY_00_110000\",\"mediaName\":\"北京朝阳\",\"origId\":18,\"origName\":\"朝阳\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJCHYGOVCN_00_110000\",\"mediaName\":\"北京朝阳新闻网\",\"origId\":18,\"origName\":\"朝阳\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_ZHAOYANGQUNZHONG_00_110000\",\"mediaName\":\"朝阳群众\",\"origId\":18,\"origName\":\"朝阳\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGZHAOYANGGUANWEI_00_110000\",\"mediaName\":\"北京朝阳官微\",\"origId\":18,\"origName\":\"朝阳\"},{\"type\":2,\"accountCode\":\"2_WX_CYGDRM_00_110000\",\"mediaName\":\"朝阳广电融媒\",\"origId\":18,\"origName\":\"朝阳\"},{\"type\":21,\"accountCode\":\"21_JRTT_HAIDIANRONGMEI_00_110000\",\"mediaName\":\"海淀融媒\",\"origId\":13,\"origName\":\"海淀\"},{\"type\":16,\"accountCode\":\"16_APP_ZSHD_00_110000\",\"mediaName\":\"掌上海淀\",\"origId\":13,\"origName\":\"海淀\"},{\"type\":2,\"accountCode\":\"2_WX_HDRM_00_110000\",\"mediaName\":\"海淀融媒\",\"origId\":13,\"origName\":\"海淀\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJHDNETCOM_00_110000\",\"mediaName\":\"海淀网\",\"origId\":13,\"origName\":\"海淀\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_HAIDIANRONGMEI_00_110000\",\"mediaName\":\"海淀融媒\",\"origId\":13,\"origName\":\"海淀\"},{\"type\":1,\"accountCode\":\"1_WB_HDRM_00_110000\",\"mediaName\":\"海淀融媒\",\"origId\":13,\"origName\":\"海淀\"},{\"type\":1,\"accountCode\":\"1_WB_BJFT_00_110000\",\"mediaName\":\"北京丰台\",\"origId\":12,\"origName\":\"丰台\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGFENGTAI_00_110000\",\"mediaName\":\"北京丰台\",\"origId\":12,\"origName\":\"丰台\"},{\"type\":2,\"accountCode\":\"2_WX_FTRMDJ_00_110000\",\"mediaName\":\"丰台融媒党建\",\"origId\":12,\"origName\":\"丰台\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_KANJIANFENGTAI_00_110000\",\"mediaName\":\"看见丰台\",\"origId\":12,\"origName\":\"丰台\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGFENGTAI_00_110000\",\"mediaName\":\"北京丰台\",\"origId\":12,\"origName\":\"丰台\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_SJSGOVCN_00_110000\",\"mediaName\":\"石景山新闻网\",\"origId\":20,\"origName\":\"石景山\"},{\"type\":1,\"accountCode\":\"1_WB_BJSSJS_00_110000\",\"mediaName\":\"北京市石景山\",\"origId\":20,\"origName\":\"石景山\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_SHIJINGSHANRONGMEI_00_110000\",\"mediaName\":\"石景山融媒\",\"origId\":20,\"origName\":\"石景山\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGSHIJINGSHAN_00_110000\",\"mediaName\":\"北京石景山\",\"origId\":20,\"origName\":\"石景山\"},{\"type\":2,\"accountCode\":\"2_WX_BJSJS_00_110000\",\"mediaName\":\"北京石景山\",\"origId\":20,\"origName\":\"石景山\"},{\"type\":16,\"accountCode\":\"16_APP_RONGHUIFUZHONGXIN_00_110000\",\"mediaName\":\"融汇副中心\",\"origId\":20,\"origName\":\"石景山\"},{\"type\":2,\"accountCode\":\"2_WX_BJTZFB_00_110000\",\"mediaName\":\"北京通州发布\",\"origId\":9,\"origName\":\"通州\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_DAYUNTONGZHOUCOM_00_110000\",\"mediaName\":\"大运通州网\",\"origId\":9,\"origName\":\"通州\"},{\"type\":21,\"accountCode\":\"21_JRTT_TONGZHOUFABU_00_110000\",\"mediaName\":\"通州发布\",\"origId\":9,\"origName\":\"通州\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_TONGZHOUFABU_00_110000\",\"mediaName\":\"通州发布\",\"origId\":9,\"origName\":\"通州\"},{\"type\":1,\"accountCode\":\"1_WB_BJTZFB_00_110000\",\"mediaName\":\"北京通州发布\",\"origId\":9,\"origName\":\"通州\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGCHANGPING_00_110000\",\"mediaName\":\"北京昌平\",\"origId\":7,\"origName\":\"昌平\"},{\"type\":2,\"accountCode\":\"2_WX_BJCP_00_110000\",\"mediaName\":\"北京昌平\",\"origId\":7,\"origName\":\"昌平\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGCHANGPING_00_110000\",\"mediaName\":\"北京昌平\",\"origId\":7,\"origName\":\"昌平\"},{\"type\":1,\"accountCode\":\"1_WB_CPB_00_110000\",\"mediaName\":\"昌平报\",\"origId\":7,\"origName\":\"昌平\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGCHANGPING_00_110000\",\"mediaName\":\"北京昌平\",\"origId\":7,\"origName\":\"昌平\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_CPRT_00_110000\",\"mediaName\":\"昌平广播电视网\",\"origId\":7,\"origName\":\"昌平\"},{\"type\":2,\"accountCode\":\"2_WX_DXRM_00_110000\",\"mediaName\":\"大兴融媒\",\"origId\":11,\"origName\":\"大兴\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGDAXING_00_110000\",\"mediaName\":\"北京大兴\",\"origId\":11,\"origName\":\"大兴\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJDXGOVCN_00_110000\",\"mediaName\":\"大兴区政府\",\"origId\":11,\"origName\":\"大兴\"},{\"type\":21,\"accountCode\":\"21_JRTT_DAXINGRONGMEI_00_110000\",\"mediaName\":\"大兴融媒\",\"origId\":11,\"origName\":\"大兴\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_DAXINGRONGMEI_00_110000\",\"mediaName\":\"大兴融媒\",\"origId\":11,\"origName\":\"大兴\"},{\"type\":1,\"accountCode\":\"1_WB_BJDX_00_110000\",\"mediaName\":\"北京大兴\",\"origId\":11,\"origName\":\"大兴\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_FUNHILLMEDIACOM_00_110000\",\"mediaName\":\"房山广电传媒网\",\"origId\":16,\"origName\":\"房山\"},{\"type\":1,\"accountCode\":\"1_WB_BJFS_00_110000\",\"mediaName\":\"北京房山\",\"origId\":16,\"origName\":\"房山\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGFANGSHAN_00_110000\",\"mediaName\":\"北京房山\",\"origId\":16,\"origName\":\"房山\"},{\"type\":2,\"accountCode\":\"2_WX_BJFS_00_110000\",\"mediaName\":\"北京房山\",\"origId\":16,\"origName\":\"房山\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGFANGSHAN_00_110000\",\"mediaName\":\"北京房山\",\"origId\":16,\"origName\":\"房山\"},{\"type\":16,\"accountCode\":\"16_APP_BEIJINGSHUNYI_00_110000\",\"mediaName\":\"北京顺义\",\"origId\":10,\"origName\":\"顺义\"},{\"type\":2,\"accountCode\":\"2_WX_SGCM_00_110000\",\"mediaName\":\"顺广传媒\",\"origId\":10,\"origName\":\"顺义\"},{\"type\":1,\"accountCode\":\"1_WB_BJSY_00_110000\",\"mediaName\":\"北京顺义\",\"origId\":10,\"origName\":\"顺义\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGSHUNYI_00_110000\",\"mediaName\":\"北京顺义\",\"origId\":10,\"origName\":\"顺义\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGSHUNYI_00_110000\",\"mediaName\":\"北京顺义\",\"origId\":10,\"origName\":\"顺义\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJSYTVCOM_00_110000\",\"mediaName\":\"顺广传媒\",\"origId\":10,\"origName\":\"顺义\"},{\"type\":1,\"accountCode\":\"1_WB_BJYQ_00_110000\",\"mediaName\":\"北京延庆\",\"origId\":21,\"origName\":\"延庆\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJYQGOVCN_00_110000\",\"mediaName\":\"延庆区政府\",\"origId\":21,\"origName\":\"延庆\"},{\"type\":21,\"accountCode\":\"21_JRTT_YANQINGRONGMEI_00_110000\",\"mediaName\":\"延庆融媒\",\"origId\":21,\"origName\":\"延庆\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_YANQINGXIAOKEDOU_00_110000\",\"mediaName\":\"延庆小可抖\",\"origId\":21,\"origName\":\"延庆\"},{\"type\":2,\"accountCode\":\"2_WX_BJYQ_00_110000\",\"mediaName\":\"北京延庆\",\"origId\":21,\"origName\":\"延庆\"},{\"type\":21,\"accountCode\":\"21_JRTT_YIJUMIYUN_00_110000\",\"mediaName\":\"宜居密云\",\"origId\":14,\"origName\":\"密云\"},{\"type\":1,\"accountCode\":\"1_WB_YJMY_00_110000\",\"mediaName\":\"宜居密云\",\"origId\":14,\"origName\":\"密云\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_MYMIYUN_00_110000\",\"mediaName\":\"My密云\",\"origId\":14,\"origName\":\"密云\"},{\"type\":2,\"accountCode\":\"2_WX_MYRM_00_110000\",\"mediaName\":\"密云融媒\",\"origId\":14,\"origName\":\"密云\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BJMYGOVCN_00_110000\",\"mediaName\":\"密云区政府\",\"origId\":14,\"origName\":\"密云\"},{\"type\":16,\"accountCode\":\"16_APP_YIJUMIYUN_00_110000\",\"mediaName\":\"宜居密云\",\"origId\":14,\"origName\":\"密云\"},{\"type\":1,\"accountCode\":\"1_WB_LSHRXMT_00_110000\",\"mediaName\":\"恋上怀柔新媒体\",\"origId\":17,\"origName\":\"怀柔\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_HUAIRTVCOM_00_110000\",\"mediaName\":\"怀柔融媒\",\"origId\":17,\"origName\":\"怀柔\"},{\"type\":16,\"accountCode\":\"16_APP_HUAIROURONGMEI_00_110000\",\"mediaName\":\"怀柔融媒\",\"origId\":17,\"origName\":\"怀柔\"},{\"type\":2,\"accountCode\":\"2_WX_HRRM_00_110000\",\"mediaName\":\"怀柔融媒\",\"origId\":17,\"origName\":\"怀柔\"},{\"type\":21,\"accountCode\":\"21_JRTT_HUAIROURONGMEI_00_110000\",\"mediaName\":\"怀柔融媒\",\"origId\":17,\"origName\":\"怀柔\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_HUAIROURONGMEI_00_110000\",\"mediaName\":\"怀柔融媒\",\"origId\":17,\"origName\":\"怀柔\"},{\"type\":16,\"accountCode\":\"16_APP_SHANGYICHENG_00_110000\",\"mediaName\":\"尚亦城\",\"origId\":8,\"origName\":\"亦庄\"},{\"type\":2,\"accountCode\":\"2_WX_BJYZ_00_110000\",\"mediaName\":\"北京亦庄\",\"origId\":8,\"origName\":\"亦庄\"},{\"type\":21,\"accountCode\":\"21_JRTT_BEIJINGYIZHUANG_00_110000\",\"mediaName\":\"北京亦庄\",\"origId\":8,\"origName\":\"亦庄\"},{\"type\":1,\"accountCode\":\"1_WB_BJYZ_00_110000\",\"mediaName\":\"北京亦庄\",\"origId\":8,\"origName\":\"亦庄\"},{\"type\":20,\"accountCode\":\"20_DOUYIN_BEIJINGYIZHUANG_00_110000\",\"mediaName\":\"北京亦庄\",\"origId\":8,\"origName\":\"亦庄\"},{\"type\":22,\"accountCode\":\"22_RMTWZ_BEIJINGGOVCN_00_110000\",\"mediaName\":\"亦庄经济开发区\",\"origId\":8,\"origName\":\"亦庄\"}],\"success\":true,\"ext\":{}}";

                //        JSONObject jsonObject = JSONObject.fromObject(hw.getJsonStr1());
        JSONObject jsonObject = JSONObject.fromObject(returenObj);
        hw.analysisJson(jsonObject);
    }
}

