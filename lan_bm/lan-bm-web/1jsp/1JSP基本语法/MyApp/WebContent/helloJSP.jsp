<%@page import="com.prosay.myapp.demo.Sample"%>
<%-- JSP注释 --%>
<%-- JSP文件保存的时候使用的字符编码 --%>
<%@page pageEncoding="UTF-8"%>
<%-- JSP页面的字符编码 --%>
<%@page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<!-- HTML的注释 -->
<!-- 网页的字符编码 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<h1>Hello 中文</h1>

<h2>Hello Prosay</h2>

<%
  String tmp = Sample.sayHello();
  out.println("<h3>" + tmp + "</h3>");
%>
</html>
