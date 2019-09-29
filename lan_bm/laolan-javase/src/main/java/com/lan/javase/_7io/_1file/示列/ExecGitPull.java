package com.lan.javase._7io._1file.示列;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecGitPull {

    public static void main(String[] args) throws Exception {
        getExeGitPull();
    }

    public static void getExeGitPull() {
        StringBuffer cmdTxt = new StringBuffer("cmd /c \n ");
        List<Map<String, Object>> poms = getGehuaPom();
        for (Map<String, Object> pom : poms) {
            cmdTxt.append("  cd  " + pom.get("dir"));
            cmdTxt.append(" && ");
            cmdTxt.append("  git checkout " + pom.get("branch"));
            cmdTxt.append(" && ");
            cmdTxt.append("  git pull ");
            cmdTxt.append(" && ");
        }
        System.out.println(cmdTxt);
    }

    private final static String mavenRepository = "/d/code/bm";

    public static List<Map<String, Object>> getGehuaPom() {
        List<Map<String, Object>> list = new ArrayList();
        // framework
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/framework/v3.5.3/framework "); }});
        // portal
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/portal/v3.5.3/portal "); }});
        // jslib
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/jslib/v3.5.3/jslib "); }});
        // common-entity
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/common-entity/v3.5.3/common-entity "); }});
        // bigdata-components
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/bigdata-components/v3.5.3/bigdata-components "); }});
        // kun-data-map
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/kun-data-map/v3.5.3/kun-data-map "); }});
        // kun-openapi 和 歌华
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/kun-openapi/v3.5.3/kun-openapi "); }});
        // dp-de
        list.add( new HashMap<String, Object>(){{ put("branch", "3.5.3-dev"); put("dir", mavenRepository + "/product/dp-dev/v3.5.3/dp-dev "); }});
        // kun-dp-dataSwitching
        list.add( new HashMap<String, Object>(){{ put("branch", "v3.5.3"); put("dir", mavenRepository + "/product/dp-dataSwitching/v3.5.3/dp-dataSwitching "); }});
        // openportal
        list.add( new HashMap<String, Object>(){{ put("branch", "1.0.1-dev"); put("dir", mavenRepository +  "/product/openportal/sdtransportal/openportal "); }});
        // 歌华
        list.add( new HashMap<String, Object>(){{ put("branch", "master"); put("dir", mavenRepository + "/javaee/bjgh/gehua "); }});
        return list;
    }
}