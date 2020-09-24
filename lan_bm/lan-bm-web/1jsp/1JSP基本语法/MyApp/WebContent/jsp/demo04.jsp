<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- JSP指令 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<ol>
  <li>在eclipse中配置的tomcat，运行的时候配置文件存放在哪里？</li>
  <%
  // TODO 将要完成的工作，主要是帮助我们记住什么地方的工作未完成
  // FIXME 修复我，主要是保住我们记住什么地方有可能有问题未处理，FIXME优先级高一点，会排在前面。
  // TODO request.getServletContext().getRealPath("/");干嘛用的？
  String path=request.getServletContext().getRealPath("/");
  %>
  <%=path%>
  <%--
eclipse的工作空间：D:\LinkDisk\EclipseProjects\workspace-mars
eclipse插件的配置信息：\.metadata\.plugins
Web容器的插件的配置信息：\org.eclipse.wst.server.core
对于不同的Web项目的服务器配置信息：\tmpX
相当于tomcat的webapps子文件夹：\wtpwebapps
项目名称：\MyApp\ 
  --%>
  <%--
  HTML的代码，在容器转译过程中，相当于把HTML代码变成了字符串，使用out对象的write方法输出，在前面加上了out.write("，在后面加上了");
  JSP注释，在容器转译过程中，就已经被Web容器的转译组件忽略了。所以在java源代码中找不到。
  HTML注释，在容器转译过程中，当作HTML代码处理。
  JSP代码段中的Java代码，在容器的转译过程中，直接复制到了转译以后的java文件的_jspservice方法中。所以也就解释了，代码段中Java代码为什么不能有定义语句。
  JSP表达式，在容器转译过程中，相当于首先完成表达式的计算，其次将表达式计算的结果，使用out对象，直接输出。前面加上了out.print(，后面加上了);
  所以，这个时候应该可以理解，为什么JSP表达式的结尾不能有;，也不可以有多于语句。
  其实也可以通俗的理解，JSP表达式中的内容，作为out.print()方法的参数。
  JSP声明中的Java代码，在容器转译过程中，直接将代码转译到了类中。所以也就解释了，声明中的Java代码为什么不能有非定义语句（方法调用的语句）。
   --%>
   <li>&lt;%@ JSP指令 %&gt;完成给JSP下一些命令的功能。指令必须放在JSP开头。同一个指令可以采用多行书写。也可以一行书写，一行书写的时候，多个属性使用空格隔开。</li>
   <%
   // TODO 会话跟踪时候补充include指令
   // TODO JSTL标准标签库时候补充taglib指令
   %>
   <li>常见的指令：<ol>
     <li>page指令，主要完成JSP的一些基本设置工作。比如说，可以指定JSP的语言language属性，页面的字符编码pageEncoding属性，页面内容的字符编码contentType属性，导入类import属性，EL表达式支持的属性，指定错误页面的属性</li>
     <li>include指令，包含页面。在一个JSP页面中，可以引入另外一个JSP</li>
     <li>taglib指令，标签库指令。在JSP页面当中，引用标签库</li>
   </ol></li>
   <%
   // TODO 会话跟踪时候补充redirect、forward、include动作
   // 《JSP设计》
   %>
   <li>&lt;jsp:xxx&gt;&lt;/jsp:xxx&gt;JSP标准动作，看起来有点像HTML的标签，实际上也有点像JSTL的标签库。动作实际上是标签</li>
   <li><ol>
   <li>&lt;jsp:redirect&gt;&lt;/jsp:redirect&gt;重定向</li>
   <li>&lt;jsp:forward page=""&gt;&lt;/jsp:forward&gt;转发</li>
   <li>&lt;jsp:include page=""&gt;&lt;/jsp:include&gt;包含</li>
   </ol></li>
</ol>
</body>
</html>