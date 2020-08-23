package com.lan.javase._7io.示列._6遍历文件内容;

import com.lan.javase._7io.示列._4扫描文件.ScanFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 读取文件预处理
 */
public class ReadFileContentPre4 {

    public static void main(String[] args) throws Exception {
        String folder = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web";

//        imgReadFolderPre(folder);  // 复制图片，运行一次后屏蔽
        txtReadFolderPre(folder);  // 调试，那些js 和 css 是需要合并到vue
    }

    /**
     */
    public static void imgReadFolderPre(String folderPath) throws Exception {
        ArrayList<Object> b1 = ScanFiles.scanFilesWithRecursion(folderPath);

        List<String> imgs = new ArrayList<>();
        imgs.add(".png");
        imgs.add(".svg");
        imgs.add(".jpg");
        for (Object arg : b1) {
            String fileName = (String) arg;
            if(fileName.indexOf("\\src\\main\\resources\\") > -1) {
                String img = fileName.substring(fileName.length() - 4, fileName.length());
                if((imgs.contains(img))) {
                    String desPathStr = fileName.replace("\\main\\resources\\", "\\");
                    FileBmUtil4.fileImgCopy(fileName, desPathStr);
                }
            }
        }

    }

    /**
     * 遍历文件夹
     * @param folderPath
     * @throws Exception
     */
    public static void txtReadFolderPre(String folderPath) throws Exception {
        List<String> igs = new ArrayList<>();
        igs.add("lib\\");   // 已忽略文件，运行第二步复制过去, 文件夹最好带 \\
        igs.add("summernote\\");
        igs.add("dps\\");

        ArrayList<Object> b1 = ScanFiles.scanFilesWithRecursion(folderPath);
        Map<String, Integer> count = new HashMap<>();

        for (Object arg : b1) {
            String fileName = (String) arg;
            // 同级 link, script 出现的出现的次数
            if(fileName.endsWith(".ftl") && (fileName.indexOf("\\src\\main\\resources\\view\\") > -1)) {
                readFileContentPre(fileName, count, igs);
            }
        }

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            System.out.println(
                    "igs.add(\""
                    + entry.getKey().replaceAll("\\\\", "\\\\\\\\")
                    + "\");"
                    + "   //" + entry.getValue() + "  " + entry.getKey().replaceAll("\\\\", "/")
            );
        }
    }


    public static void readFileContentPre(String fileName, Map<String, Integer> count, List<String> igs) throws Exception {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));

            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
//                /********* css file ***********/
                if(tempStr.trim().startsWith("<link")) {
                    // 1. 引用 src 开头 res
                    getCount(count, tempStr, ".css", igs);
                    continue;
                }
//                /********* css file end ***********/
//                /********* js file ***********/
                if(tempStr.trim().startsWith("<script")) {
                    if((tempStr.trim().indexOf("src") > 0)) {
                        // 1. 引用 src 开头 res
                        getCount(count, tempStr, ".js", igs);
                        continue;
                    }
                }
                /********* js file end ***********/

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 获得同级2数
     * @param count
     * @param tempStr
     */
    private static void getCount(Map<String, Integer> count, String tempStr, String type, List<String> igs) {
        String cssPath = FileBmUtil4.getJsCssPath(tempStr, type);
        for (String ig : igs) {
            if(cssPath.indexOf(ig) > -1) {
                return;
            }
        }
        if(count.get(cssPath) == null) {
            count.put(cssPath, 0);
        }
        count.put(cssPath, count.get(cssPath) + 1);
    }


}
