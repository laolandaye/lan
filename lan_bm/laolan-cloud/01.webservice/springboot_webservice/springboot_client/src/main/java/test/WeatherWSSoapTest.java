package test;

import cn.com.webxml.*;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class WeatherWSSoapTest {

    public static void main(String[] args) {
        getUrl();   // 在线地址调用，使用接口
//        getFile();  // 本地文件调用，使用class
    }

    private static void getUrl() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WeatherWSSoap.class);
        factory.setAddress("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx/getSupportCityDataset");
        WeatherWSSoap weatherWSSoap = (WeatherWSSoap) factory.create();
//        ArrayOfString arr = weatherWSSoap.getRegionCountry();
//        if(null != arr) {
//            for (String message : arr.getString()) {
//                System.out.println(message);
//            }
//        }
//        System.out.println("******************");
//        ArrayOfString arr2 = weatherWSSoap.getRegionProvince();
//        if(null != arr2) {
//            for (String message : arr2.getString()) {
//                System.out.println(message);
//            }
//        }
//        System.out.println("******************");
        GetSupportCityDatasetResponse.GetSupportCityDatasetResult arr3 = weatherWSSoap.getSupportCityDataset("四川");
//        if(null != arr2) {
//            for (String message : arr2.getString()) {
//                System.out.println(message);
//            }
//        }
        System.out.println("******************");
//        ArrayOfString arr2 = weatherWSSoap.getWeather("", "");
//        if(null != arr2) {
//            for (String message : arr2.getString()) {
//                System.out.println(message);
//            }
//        }
    }

    private static void getFile() {
        WeatherWS weatherWS = new WeatherWS();
        WeatherWSSoap weatherWSSoap = weatherWS.getWeatherWSSoap();
        ArrayOfString arr = weatherWSSoap.getRegionCountry();
        if(null != arr) {
            for (String message : arr.getString()) {
                System.out.println(message);
            }
        }
    }
}
