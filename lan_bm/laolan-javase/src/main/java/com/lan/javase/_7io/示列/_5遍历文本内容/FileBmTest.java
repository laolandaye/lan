package com.lan.javase._7io.示列._5遍历文本内容;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class FileBmTest {

    @Test
    public static void FileCopyTest() throws Exception {
        String srcPathStr = "D:\\lan\\old\\kunApiCdrCount.txt"; //源文件地址
        String desPathStr = "D:\\lan\\old\\kunApiCdrCount2.txt"; //目标文件地址
        FileBmUtil.fileCopy(srcPathStr, desPathStr);//将E:\\java task\\zhl.txt文件拷贝到E:\\java task\\zhlll
    }

    @Test
    public static void FileDelTest() throws Exception {
        String fileName = "D:\\code\\bm\\product\\kun-openapi\\gehua\\kun-openapi\\kun-openapi-deploy\\src\\main\\resources\\view\\web\\frame.ftl";

        FileBmUtil.delSecifiedFile(fileName);
    }

    @Test
    public static void createFileBmVoTest() throws Exception {
        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\echarts\\cdrApi.ftl";

        FileBmVo vo = FileBmUtil.createFileBmVo(fileName);
        System.out.println(vo);
    }

    /**
     * 注意：
     *      js:
     *          已解决: ${mvcPath} 替换为 this.$kun.getContextPath()
     *          未处理： 1. ${loginConfigVo}
     */
    public static void main(String[] args) throws Exception {
        // 指定文件拆分
        // 情况一： 都在ftl, 多个frl 和script 标签
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-deploy\\src\\main\\resources\\view\\web\\index.ftl";
        // 情况2：1.已经拆分好了的  2.ftl 还存在 style, 和 script
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\echarts\\cdrApi.ftl";
        // 情况3 引入的js 被注释掉了
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\intro\\document_center.ftl";
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\openapi\\api\\kunApiPermit.ftl";
//        ReadFileContent.dealReadFileContent(fileName);

        // 指定文件夹
        String folder = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi";
        List<String> writeFolder = new ArrayList<>();
        writeFolder.add("kun-openapi-web");
        ReadFileContent.dealReadFolder(folder, writeFolder);

    }

}
