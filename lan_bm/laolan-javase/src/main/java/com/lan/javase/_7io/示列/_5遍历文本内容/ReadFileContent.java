package com.lan.javase._7io.示列._5遍历文本内容;

import com.lan.javase._7io.示列._4扫描文件.ScanFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFileContent {

    /**
     * 遍历文件夹
     * @param folderPath
     * @throws Exception
     */
    public static void dealReadFolder(String folderPath, List<String> writeFolder) throws Exception {
        ArrayList<Object> b1 = ScanFiles.scanFilesWithRecursion(folderPath);
        for (Object arg : b1) {
            String fileName = (String) arg;
            for (String s : writeFolder) {
                if(fileName.indexOf("\\" + s + "\\") > 0) {
                    if(fileName.endsWith(".ftl") && (fileName.indexOf("\\view\\") > 0)) {
                        dealReadFileContent(fileName);
                    }
                }
            }
        }
    }

    /**
     * 1.创建 vo createFileBmVo
     * 2.复制重命名文件
     * 3.读取文件
     *
     * 4.删除复制重命名文件
     * @param fileName
     */
    public static void dealReadFileContent(String fileName) throws Exception {
        System.out.println("************************* " + fileName + " ***************************");
        FileBmVo vo = FileBmUtil.createFileBmVo(fileName);
        FileBmUtil.fileCopy(vo.getFileName(), vo.getCopyFileName());
        ReadFileContent.readFileContent(vo);
        FileBmUtil.delSecifiedFile(vo.getCopyFileName());
        System.out.println("************************* " + fileName + " end ***************************");
    }

    public static void readFileContent(FileBmVo vo) throws Exception {
        BufferedReader reader = null;
        List<OutputStreamWriter> dosCsses = new ArrayList<>();
        Boolean flagCss = false;
        List<OutputStreamWriter> dosJses = new ArrayList<>();
        Boolean flagJs = false;
        OutputStreamWriter dosFtl = null;
        Boolean flagFtl = false;
        try {
            reader = new BufferedReader(new FileReader(new File(vo.getCopyFileName())));

            /********* ftl file ***********/
            dosFtl=new OutputStreamWriter(new FileOutputStream(new File(vo.getFileName())));
            /********* ftl file end ***********/

            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                // 如果是注释，直接放过
                if(tempStr.trim().startsWith("<!--")) {
                    dosFtl.write(tempStr + "\r\n");
                    continue;
                }

                // 4.0 动态表单处理 <script type="text/x-template" id="mydate">
                if(tempStr.trim().startsWith("<script") && (tempStr.trim().indexOf("text/x-template") > 0)) {
                    flagFtl = true;
                    dosFtl.write(tempStr + "\r\n");
                    continue;
                }

                /********* css file ***********/
                if(tempStr.trim().startsWith("<link") && tempStr.indexOf("/res/") > 0) {
                    dosFtl.write(FileBmUtil.strCompiled(tempStr, ".css") +  "\r\n");
                    continue;
                }
                if(tempStr.trim().startsWith("<style")) {
                    flagCss = true;
                    FileBmUtil.getOutObj(dosCsses, dosFtl, vo.getFileBmPosCss());
                    continue;
                }
                if(tempStr.trim().equals("</style>")) {
                    flagCss = false;
                    continue;
                }
                if(flagCss) {
                    dosCsses.get(dosCsses.size() - 1).write(tempStr + "\r\n");  // 此时刚好是数组里最大的是当前流对象
                    continue;
                }
                /********* css file end ***********/

                /********* js file ***********/
                if(tempStr.trim().startsWith("<script")) {
                    if((tempStr.trim().indexOf("src") > 0)) {
                        // 1. 引用 src 开头 res
                        if (tempStr.indexOf("/res/") > 0) {
                            dosFtl.write(FileBmUtil.strCompiled(tempStr, ".js")  +  "\r\n");
                        }
                        continue;
                    } else {
                        flagJs = true;
                        FileBmUtil.getOutObj(dosJses, dosFtl, vo.getFileBmPosJs());
                        continue;
                    }
                }
                if(tempStr.trim().startsWith("</script>")) {
                    // 处理 js 和 4.0 组件
                    if(flagFtl) {
                        flagFtl = false;
                        dosFtl.write(tempStr + "\r\n");
                        continue;
                    }
                    if(flagJs) {
                        flagJs = false;
                        continue;
                    }
                }
                if(flagJs) {
                    if(tempStr.trim().indexOf("${mvcPath}") > 0) {
                        System.out.println("替换前" + tempStr);
                        tempStr = tempStr.replaceAll("\\\"", "'");
                        // 处理这种情况  contextPath: "${mvcPath}",
                        if(tempStr.trim().indexOf("\'${mvcPath}\'") > 0) {
                            tempStr = tempStr.replaceAll("'[$]\\{mvcPath\\}'", "this.\\$kun.getContextPath()");
                        }
                        // 处理这种情况 	url: "${mvcPath}/api/openapi/apiCdrCount",
                        if(tempStr.trim().indexOf("\'${mvcPath}/") > 0) {
                            tempStr = tempStr.replaceAll("'[$]\\{mvcPath\\}/", "this.\\$kun.getContextPath() + '/");
                        }
                        System.out.println("替换后" + tempStr);
                    }
                    dosJses.get(dosJses.size() - 1).write(tempStr + "\r\n");  // 此时刚好是数组里最大的是当前流对象
                    continue;
                }
                dosFtl.write(tempStr + "\r\n");
                /********* js file end ***********/
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dosFtl != null) {
                try {
                    dosFtl.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (dosJses != null) {
                try {
                    for (int i = dosJses.size(); i > 0; i--) {
                        dosJses.get(i-1).close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (dosCsses != null) {
                try {
                    for (int i = dosCsses.size(); i > 0; i--) {
                        dosCsses.get(i-1).close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
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
