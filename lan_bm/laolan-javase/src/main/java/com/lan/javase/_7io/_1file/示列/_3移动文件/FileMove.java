package com.lan.javase._7io._1file.示列._3移动文件;

import java.io.File;

public class FileMove {

    public static void main(String[] args) {
        //遍历文件夹下所有单个文件尽心操作转移
        File file = new File("C:\\Users\\Administrator\\Desktop\\022\\");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(!files[i].isDirectory()){
                String temp = "";
                if(i < 9) {
                    temp = "0" + (i + 1);
                } else {
                    temp = "" + (i + 1);
                }
//                files[i].renameTo(new File("C:\\Users\\Administrator\\Desktop\\023\\" + files[i].getName() + i +  ".png"));
                files[i].renameTo(new File("C:\\Users\\Administrator\\Desktop\\023\\" + "20180616" + temp +  ".png"));
            }
        }
        //直接通过文件夹重命名
//        File file1 = new File("C:\\Users\\ddd\\Documents\\Tencent Files\\ddd\\Image\\");
    }

}
