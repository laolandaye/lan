<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String contextPath =request.getContextPath();
    String basePath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + contextPath;
%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"  />
    <meta http-equiv="content-type" content="no-cache, must-revalidate" />
    <meta http-equiv="Cache-Control" content="no-cache, must-revalidate" />
    <meta http-equiv="expires" content="Wed, 26 Feb 1997 08:21:57 GMT"/>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
        <sitemesh:write property='title'/>
    </title>
    <!-- 第三方开源样式-开始 -->
    <style>
        @import url("${path}/static/lib/element/css/index.css");
    </style>
    <!-- 第三方开源样式-结束 -->
    <sitemesh:write property="head"/>
</head>
<body>


<!-- 第三方开源库-开始 -->
<script type="text/javascript" src="${path}/static/lib/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${path}/static/lib/moment/moment.js"></script>
<script type="text/javascript" src="${path}/static/lib/vue/vue.js"></script>
<script type="text/javascript" src="${path}/static/lib/element/js/index.js"></script>
<script type="text/javascript" src="${path}/static/lib/axios.min.js"></script>
<!-- 第三方开源库-结束 -->
<!-- 工程通用js-开始 -->
<!-- 工程通用js-结束 -->
<sitemesh:write property="body"/>
<sitemesh:write property="page.javascript"/>
</body>
</html>
