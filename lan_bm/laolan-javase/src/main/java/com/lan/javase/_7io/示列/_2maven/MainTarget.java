package com.lan.javase._7io.示列._2maven;

import com.lan.javase._7io.示列._2删除文件.DelFiles;
import com.lan.javase._7io.示列._4扫描文件.ScanFiles;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// 删除所有target

/**
 * <spring.version>4.3.6.RELEASE</spring.version>
 * <jackson.version>2.8.7</jackson.version>
 * <spring.data.jpa.version>1.11.10.RELEASE</spring.data.jpa.version>
 */

public class MainTarget {

    public static void main(String[] args) throws Exception {
        String folderPath = "D:\\code\\bm\\product";

        ArrayList<Object> filePaths = ScanFiles.scanFilesWithRecursion(folderPath);

        Set<String> delDirs = new HashSet<>();

        for (Object arg : filePaths) {
            String filePath = (String) arg;
            if(filePath.contains("\\target\\")) {
                // 去重，只存储要删除的文件夹
                delDirs.add(filePath.split("\\\\target")[0] + "\\target");
            }

        }

        System.out.println("开始：" + new Date());
        for (String delDir : delDirs) {
            System.out.println("删除文件夹：" + delDir);
            DelFiles.delAllFile(delDir);
        }
        System.out.println("结束：" + new Date());
    }

}
