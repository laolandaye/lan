package _7io._1file.示列._1复制文件;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 *      原文：https://blog.csdn.net/xyphf/article/details/78276796
 */
public class CopyFileDemo{

    public static void main(String[] args)
    {
        File file = new File("d:/photos");
        File[] list = file.listFiles();

        // 如果目录下文件存在
        if (file.exists() && file.isDirectory())
        {
            for (int i = 0; i < list.length; i++)
            {
                //取文件名子存入name中
                String name = list[i].getName();
                // 截取.之前的字符串出来
                int index = name.indexOf(".");
                // 截取.JPG出来
                int index2 = name.lastIndexOf(".");
                String name3 = name.substring(index2);
                // 拼接字符串
                String newName = "2016-4-15-" + (i + 1) + name3;
                //重命名
                File dest = new File("d:/photos" + "/" + newName);
                list[i].renameTo(dest);
                System.out.println(dest.getName());
            }
        }
    }
}

