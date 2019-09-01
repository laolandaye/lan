<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <sitemesh:write property='title'/>
    </title>
    <!-- 第三方开源样式css-开始 -->
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 第三方开源样式css-结束 -->
    <sitemesh:write property="head"/>
</head>
<body>
<sitemesh:write property="body"/>

<!-- 第三方开源库js-开始 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<!-- 第三方开源库js-结束 -->
<!-- 工程通用js-开始 -->
<!-- 工程通用js-结束 -->

<sitemesh:write property="page.javascript"/>
</body>
</html>
