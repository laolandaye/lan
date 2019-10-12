package com.lan.javase._7io._1file.示列;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExecGitInstall {

    public static void main(String[] args) throws Exception {
        getExecGitInstall();
    }

    public static void getExecGitInstall() {
        StringBuffer cmdTxt = new StringBuffer("cmd /c  \n ");
        List<String> poms = getGehuaPom();
        for (String pom : poms) {
            cmdTxt.append("  cd  ");
            cmdTxt.append(pom);
            cmdTxt.append(" && ");
            cmdTxt.append("  mvn clean package install -U ");
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

}
