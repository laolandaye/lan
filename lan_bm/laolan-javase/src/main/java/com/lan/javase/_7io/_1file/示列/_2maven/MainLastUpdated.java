package com.lan.javase._7io._1file.示列._2maven;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainLastUpdated {

    public static void main(String[] args) throws Exception {
        System.currentTimeMillis();

        String folderPath = "D:\\module\\maven\\repository\\repository";

        ArrayList<Object> filePaths = ScanFiles.scanFilesWithRecursion(folderPath);

        Set<String> delDirs = new HashSet<>();

        List<String> delFiles = new ArrayList<>();
//        delFiles.add("lastUpdated");
//        delFiles.add("_remote.repositories");
        delFiles.add("4.3.6.RELEASE");



        for (Object arg : filePaths) {
            String filePath = (String) arg;
            String [] files = filePath.split("\\\\");
            String file = files[files.length - 1];  // 获得文件名
            // 判断是否是要删除的文件
            for (String delFile : delFiles) {
                if(file.contains(delFile)) {
                    // 去重，只存储要删除的文件夹
                    delDirs.add(filePath.replace(file, ""));
                }
            }
        }

        for (String delDir : delDirs) {
            System.out.println("删除文件夹：" + delDir);
            DelFiles.delAllFile(delDir);
        }
        System.out.println("ok");
    }

}
