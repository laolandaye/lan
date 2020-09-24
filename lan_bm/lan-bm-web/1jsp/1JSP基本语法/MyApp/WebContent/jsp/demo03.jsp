<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP基础语法和组成</title>
</head>
<body>
<h1>JSP基础语法和组成</h1>
<%-- 笔记 --%>
<h2>JSP的工作原理：编辑源码->部署至服务器->启动服务器->通过浏览器访问</h2>
<h2>其实JSP的工作原理中有很大一部分工作程序员并未参与。而是由Web容器自动完成的。</h2>
<h2>Tomcat(Web容器在JSP的工作中完成很大一部分工作)，具体如下：</h2>
<ol>
<li>查询并加载JSP页面，找不到资源直接报404的错误代码，404的错误代码浏览器会解析为：页面未找到！File Not Found.</li>
<li>转译:翻译（将JSP文件翻译成了java文件）</li>
<li>编译:（将java文件编译成了class文件）</li>
<li>执行:（运行class文件，文件执行的结果就是一个完整HTML页面）（所谓的动态网页，我们在JavaWeb课程中，实际上是指的内容动态，在这里Java程序可以从数据库中获取数据，所以页面的内容会根据数据库的情况发生改变）</li>
<li>响应:（将生成的结果HTML页面传输到浏览器）</li>
</ol>
<h3>接下来的工作由浏览器接班，负责解析HTML页面，并采用图形化的方式显示出来。</h3>
<ol>
  <li>HTML</li>
  <pre>
    标准的HTML语言的标签，包括CSS和JavaScript，统称为HTML。
    这一部分的内容，在JSP页面中，会将所有的上述的代码当作字符串，直接输出到浏览器。
  </pre>
  <li>注释</li>
  <pre>
    HTML的注释和JSP的注释
    HTML:&lt;!--  --&gt;
    JSP:&lt;%--  --%&gt;
    所有的注释都会被忽略，区别在于：
    HTML注释是浏览器会忽略。
    JSP注释Web容器就已经忽略了。
    通俗的来说，HTML注释可以在网页的源代码中看到，
    JSP注释则看不到。
    <!-- 这个是HTML的注释 -->
    <%-- 这个是JSP的注释 --%>
    <%--
    多行注释也可以
     --%>
  </pre>
  <li>JSP代码段</li>
    <pre>
      &lt;% Java代码; %&gt;JSP的代码段，Java的大多数语句可以放在代码段中，语法规则按照标准的Java语法规则。
      什么语句不能放？定义语句不能放。不管是定义什么都不行（类，方法，属性统统不行）
    </pre>
    <%
    A a=new A();
    int number=10;//局部变量，在方法中定义的变量，仅仅在方法中有效
    a.sayHello();
    System.out.println("userName="+a.getUserName());
    System.out.println("number="+number);
    %>
  <li>JSP表达式</li>
  <pre>
      &lt;%= Java代码 %&gt;JSP表达式，可以放属性和方法的调用语句。
      JSP表达式有一个特别的地方。Java语句的结尾，不可以有“;”，也不可以有多句语句！！！
      JSP表达式的功能，其实就是输出语句。输出属性的值或者是方法的返回值。
      输出的位置是页面。
  </pre>
  <%="number="+number%>
  <%="userName is :"+a.getUserName()%>
  <li>JSP声明</li>
  <pre>
      &lt;%! Java代码; %&gt;JSP的声明，可以放的仅仅只能是定义语句，语法规则按照标准的Java语法规则。
      除了定义语句，其他的语句一概不能放。
      JSP当中不可以定义抽象的东西！！！
  </pre>
    <%!
    public class A {//定义类
      private String userName="zhangsan";//定义属性，也可以叫做成员变量
    //尽量避免使用首字母仅有一个小写字母的标识符uName，u_Name，这种变量名在EL表达式中可能会有问题。
      public void sayHello() {//定义方法
        System.out.println(this.userName+"说：你好，JSP!");
      }
      
      public String getUserName() {
        return this.userName;
      }
    }
    
    //System.out.println("你好，JSP!");//错误的写法，非定义语句不能出现在JSP声明中！
    %>
  <li>JSP指令</li>
  <li>JSP动作</li>
</ol>
</body>
</html>