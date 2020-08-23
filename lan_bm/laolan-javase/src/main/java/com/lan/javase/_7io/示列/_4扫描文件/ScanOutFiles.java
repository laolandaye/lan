package com.lan.javase._7io.示列._4扫描文件;

import java.io.File;

public class ScanOutFiles {
    /**
     * @param args
     */
    public static void main(String[] args) {
        File dir=new File("D:\\学习视频\\aaa\\八斗大数据培训第九期");
        listAll(dir,0);
    }
    public static void listAll(File dir,int count) {
        if(!dir.exists()){//健壮性判断
            throw new RuntimeException("目录不存在！！！");
        }
        System.out.println();
//        System.out.println(getSpace(count)+dir.getAbsolutePath());
        System.out.println(getSpace(count)+dir.getName());
        count++; //用于记录目录的级数。
        File files[]=dir.listFiles();
        for(File file:files){
            if(file.isDirectory())
                listAll(file,count);
            else
//                System.out.println(getSpace(count)+file.getAbsolutePath());
                System.out.println(getSpace(count)+file.getName());
        }
    }
    public static String getSpace(int count) { //根据目录级数产生空格。
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<count;i++){
            sb.append("  ");
        }
        return sb.toString();
    }
}