package com.lan.javase._7io.示列._6遍历文件内容;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class TempStrUtil4 {

    /**
     * solr检索时，转换特殊字符
     *
     * @param s 需要转义的字符串
     * @return 返回转义后的字符串
     */
    public static String escapeQueryChars(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        //查询字符串一般不会太长，挨个遍历也花费不了多少时间
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // These characters are part of the query syntax and must be escaped
            if (c == '\\' || c == '+' || c == '-' || c == '!' || c == '(' || c == ')'
                    || c == ':' || c == '^'	|| c == '[' || c == ']' || c == '\"'
                    || c == '{' || c == '}' || c == '~' || c == '*' || c == '?'
                    || c == '|' || c == '&' || c == ';' || c == '/' || c == '.'
                    || c == '$' || Character.isWhitespace(c)) {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 获得转移后的 mvcPath
     */
    public static List<String> dealMvcPath(String tempStr) {
        String mvcPath = tempStr.substring(tempStr.indexOf("${"), tempStr.indexOf("}") + 1);
        return Arrays.asList(new String []{mvcPath,  escapeQueryChars(mvcPath)});
    }

    public static String dealFtl(String tempStr) {
        if(tempStr.indexOf("mvcPath") > -1) {
            System.out.println("替换前" + tempStr);
            tempStr = tempStr.replaceAll(TempStrUtil4.dealMvcPath(tempStr).get(1), "@");
            System.out.println("替换后" + tempStr);
        }
        return tempStr;
    }

    public static String dealCss(String tempStr) {
        if(tempStr.trim().indexOf("background") > -1 && tempStr.trim().indexOf("url") > -1 && tempStr.trim().indexOf("background") < tempStr.trim().indexOf("url")) {
            System.out.println("替换前" + tempStr);
            tempStr = "export default " + tempStr.substring(tempStr.indexOf("Vue") + 3, tempStr.length());
            System.out.println("替换后" + tempStr);
            return tempStr;
        }
        return tempStr;
    }

    public static String dealJs(String tempStr) {
        if(tempStr.trim().indexOf("new") > -1 && tempStr.trim().indexOf("Vue") > -1 && tempStr.trim().indexOf("new") < tempStr.trim().indexOf("Vue")) {
            System.out.println("new Vue替换前" + tempStr);
            tempStr = "export default " + tempStr.substring(tempStr.indexOf("Vue") + 3, tempStr.length());
            System.out.println("new Vue替换后" + tempStr);
            return tempStr;
        }
        if(tempStr.trim().startsWith("el") && tempStr.trim().indexOf("#") > -1) {
            System.out.println("el删除" + tempStr);
            return "deleteALine";
        }
        if(tempStr.indexOf("mvcPath") > -1) {
            System.out.println("mvcPath替换前" + tempStr);
            tempStr = tempStr.replaceAll("\\\"", "'");
            List<String> mvcPaths = TempStrUtil4.dealMvcPath(tempStr);
            // 处理这种情况  contextPath: "${mvcPath}",
            if(tempStr.trim().indexOf("\'" + mvcPaths.get(0) + "\'") > 0) {
                tempStr = tempStr.replaceAll("'" + mvcPaths.get(1) + "'", "this.\\$kun.getContextPath()");
            }
            // 处理这种情况 	url: "${mvcPath}/api/openapi/apiCdrCount",
            if(tempStr.trim().indexOf("\'" + mvcPaths.get(0) + "/") > 0) {
                tempStr = tempStr.replaceAll("'" + mvcPaths.get(1)  + "/", "this.\\$kun.getContextPath() + '/");
            }
            System.out.println("mvcPath替换后" + tempStr);
        }
        return tempStr;
    }

}
