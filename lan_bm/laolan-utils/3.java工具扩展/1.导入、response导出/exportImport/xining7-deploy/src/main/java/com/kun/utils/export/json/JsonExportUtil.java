package com.kun.utils.export.json;

import com.kun.utils.export.excel.ExcelExportBean;
import com.kun.utils.export.excel.ExcelExportUtil;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExportUtil {

    public static void main(String[] args) {
        int age = 23;

        List<String> aihao = new ArrayList<String>();
        aihao.add("pashan");
        aihao.add("movies");

        Map map = new HashMap();
        map.put("aihao", aihao);

        JSONObject jo = JSONObject.fromObject(map);
        createJsonFile(jo.toString(), "d:/", "111");
    }


    /**
     * 生成.json格式文件(java方式，了解一下就行)
     */
    private static boolean createJsonFile(String jsonString, String filePath, String fileName) {
        // 标记文件生成是否成功
        boolean flag = true;
        // 拼接文件完整路径
        String fullPath = filePath + File.separator + fileName + ".json";
        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();


            // 格式化json字符串
            jsonString = JsonFormatTool.formatJson(jsonString);


            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }


        // 返回是否成功的标记
        return flag;
    }

    //发送响应流方法
    private static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 下载操作
    public static void exportFile(
            JsonExportBean jeBean,
            HttpServletResponse response
    ) {
        response.setCharacterEncoding("UTF-8");
        //响应到客户端
        try {
            // 格式化json字符串
            String jsonStringFormat = JsonFormatTool.formatJson(jeBean.getJsonString());
            // 写回js
            response.getWriter().write(jsonStringFormat);
            setResponseHeader(response, jeBean.getFileName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


