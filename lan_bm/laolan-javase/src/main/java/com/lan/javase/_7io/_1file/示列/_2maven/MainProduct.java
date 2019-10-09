package com.lan.javase._7io._1file.示列._2maven;

import java.util.*;

// 修改parent
/**
 * <spring.version>4.3.6.RELEASE</spring.version>
 * <jackson.version>2.8.7</jackson.version>
 * <spring.data.jpa.version>1.11.10.RELEASE</spring.data.jpa.version>
 */

public class MainProduct {

    public static void main(String[] args) throws Exception {
        String folderPath = "F:\\maven\\repository\\repository\\com\\kun";

        ArrayList<Object> filePaths = ScanFiles.scanFilesWithRecursion(folderPath);

        Set<String> delDirs = new HashSet<>();

        Set<String> delFiles = getDelFiles();

        for (Object arg : filePaths) {
            String filePath = (String) arg;
            String [] files = filePath.split("\\\\");
            String file = files[files.length - 1];  // 获得文件名
            // 判断是否是要删除的文件
            if(delFiles.contains(file)) {
                // 去重，只存储要删除的文件夹
                delDirs.add(filePath.replace(file, ""));
            }
        }

        System.out.println("开始：" + new Date());
        for (String delDir : delDirs) {
            System.out.println("删除文件夹：" + delDir);
            DelFiles.delAllFile(delDir);
        }
        System.out.println("结束：" + new Date());
    }

    private static Set<String> getDelFiles() {
        Set<String> delFiles = new HashSet<>();
        delFiles.add("parent-3.5.3.pom");
        delFiles.add("kun-web-3.5.3.jar");
        delFiles.add("kun-core-3.5.3.jar");
        delFiles.add("kun-crypto-3.5.3.jar");
        delFiles.add("kun-orm-3.5.3.jar");
        delFiles.add("kun-webframe-3.5.3.jar");
        delFiles.add("kun-webframe-entity-3.5.3.jar");
        delFiles.add("kun-webframe-notice-3.5.3.jar");
        delFiles.add("kun-entity-po-3.5.3.jar");
        delFiles.add("kun-workflow-parent-3.5.3.pom");
        delFiles.add("kun-workflow-3.5.3.jar");
        delFiles.add("kun-workflow-interface-3.5.3.jar");
        delFiles.add("kun-openapi-web-3.5.3.jar");
        delFiles.add("kun-openapi-entity-3.5.3.jar");
       delFiles.add("kun-datam-parent-3.5.3.pom");
        delFiles.add("kun-datam-3.5.3.jar");
        delFiles.add("kun-dam-parent-3.5.3.pom");
        delFiles.add("kun-dam-base-3.5.3.jar");
        delFiles.add("kun-dam-meta-3.5.3.jar");
        delFiles.add("kun-dam-quality-3.5.3.jar");
        delFiles.add("kun-dam-standard-3.5.3.jar");
        delFiles.add("kun-dp-dev-3.5.3.jar");
        delFiles.add("kun-dp-job-3.5.3.jar");
        delFiles.add("kun-dp-version-3.5.3.jar");
        delFiles.add("kun-metaca-parent-3.5.3.pom");
        delFiles.add("kun-metaca-parser-3.5.3.jar");
        delFiles.add("kun-monitor-parent-3.5.3.pom");
        delFiles.add("kun-monitor-web-3.5.3.jar");
        delFiles.add("kun-bigdata-components-interface-3.5.3.jar");
        delFiles.add("kun-parser-parent-3.5.3.pom");
        delFiles.add("kun-parser-3.5.3.jar");
        delFiles.add("kun-parser-druid-3.5.3.jar");
        delFiles.add("kun-dp-dataSwitching-3.5.3.jar");
        delFiles.add("kun-dp-po-3.5.3.jar");
        delFiles.add("kun-sec-parent-3.5.3.pom");
        delFiles.add("kun-sec-3.5.3.jar");
        delFiles.add("kun-sec-entity-po-3.5.3.jar");
        delFiles.add("kun-check-parent-3.5.3.pom");
        delFiles.add("kun-check-web-3.5.3.jar");
        delFiles.add("kun-check-entity-3.5.3.jar");
        delFiles.add("kun-apigate-parent-3.5.3.pom");
        delFiles.add("kun-apigate-dsmeta-3.5.3.jar");
        return delFiles;
    }

}
