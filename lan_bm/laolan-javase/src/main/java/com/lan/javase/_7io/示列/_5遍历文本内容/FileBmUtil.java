package com.lan.javase._7io.示列._5遍历文本内容;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileBmUtil {


    /**
     * 构建 vo:
     *      1.判断路径是否存在，如果存在，往后+1， 默认创建三个
     * @param fileName 文件全路径
     * @return
     */
    public static FileBmVo createFileBmVo(String fileName) {
        String [] files = fileName.split("\\\\");

        FileBmVo vo = new FileBmVo();
        vo.setFileName(fileName);
        vo.setName(files[files.length - 1]);
        vo.setFolder(vo.getFileName().replace(vo.getName(), ""));

        vo.setPrename(vo.getName().split("\\.")[0]);
        vo.setCopyFileName(vo.getPrename() + "2222.ftl");
        vo.setCopyFileName(vo.getFolder() + vo.getPrename() + "2222.ftl");

        List<FileBmVo.FileBmPo> cssPos = new ArrayList<>();
        int iCss = 4;
        for (int i = 1; i < iCss; i++) {
            String temp = (i==1) ? "" : ("" + i);

            FileBmVo.FileBmPo cssPo = new FileBmVo.FileBmPo();
            cssPo.setType(".css");
            cssPo.setName(vo.getPrename() + temp + ".css");
            cssPo.setFolder(vo.getFolder().replace("\\view\\", "\\res\\") + "css\\");
            String fileNameCss = cssPo.getFolder() + cssPo.getName();
            if(existsFile(fileNameCss)) {
                iCss += 1;
                continue;
            }
            cssPo.setFileName(fileNameCss);
            cssPo.setFileLinkScript("<link href=\"${mvcPath}/res/" +
                    cssPo.getFileName().split("\\\\res\\\\")[1].replaceAll("\\\\", "\\/")
                    + "\" type=\"text/css\" rel=\"stylesheet\"  /> \r\n");
            cssPos.add(cssPo);

        }
        vo.setFileBmPosCss(cssPos);

        List<FileBmVo.FileBmPo> jsPos = new ArrayList<>();
        int iJs = 4;
        for (int i = 1; i < iJs; i++) {
            String temp = (i==1) ? "" : ("" + i);

            FileBmVo.FileBmPo jsPo = new FileBmVo.FileBmPo();
            jsPo.setType(".js");
            jsPo.setName(vo.getPrename()+ temp + ".js");
            jsPo.setFolder(vo.getFolder().replace("\\view\\", "\\res\\") + "js\\");
            String fileNameJs = jsPo.getFolder() + jsPo.getName();
            if(existsFile(fileNameJs)) {
                iJs += 1;
                continue;
            }
            jsPo.setFileName(fileNameJs);
            jsPo.setFileLinkScript("<script type=\"text/javascript\" src=\"${mvcPath}/res/" +
                    jsPo.getFileName().split("\\\\res\\\\")[1].replaceAll("\\\\", "\\/")
                    + "\"></script> \r\n");
            jsPos.add(jsPo);
        }
        vo.setFileBmPosJs(jsPos);

        return vo;
    }

    /**
     * 文件复制，重命名
     * @param srcPathStr
     * @param desPathStr
     */
    public static void fileCopy(String srcPathStr, String desPathStr) {
        try
        {
            FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
            FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象
            File fileCss = new File(desPathStr);
            if(!fileCss.exists()){
                fileCss.createNewFile();
            }
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1)//循环读取数据
            {
                fos.write(datas,0,len);
            }
            System.out.println("复制重命名文件成功： " + desPathStr);
            fis.close();//释放资源
            fis.close();//释放资源
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 判断文件是否存在, true 代表存在
     */
    public static Boolean existsFile(String fileNameTemp) {
        File fileCss = new File(fileNameTemp);
        if(fileCss.exists()){
            return true;
        }
        return false;
    }

    /**
     * 构建新的 css 和 class
     * @param fileNameTemp
     * @return
     * @throws Exception
     */
    public static File createFileFolder(String fileNameTemp) throws Exception {
        File fileCss = new File(fileNameTemp);
        if(!fileCss.getParentFile().exists()){
            fileCss.getParentFile().mkdirs();
        }
        if(!fileCss.exists()){
            fileCss.createNewFile();
            System.out.println("创建文件成功： " + fileNameTemp);
        }
        return fileCss;
    }

    /**
     *  // 1.获得当前处理的流对象  2.添加回ftl 文件
     * @param dosCsses  输出流对象的list （css 或者 js）
     * @param dosFtl    ftl 输出流对象
     * @param fileBmPosCss  对象po（css 或者 js）
     */
    public static void getOutObj(List<OutputStreamWriter> dosCsses, OutputStreamWriter dosFtl, List<FileBmVo.FileBmPo> fileBmPosCss) throws Exception {
        for (int i = 0; i < fileBmPosCss.size(); i++) {
            FileBmVo.FileBmPo po = fileBmPosCss.get(i);
            if(!po.getFlag()) {
                dosFtl.write(strCompiled(po.getFileLinkScript(), po.getType()));
                dosCsses.add(new OutputStreamWriter(new FileOutputStream(FileBmUtil.createFileFolder(po.getFileName()))));
                po.setFlag(true);
                break;
            }
        }
    }

    /**
     *  link 编译后的脚本
     * @param src 原来的
     * @param type,  .css 或者 .js
     */
    public static String strCompiled(String src, String type) {
        System.out.println("替换前：" + src);
        String [] names =  src.substring(src.indexOf("/res/"),  src.indexOf(type)).substring("/res/".length()).split("/");
        String name = names[names.length - 1];
        src = src.replace(name + type, name + ".compiled" + type);
        System.out.println("替换后：" + src);
        return src;
    }

    /**
     * 删除指定文件
     * @param fileName 文件全路径
     */
    public static void delSecifiedFile(String fileName) {
        String [] fs = fileName.split("\\\\");
        String name = fs[fs.length - 1];    // 文件名

        File folder = new File(fileName.replace(name, "")); // 文件夹
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.getName().equals(name)) {
                System.gc();	//加上确保文件能删除，不然可能删不掉
                if(file.delete()){
                    System.out.println("删除文件成功： " + fileName);
                }else{
                    System.out.println("删除文件失败");
                }
            }
        }
    }
}
