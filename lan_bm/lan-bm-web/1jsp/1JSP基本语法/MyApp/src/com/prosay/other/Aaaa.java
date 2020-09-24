package com.prosay.other;

public class Aaaa {
  // JSP声明转译的位置，转译到了类中间
  public class A {// 定义类
    private String userName = "zhangsan";// 定义属性，也可以叫做成员变量
    // 尽量避免使用首字母仅有一个小写字母的标识符uName，u_Name，这种变量名在EL表达式中可能会有问题。

    public void sayHello() {// 定义方法
      System.out.println(this.userName + "说：你好，JSP!");
    }

    public String getUserName() {
      return this.userName;
    }
  }

  // JSP代码段转译的位置，转移到了service方法中间
  public void service() {
    A a = new A();
    int number = 10;// 局部变量，在方法中定义的变量，仅仅在方法中有效
    a.sayHello();
    System.out.println("userName=" + a.getUserName());
    System.out.println("number=" + number);
  }

  public void fun() {
    try {
      Class.forName("");
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
