package com.lan.javase._7io.示列;

import java.util.ArrayList;
import java.util.List;

public class MavenInstall {

    public static void getExecGitInstall(String con) {
        StringBuffer cmdTxt = new StringBuffer("cmd /c  \n ");
        List<String> poms = new ArrayList<>();
        String path = " -s \"D:/module/maven/apache-maven-3.6.1/conf/";
        if("gehua".equals(con)) {
            poms = getGehuaPom();
            path += "settings.xml\" ";
        } else if("bjsjfc".equals(con)) {
            poms = getBjsjfcPom();
            path += "settings-bjsjfc.xml\" ";
        } else if("spark".equals(con)) {
            poms = getSparkPom();
            path += "settings-spark.xml\" ";
        } else if("cqrcb".equals(con)) {
            poms = getCqrcbPom();
            path += "settings-nei.xml\" ";
        }
        for (String pom : poms) {
            cmdTxt.append("  cd  ");
            cmdTxt.append(pom);
            cmdTxt.append(" && ");
            cmdTxt.append("  mvn ");
            cmdTxt.append(path);
            cmdTxt.append("  clean package install -U ");
            if("spark".equals(con)) {
                cmdTxt.append(" -P dev,test ");
            }
            cmdTxt.append(" && ");
        }
        System.out.println(cmdTxt);
    }

    private final static String mavenRepository = "D:\\code\\bm";

    public static List<String> getGehuaPom() {
        List list = new ArrayList();
        // portal
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\parent ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\crypto ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\core ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\orm ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\web ");
        // portal
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe-entity ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\kun-webframe-notice ");
        // jslib
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\parent ");
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\uxlib ");
        // common-entity
        list.add(mavenRepository + "\\product\\common-entity\\v3.5.3\\common-entity\\kun-entity-parent ");
        list.add(mavenRepository + "\\product\\common-entity\\v3.5.3\\common-entity\\kun-entity-po ");
        // bigdata-components
        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-bigdata-components-parent ");
        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-bigdata-components-interface ");
        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-elasticsearch-components ");
        // kun-data-map
        list.add(mavenRepository + "\\product\\kun-data-map\\v3.5.3\\kun-data-map\\kun-datam-parent ");
        list.add(mavenRepository + "\\product\\kun-data-map\\v3.5.3\\kun-data-map\\kun-datam ");
        // kun-openapi 和 歌华
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-parent ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-sdk ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-entity ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-runtime ");
        list.add(mavenRepository + "\\javaee\\bjgh\\gehua\\kun-openapi-runtime-bjgh ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-boot ");
        // dp-de
        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-parent ");
        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-version ");
        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-job ");
        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-dev ");
        // kun-dp-dataSwitching
        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dp-ds-parent ");
        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dp-po ");
        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dataSwitching ");
        // kun-dp-dataSwitching
        list.add(mavenRepository + "\\product\\openportal\\sdtransportal\\openportal\\kun-open-parent ");
        list.add(mavenRepository + "\\product\\openportal\\sdtransportal\\openportal\\kun-open-web ");
        // 歌华
        list.add(mavenRepository + "\\javaee\\bjgh\\gehua\\bjgh-deploy");
        return list;
    }

    public static List<String> getBjsjfcPom() {
        List list = new ArrayList();
        // portal
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\parent ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\crypto ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\core ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\orm ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\web ");
        // portal
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe-entity ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\kun-webframe-notice ");
        // jslib
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\parent ");
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\uxlib ");
        // common-entity
        list.add(mavenRepository + "\\product\\common-entity\\v3.5.3\\common-entity\\kun-entity-parent ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\kun-entity-po ");
        // bigdata-components
        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-bigdata-components-parent ");
        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-bigdata-components-interface ");
//        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-elasticsearch-components ");
        // kun-data-map
        list.add(mavenRepository + "\\product\\kun-data-map\\v3.5.3\\kun-data-map\\kun-datam-parent ");
        list.add(mavenRepository + "\\product\\kun-data-map\\v3.5.3\\kun-data-map\\kun-datam ");
        // kun-openapi 和 北京数据蜂巢
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-parent ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-sdk ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-entity ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-runtime ");
        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\kun-openapi-runtime-bjsjfc ");
//        list.add(mavenRepository + "\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-boot ");
        // dp-de
        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-parent ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\kun-dp-version ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\kun-dp-job ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\kun-dp-dev ");
        // kun-dp-dataSwitching
        list.add(mavenRepository + "\\product\\openportal\\sdtransportal\\openportal\\kun-open-parent ");
        list.add(mavenRepository + "\\product\\openportal\\sdtransportal\\openportal\\kun-open-web ");
        // kun-dp-dataSwitching
        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dp-ds-parent ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dp-po ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dataSwitching ");
        // dam
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-parent ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-meta ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-base ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-dqc ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-quality ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-quapp ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-quality ");
        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\dam-standard ");

        list.add(mavenRepository + "\\javaee\\fengchao\\kun-bjsjfc\\kun-dp-deploy ");
        return list;
    }

