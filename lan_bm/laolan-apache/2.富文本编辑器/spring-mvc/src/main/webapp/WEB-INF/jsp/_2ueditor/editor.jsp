<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="mvcPath" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <style>
    </style>
</head>
<body>
    <div id="app" v-cloak>
        <el-button type="primary" style="float: right;margin: 10px" @click="createPolicy" :disabled="isEditor">发布</el-button>
        <div style="width:80%;margin-left: 10%">
        <script id="editor" type="text/plain" style="width:100%;height:100%;margin:5px auto"></script>
        </div>
        <div>
            <div v-html="editorContent"></div>
        </div>
    </div>

    <content tag="javascript">
        <script type="text/javascript" charset="utf-8" src="${mvcPath}/ueditor/utf8-jsp/ueditor.config.js"></script>
        <script type="text/javascript" charset="utf-8" src="${mvcPath}/ueditor/utf8-jsp/ueditor.all.min.js"> </script>
        <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
        <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
        <script type="text/javascript" charset="utf-8" src="${mvcPath}/ueditor/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
        <script type="text/javascript">
            var ue = UE.getEditor('editor');
        </script>
        <script>
            var vue = new Vue({
                el: '#app',
                data:function(){
                    return {
                        isEditor:false,
                        editorContent:'',
                    }
                },
                created:function(){
                    // this.contextPath = this.$kun.getContextPath();
                    // this.initData();
                    // this.getCompanyList();
                },

                filters: {
                },
                mounted: function(){
                },
                methods: {
                    createPolicy:function(){
                        this.editorContent = UE.getEditor('editor').getContent();
                    },
                }
            });
        </script>
    </content>
</body>
</html>
