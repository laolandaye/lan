package com.kun.utils.export.csv;

import com.csvreader.CsvWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;

public class CsvExportUtil {
    /**
     * 导出csv文件(此方法可以保持到本地)
     * @author Jason K
     * @param fileName  导出完整路径不加后缀名
     * @param linkedHashMap 导出csv文件的标头数据
     * @param lists 导出csv文件的行数据
     */
    private static void csv(String fileName, LinkedHashMap<String, String> linkedHashMap, List<LinkedHashMap<String, String>> lists){
        char delimiter = ',';
        try {
            CsvWriter csvOutput = new CsvWriter(fileName+".csv", delimiter, Charset.forName("GBK"));
            csvOutput.setEscapeMode(CsvWriter.ESCAPE_MODE_DOUBLED);
            //第一行
            for(String key:linkedHashMap.keySet()){
                csvOutput.write(linkedHashMap.get(key));
            }
            csvOutput.endRecord();
            //循环写出数据
            for(LinkedHashMap<String, String> data : lists){
                for(String key:linkedHashMap.keySet()){
                    csvOutput.write(data.get(key));
                }
                csvOutput.endRecord();
            }
            csvOutput.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 导出csv文件
     * @author Jason K
     * @param out
     * @param linkedHashMap 头标题
     * @param lists 行数据
     */
    private static void csv(OutputStream out, LinkedHashMap<String, String> linkedHashMap, List<LinkedHashMap<String, String>> lists){
        char delimiter = ',';
        try {
            CsvWriter csvOutput = new CsvWriter(out, delimiter, Charset.forName("GBK"));
            csvOutput.setEscapeMode(CsvWriter.ESCAPE_MODE_DOUBLED);
            //第一行
            for(String key:linkedHashMap.keySet()){
                csvOutput.write(linkedHashMap.get(key));
            }
            csvOutput.endRecord();
            //循环写出数据
            for(LinkedHashMap<String, String> data : lists){
                for(String key:linkedHashMap.keySet()){
                    csvOutput.write(data.get(key));
                }
                csvOutput.endRecord();
            }
            csvOutput.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 下载文件
     * @author Jason K
     * @param response
     * @param fileName 文件名称
     * @param linkedHashMap 头标题
     * @param lists 行数据
     * @throws IOException
     */
    public static void exportFile(
            HttpServletResponse response,
            String fileName,
            LinkedHashMap<String, String> linkedHashMap,
            List<LinkedHashMap<String, String>> lists)
            throws IOException {
        response.setContentType("application/csv;charset=GBK");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
        try {
            response.setCharacterEncoding("GBK");
            CsvExportUtil.csv(response.getOutputStream(), linkedHashMap, lists);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

}

