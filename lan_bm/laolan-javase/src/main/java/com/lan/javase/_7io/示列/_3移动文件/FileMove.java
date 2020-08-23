package com.lan.javase._7io.示列._3移动文件;

import java.io.File;

public class FileMove {

    public static void main(String[] args) {
        //遍历文件夹下所有单个文件尽心操作转移
        File filePath = new File("D:\\学习视频\\十次方(前后端)无加密 完整版\\微服务\\十次方微服务day10");
        File[] files = filePath.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(!files[i].isDirectory()){
                String temp = files[i].getAbsolutePath().replaceAll("【十次方禁广告闲聊讨论群863676367】", "");
                File newfile=new File(temp);
                System.out.println(newfile.getAbsolutePath());
                files[i].renameTo(newfile);
            }
        }
    }

}
