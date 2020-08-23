package com.lan.javase._7io.示列._6遍历文件内容;

import com.lan.javase._7io.示列._4扫描文件.ScanFiles;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadFileContent4 {

    /**
     * 注意
     *     1. 将 ftl 转换成，生成相应的 目录，.vue 文件, main.js
     *     2. .vue 文件包含 html, js（并入当前页面的js和唯一引用js）,css（并入当前页面的css和唯一引用css）
     *          lib 文件js或css 以 import 形式引入 main.js
     *     3. html
     *           ${mvcPath} 在 html 中 改为 @
     *           注释 TODO
     *
     *    4. js
     *            ${mvcPath} 改为 this.$kun.getContextPath()
     *            var vue=new Vue({  替换后export default ({
     *            删除 el: '#app',
     *    5. css
     *            处理图片的相对路径（.vue, 在.css 里的不管）： TODO
     *
     * 未完成, 人工处理
     *      1.ftl 关于 freemark 的语法，需人工
     *      2.关于 include 页面的 子模块，需改造成 vue 组件形式，需人工
     *      3. 4.0 组件 需人工
     *
     */
    public static void main(String[] args) throws Exception {
        List<String> igs = new ArrayList<>();
        igs.add("lib\\");   // 已忽略文件，运行第二步复制过去
        igs.add("summernote\\");
        igs.add("res\\openapi\\js\\go.js");   //3  res/openapi/js/go.js
        igs.add("res\\openapi\\css\\index.css");   //2  res/openapi/css/index.css
        igs.add("res\\directory\\common\\css\\detail.css");   //1  res/directory/common/css/detail.css
        igs.add("res\\dps\\main\\css\\style.css");   //1  res/dps/main/css/style.css
        igs.add("res\\openapi\\css\\font.css");   //2  res/openapi/css/font.css
        igs.add("res\\dps\\js\\meta\\ai.funcEditer.js");   //1  res/dps/js/meta/ai.funcEditer.js
        igs.add("res\\dps\\js\\flowchar\\Toolbar.js");   //1  res/dps/js/flowchar/Toolbar.js
        igs.add("view\\aijs\\js\\ai.treeview.js");   //1  view/aijs/js/ai.treeview.js
        igs.add("res\\dps\\css\\script.css");   //1  res/dps/css/script.css
        igs.add("res\\openapi\\js\\highlight.js");   //5  res/openapi/js/highlight.js
        igs.add("res\\dps\\css\\layout-default-latest.css");   //1  res/dps/css/layout-default-latest.css
        igs.add("res\\echarts\\js\\common.js");   //2  res/echarts/js/common.js
        igs.add("res\\marked\\marked.js");   //1  res/marked/marked.js
        igs.add("res\\dps\\css\\sqlEditor.css");   //1  res/dps/css/sqlEditor.css
        igs.add("view\\aijs\\js\\ai.grid.js");   //1  view/aijs/js/ai.grid.js
        igs.add("res\\dps\\js\\flowchar\\Actions.js");   //1  res/dps/js/flowchar/Actions.js
        igs.add("res\\openapi\\js\\openapi.js");   //14  res/openapi/js/openapi.js
        igs.add("res\\openapi\\css\\main.css");   //1  res/openapi/css/main.css
        igs.add("res\\js\\DPEchartsTheme.js");   //1  res/js/DPEchartsTheme.js
        igs.add("res\\dps\\js\\dev\\ai.meta.ide.table.js");   //1  res/dps/js/dev/ai.meta.ide.table.js
        igs.add("res\\openapi\\css\\tableDetails.css");   //4  res/openapi/css/tableDetails.css
        igs.add("res\\openapi\\js\\common.js");   //1  res/openapi/js/common.js
        igs.add("res\\dps\\js\\flowchar\\EditorUi.js");   //1  res/dps/js/flowchar/EditorUi.js
        igs.add("res\\dps\\css\\sqf-bootstrap.css");   //1  res/dps/css/sqf-bootstrap.css
        igs.add("view\\aijs\\js\\ai.field.js");   //1  view/aijs/js/ai.field.js
        igs.add("res\\dps\\js\\flowchar\\Sidebar.js");   //1  res/dps/js/flowchar/Sidebar.js
        igs.add("res\\openapi\\css\\apiDetails.css");   //2  res/openapi/css/apiDetails.css
        igs.add("res\\openapi\\css\\highlight.css");   //5  res/openapi/css/highlight.css
        igs.add("res\\openapi\\js\\main.js");   //1  res/openapi/js/main.js
        igs.add("res\\dps\\css\\dacp_sqf.css");   //1  res/dps/css/dacp_sqf.css
        igs.add("res\\directory\\common\\css\\index.css");   //1  res/directory/common/css/index.css
        igs.add("res\\dps\\js\\dev\\ai.meta.widget.proc.js");   //1  res/dps/js/dev/ai.meta.widget.proc.js
        igs.add("res\\marked\\github-markdown.css");   //1  res/marked/github-markdown.css
        igs.add("res\\dps\\css\\grapheditor.css");   //1  res/dps/css/grapheditor.css
        igs.add("res\\dps\\css\\ai.css");   //1  res/dps/css/ai.css
        igs.add("res\\dev\\js\\modelFlow.js");   //1  res/dev/js/modelFlow.js
        igs.add("res\\dps\\js\\dev\\ai.meta.widget.js");   //1  res/dps/js/dev/ai.meta.widget.js
        igs.add("res\\dps\\css\\AeroWindow-Contextmenu.css");   //1  res/dps/css/AeroWindow-Contextmenu.css

        // 指定文件拆分
        // 情况一： 都在ftl, 多个frl 和script 标签
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-deploy\\src\\main\\resources\\view\\web\\index.ftl";
        // 情况2：1.已经拆分好了的  2.ftl 还存在 style, 和 script
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\echarts\\cdrApi.ftl";
        // 情况3 引入的js 被注释掉了
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\intro\\document_center.ftl";
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\openapi\\api\\kunApiPermit.ftl";
//        String fileName = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web\\src\\main\\resources\\view\\openapi\\apiListNew.ftl";
//        ReadFileContent4.dealReadFileContent(fileName, igs);

        // 指定文件夹
        String folder = "D:\\code\\bm\\product\\kun-openapi\\v3.5.3\\kun-openapi\\kun-openapi-web";
        ReadFileContent4.dealReadFolder(folder, igs);

    }

    /**
     * 遍历文件夹
     * @param folderPath
     * @throws Exception
     */
    public static void dealReadFolder(String folderPath, List<String> igs) throws Exception {
        ArrayList<Object> b1 = ScanFiles.scanFilesWithRecursion(folderPath);
        for (Object arg : b1) {
            String fileName = (String) arg;
            if(fileName.endsWith(".ftl") && (fileName.indexOf("\\src\\main\\resources\\view\\") > -1)) {
                dealReadFileContent(fileName, igs);
            }
        }
    }

    /**
     * 1.创建 vo createFileBmVo
     * 2.复制重命名文件
     * 3.读取文件
     *
     * 4.删除复制重命名文件
     * @param fileNameFtl
     * @param igs 直接复制的 公共的css,js 以及 lib 的东西
     */
    public static void dealReadFileContent(String fileNameFtl, List<String> igs) throws Exception {
        System.out.println("************************* " + fileNameFtl + " ***************************");
        FileBmVo4 vo = FileBmUtil4.createFileBmVo(fileNameFtl);
        FileBmUtil4.createSecifiedFile(vo.getFileName()); // 生产 .vue 文件
        FileBmUtil4.createSecifiedFile(vo.getFileNameMainJs()); // 生产 mian.js 文件
        ReadFileContent4.readFileContent(vo, igs);
        System.out.println("************************* " + fileNameFtl + " end ***************************");
    }

    public static void readFileContent(FileBmVo4 vo, List<String> igs) throws Exception {
        BufferedReader reader = null;
        OutputStreamWriter dosVue = null;
        OutputStreamWriter dosMainJs = null;

        List<String> htmls = new ArrayList<>();
        Integer intHtml = 0;

        List<String> jses = new ArrayList<>();
        Boolean flagJs = false;

        List<String> csses = new ArrayList<>();
        Boolean flagCss = false;

        List<String> mainJses = new ArrayList<>();
        mainJses.add("import Vue from 'vue';");
        mainJses.add("import ElementUI from 'element-ui';");
        mainJses.add("import UedComponents from '@kun/ued-components';");

        try {
            reader = new BufferedReader(new FileReader(new File(vo.getFileNameFtl())));
            dosVue = new OutputStreamWriter(new FileOutputStream(new File(vo.getFileName())));
            dosMainJs = new OutputStreamWriter(new FileOutputStream(new File(vo.getFileNameMainJs())));

            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
//                // 如果是注释，直接放过
//                if(tempStr.trim().startsWith("<!--")) {
//                    dosVue.write(tempStr + "\r\n");
//                    continue;
//                }
//
//                // 4.0 动态表单处理 <script type="text/x-template" id="mydate">
//                if(tempStr.trim().startsWith("<script") && (tempStr.trim().indexOf("text/x-template") > 0)) {
//                    flagFtl = true;
//                    dosVue.write(tempStr + "\r\n");
//                    continue;
//                }

                /********* css file ***********/
                if(tempStr.trim().startsWith("<link")) {
                    // 1. 引用 src 开头 res
                    String cssPath = FileBmUtil4.getJsCssPath(tempStr, ".css");
                    if(FileBmUtil4.ifLibFile(igs, cssPath)) {
                        FileBmUtil4.readJsCsslib(cssPath, vo.getPathFtl(), mainJses);
                    } else {
                        FileBmUtil4.readCss(vo.getPathFtl() + cssPath, csses);
                    }
                    continue;
                }
                if(tempStr.trim().startsWith("<style")) {
                    flagCss = true;
                    continue;
                }
                if(tempStr.trim().equals("</style>")) {
                    flagCss = false;
                    continue;
                }
                if(flagCss) {
                    tempStr = TempStrUtil4.dealCss(tempStr);
                    if(StringUtils.isNotBlank(tempStr) && !"deleteALine".equals(tempStr)) {
                        csses.add(tempStr);
                    }
                    continue;
                }
//                /********* css file end ***********/

//                /********* js file ***********/
                if(tempStr.trim().startsWith("<script")) {
                    if((tempStr.trim().indexOf("src") > 0)) {
                        // 1. 引用 src
                        String jsPath = FileBmUtil4.getJsCssPath(tempStr, ".js");
                        if(FileBmUtil4.ifLibFile(igs, jsPath)) {
                            FileBmUtil4.readJsCsslib(jsPath, vo.getPathFtl(), mainJses);
                        } else {
                            FileBmUtil4.readJs(vo.getPathFtl() + jsPath, jses);
                        }
                        continue;
                    } else {
                        flagJs = true;
                        continue;
                    }
                }
                if(tempStr.trim().startsWith("</script>")) {
                    if(flagJs) {
                        flagJs = false;
                        continue;
                    }
                }
                if(flagJs) {
                    tempStr = TempStrUtil4.dealJs(tempStr);
                    if(StringUtils.isNotBlank(tempStr) && !"deleteALine".equals(tempStr)) {
                        jses.add(tempStr);
                    }
                    continue;
                }
                /********* js file end ***********/

                if(tempStr.trim().startsWith("<body")) {
                    if(tempStr.trim().indexOf("id") > -1) {
                        intHtml = 2;// 存在 el 的id直接写在body 上
                        htmls.add("<div>");
                    } else {
                        intHtml = 1;
                    }
                    continue;
                }
                if(tempStr.trim().indexOf("</body>") > -1) {
                    if(intHtml == 2) {
                        htmls.add("</div>");
                    }
                    intHtml = 0;
                    continue;
                }
                if(intHtml != 0) {
                    htmls.add(TempStrUtil4.dealFtl(tempStr));
                }
            }

            mainJses.add("import App from './App.vue';");
            mainJses.add("import '@kun/ued-components/lib/theme-blue/index.css';");
            mainJses.add("");
            mainJses.add("// process.env.VUE_APP_USE_MOCK === 'true' && require('./mock');");
            mainJses.add("");
            mainJses.add("Vue.config.productionTip = false;");
            mainJses.add("// ElementUI 注册");
            mainJses.add("Vue.use(ElementUI);");
            mainJses.add("Vue.use(UedComponents);");
            mainJses.add("Vue.prototype.$kun = UedComponents.Kun;");
            mainJses.add("Vue.prototype.$BASE_API = process.env.VUE_APP_BASE_API || '';");
            mainJses.add("");
            mainJses.add("new Vue({");
            mainJses.add("  render: h => h(App),");
            mainJses.add("}).$mount('#app');");
            for (String mainJs : mainJses) {
                dosMainJs.write(mainJs + "\r\n");
            }

            dosVue.write("<template>" + "\r\n");
            for (String html : htmls) {
                dosVue.write(html + "\r\n");
            }
            dosVue.write("</template>" + "\r\n");
            dosVue.write("\r\n");
            dosVue.write("<script>" + "\r\n");
            for (String js : jses) {
                dosVue.write(js + "\r\n");
            }
            dosVue.write("</script>" + "\r\n");
            dosVue.write("\r\n");
            dosVue.write("<style>" + "\r\n");
            for (String css : csses) {
                dosVue.write(css + "\r\n");
            }
            dosVue.write("</style>" + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dosMainJs != null) {
                try {
                    dosMainJs.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (dosVue != null) {
                try {
                    dosVue.close();
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