    public static List<String> getSparkPom() {
        List list = new ArrayList();
        // portal
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\parent ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\crypto ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\core ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\orm ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\web ");
        // portal
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe-entity ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\kun-webframe-notice ");
        // jslib
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\parent ");
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\uxlib ");
        // 实时 spark
        list.add(mavenRepository + "\\product\\kun-streaming\\feat-add-spark\\kun-streaming\\kun-streaming-parent ");
        list.add(mavenRepository + "\\product\\kun-streaming\\feat-add-spark\\kun-streaming\\kun-streaming-core ");
        list.add(mavenRepository + "\\product\\kun-streaming\\feat-add-spark\\kun-streaming\\kun-streaming-spark ");

        return list;
    }

    public static List<String> getCqrcbPom() {
        List list = new ArrayList();
        // portal
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\parent ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\crypto ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\core ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\orm ");
        list.add(mavenRepository + "\\product\\framework\\v3.5.3\\framework\\web ");
        // portal
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe-entity ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\webframe ");
        list.add(mavenRepository + "\\product\\portal\\v3.5.3\\portal\\kun-webframe-notice ");
        // jslib
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\parent ");
        list.add(mavenRepository + "\\product\\jslib\\v3.5.3\\jslib\\uxlib ");
        // common-entity
//        list.add(mavenRepository + "\\product\\common-entity\\v3.5.3\\common-entity\\kun-entity-parent ");
//        list.add(mavenRepository + "\\product\\common-entity\\v3.5.3\\common-entity\\kun-entity-po ");
           // dp-de
//        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-parent ");
//        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-version ");
//        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-job ");
//        list.add(mavenRepository + "\\product\\dp-dev\\v3.5.3\\dp-dev\\kun-dp-dev ");
        // bigdata-components
//        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-bigdata-components-parent ");
//        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-bigdata-components-interface ");
//        list.add(mavenRepository + "\\product\\bigdata-components\\v3.5.3\\bigdata-components\\kun-elasticsearch-components ");
        // kun-openapi 和 歌华
        list.add(mavenRepository + "\\product\\kun-openapi\\4.0.0-nokun\\kun-openapi\\kun-openapi-parent ");
        list.add(mavenRepository + "\\product\\kun-openapi\\4.0.0-nokun\\kun-openapi\\kun-openapi-sdk ");
        list.add(mavenRepository + "\\product\\kun-openapi\\4.0.0-nokun\\kun-openapi\\kun-openapi-entity ");
//        list.add(mavenRepository + "\\product\\kun-openapi-cloud\\v3.5.3\\kun-openapi-cloud\\rpc-log\\kun-openapi-interface ");
//        list.add(mavenRepository + "\\product\\kun-openapi-cloud\\v3.5.3\\kun-openapi-cloud\\rpc-log\\kun-openapi-log ");
        list.add(mavenRepository + "\\product\\kun-openapi\\4.0.0-nokun\\kun-openapi\\kun-openapi-runtime ");
        list.add(mavenRepository + "\\product\\kun-openapi\\4.0.0-nokun\\kun-openapi\\kun-openapi-web ");
//        list.add(mavenRepository + "\\product\\kun-openapi\\4.0.0\\kun-openapi\\kun-openapi-boot ");
//        list.add(mavenRepository + "\\product\\kun-openapi-cloud\\v3.5.3\\kun-openapi-cloud\\kun-openapi-eureka ");
//        list.add(mavenRepository + "\\product\\kun-openapi-cloud\\v3.5.3\\kun-openapi-cloud\\kun-openapi-zuul ");
//        list.add(mavenRepository + "\\product\\kun-openapi-cloud\\v3.5.3\\kun-openapi-cloud\\kun-openapi-cloud-provider ");
        // kun-data-map
//        list.add(mavenRepository + "\\product\\kun-data-map\\v3.5.3\\kun-data-map\\kun-datam-parent ");
//        list.add(mavenRepository + "\\product\\kun-data-map\\v3.5.3\\kun-data-map\\kun-datam ");
//        // kun-dp-dataSwitching
//        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dp-ds-parent ");
//        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dp-po ");
//        list.add(mavenRepository + "\\product\\dp-dataSwitching\\v3.5.3\\dp-dataSwitching\\dataSwitching ");
        // openportal
        list.add(mavenRepository + "\\product\\openportal\\4.0.0-nokun\\openportal\\kun-open-parent ");
        list.add(mavenRepository + "\\product\\openportal\\4.0.0-nokun\\openportal\\kun-open-web ");
        list.add(mavenRepository + "\\product\\openportal\\4.0.0-nokun\\openportal\\kun-open-deploy");
        return list;
    }
}
