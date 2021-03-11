package com.rsr.framework.core.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {
    static Pattern URL_PATTERN = Pattern.compile("^((http|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,6})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?$");

    public ValidationUtils() {
    }

    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        } else if (pObj == "") {
            return true;
        } else {
            if (pObj instanceof String) {
                if (((String)pObj).length() == 0) {
                    return true;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection)pObj).size() == 0) {
                    return true;
                }
            } else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isNotEmpty(Object pObj) {
        if (pObj == null) {
            return false;
        } else if (pObj == "") {
            return false;
        } else {
            if (pObj instanceof String) {
                if (((String)pObj).length() == 0) {
                    return false;
                }
            } else if (pObj instanceof Collection) {
                if (((Collection)pObj).size() == 0) {
                    return false;
                }
            } else if (pObj instanceof Map && ((Map)pObj).size() == 0) {
                return false;
            }

            return true;
        }
    }

    public static boolean isIE(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        boolean isIe = true;
        int index = userAgent.indexOf("msie");
        if (index == -1) {
            isIe = false;
        }

        return isIe;
    }

    public static boolean isChrome(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        boolean isChrome = true;
        int index = userAgent.indexOf("chrome");
        if (index == -1) {
            isChrome = false;
        }

        return isChrome;
    }

    public static boolean isFirefox(HttpServletRequest request) {
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        boolean isFirefox = true;
        int index = userAgent.indexOf("firefox");
        if (index == -1) {
            isFirefox = false;
        }

        return isFirefox;
    }

    public static int checkPassword(String pwd, int min, int max) {
        if (pwd.length() < min) {
            return 2;
        } else if (pwd.length() > max) {
            return 3;
        } else {
            String reg = "^\\d*$";
            Pattern p = Pattern.compile(reg);
            Matcher match = p.matcher(pwd);
            boolean flag = match.matches();
            if (flag) {
                return 4;
            } else {
                reg = "^[a-zA-Z]*$";
                Pattern p2 = Pattern.compile(reg);
                Matcher match2 = p2.matcher(pwd);
                flag = match2.matches();
                return flag ? 5 : 1;
            }
        }
    }

    public static boolean isUrl(String url) {
        if (isEmpty(url)) {
            return false;
        } else {
            Matcher mat = URL_PATTERN.matcher(url);
            return mat.find();
        }
    }
}

