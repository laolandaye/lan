package com.lan.javase._11反射.反射注解应用;

import java.io.*;
import java.util.HashSet;

public class FileRead {
    public static void main(String[] args) {
        File fileStation = new File("C:\\Users\\ZMC\\Desktop\\lxd\\station.txt");
        String filePath = "C:\\Users\\ZMC\\Desktop\\result\\";
        HashSet<String> set = new HashSet<>();
        try {
            BufferedReader bfs = new BufferedReader(new FileReader(fileStation));
            String str;
            // 按行读取字符串
            while ((str = bfs.readLine()) != null) {
                String[] split = str.split(" ");
                set.add(split[0]);
            }
            bfs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File fileP = new File("C:\\Users\\ZMC\\Desktop\\lxd");
        File[] files = fileP.listFiles();
        for (File file: files){
            if (file.isDirectory()) {
                File[] fileList = file.listFiles();
                for (File filed : fileList) {
                    File result = new File(filePath + filed.getName());
                    try {
                        BufferedReader bf = new BufferedReader(new FileReader(filed));
                        BufferedWriter writer = new BufferedWriter(new FileWriter(result));
                        String str;
                        // 按行读取字符串
                        while ((str = bf.readLine()) != null) {
                            String[] split = str.split(" ");
                            if (set.contains(split[0])) {
                                writer.write(str);
                                writer.newLine();
                            }
                        }
                        writer.close();
                        bf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
