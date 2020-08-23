package com.lan.javase._7io.示列._6遍历文件内容;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;

public class FileBmUtil4 {


    /**
     * @param fileNameFtl 文件全路径
     * @return
     */
    public static FileBmVo4 createFileBmVo(String fileNameFtl) {
        String[] files = fileNameFtl.split("\\\\");
        String ftlName = files[files.length - 1];
        String ftlFolder = fileNameFtl.replace(ftlName, "");

        FileBmVo4 vo = new FileBmVo4();
        vo.setFileNameFtl(fileNameFtl);
        vo.setPathFtl(fileNameFtl.split("view\\\\")[0]);
        vo.setPrename(ftlName.split("\\.")[0]);

        vo.setPath(fileNameFtl.split("main\\\\resources\\\\view\\\\")[0]);
        vo.setFolder(ftlFolder.replace("main\\resources\\view", "pages") + vo.getPrename() + "\\");
        vo.setFileName(vo.getFolder() + "App.vue");
        vo.setFileNameMainJs(vo.getFolder() + "main.js");

        return vo;
    }

    /**
     * 创建指定文件
     * @param fileName 文件全路径
     */
    public static void createSecifiedFile(String fileName) {
        try {
            File f = new File(fileName);
            //判断父目录路径是否存在，即test.txt前的I:\a\b\
            if (f.getParentFile().exists()) {
                if(f.exists() && f.isFile()) {
                } else {
                    f.createNewFile();
                    System.out.println("创建文件成功：" + fileName);
                }
            } else {
                //不存在则创建父目录
                f.getParentFile().mkdirs();
                f.createNewFile();
                System.out.println("创建文件成功：" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件复制文本，逐行读取
     * @param srcPathStr
     * @param desPathStr
     */
    public static void fileCopy(String srcPathStr, String desPathStr) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcPathStr);//创建输入流对象
            createSecifiedFile(desPathStr);
            fos = new FileOutputStream(desPathStr); //创建输出流对象, 覆盖原来的文件
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            System.out.println("复制文件成功： " + desPathStr);
            fos.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 复制文件(图片), 覆盖
     * @param sourse
     * @param target
     */
    public static void fileImgCopy(String sourse,String target) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File(sourse));
            createSecifiedFile(target);
            // https://blog.csdn.net/hongxiao2016/article/details/88685936
            fos = new FileOutputStream(new File(target), true);    // 就这里不同

            byte[] a = new byte[1024*1024*4];
            int b = -1;

            //边读边写
            while((b = fis.read(a))!=-1) {
                fos.write(a,0,b);
            }
            System.out.println("复制img文件成功： " + target);
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取css/或者js文件添加到list
     */
    public static String getJsCssPath(String tempStr, String type) {
        List<String> mvcPaths = TempStrUtil4.dealMvcPath(tempStr);
        String res =  tempStr.substring(tempStr.indexOf(mvcPaths.get(0)),  tempStr.indexOf(type))
                    .replaceAll(mvcPaths.get(1)  + "/", "")
                    .replaceAll("/", "\\\\");
        return res + type;
    }

    /**
     * 读取css/或者js文件添加 是否需要可读
     */
    public static boolean ifLibFile(List<String> igs, String jsPath) {
        for (String ig : igs) {
            if(jsPath.indexOf(ig) > -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 读取css/或者js文件添加到list
     */
    public static void readJsCsslib(String jsPath, String pathFtl, List<String> mainJses) {
        String fileName = pathFtl + jsPath;
        String desPathStr = fileName.replace("main\\resources\\", "\\");
        fileCopy(fileName, desPathStr);

        mainJses.add("import '@/" + jsPath.replaceAll("\\\\", "/") + "'");
    }

    /**
     * 读取css文件添加到list
     */
    public static void readCss(String fileName, List<String> csses) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                csses.add(tempStr);
            }
        } catch (Exception e) {
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
     * 读取js文件添加到list
     */
    public static void readJs(String fileName, List<String> jses) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                tempStr = TempStrUtil4.dealJs(tempStr);
                if(StringUtils.isNotBlank(tempStr) && !"deleteALine".equals(tempStr)) {
                    jses.add(tempStr);
                }
            }
        } catch (Exception e) {
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
}
