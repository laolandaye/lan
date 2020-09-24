package com.prosay.other;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 动脑猿道 Carman老师
 * @year 2018
 * @date 2018年8月26日
 *
 *       今天的内容：泛型的使用
 */
public class Sample01 {

  @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
  public static void main(String[] args) {
    // 什么叫泛型，广泛的类型
    // JDK1.5以前是没有泛型的。
    // 为什么会引入泛型的概念？
    // 实例：我有一组数据，要求保存在内存中。
    // 传统的实现方案有哪些？
    // 1,用变量（缺点，需要定义大量变量，会造成变量名冲突机会成倍提高，也不利于代码的阅读）
    // 2,用数组（解决了变量名称的问题，大幅度的降低的变量名称的数量）
    // 3,用集合（解决数组长度固定，类型一致的短板）数据检查：宽进严出
    // 4,用泛型集合（解决了获取传统集合的数据类型变化，需要判断转型效率低下的问题）数据检查：严进宽出

    // 在JDK1.5以前，集合的使用
    // 创建集合
    List list = new ArrayList();

    // 往集合中添加元素
    list.add(new Integer(10));
    list.add(new String("HelloWorld"));
    list.add(new Double(3.14));
    list.add(10);// Jdk1.5以前不能这样写。自动装箱
    int num = new Integer(20);// Jdk1.5以前不能这样写。自动拆箱
    // Sample01 obj = new Sample01();
    // Student stu = obj.new Student();
    list.add(new Sample01().new Student());

    // 遍历/迭代集合（将集合中的元素全部取出来）
    for (int i = 0; i < list.size(); i++) {
      Object tmp = list.get(i);
      System.out.println(tmp);
    }

    // 从集合中获取对象类型的时候，带来的对象类型改变的问题。
    // 所以这个时候，我们需要从集合中获取完元素以后，做数据类型的转换
    Object no4 = list.get(4);
    if (no4 instanceof Sample01.Student) {// 判断类型，避免异常
      ((Sample01.Student) no4).sayHello();
    }

    // JDK1.5以后的版本，采用泛型集合
    List<Integer> intList = new ArrayList<Integer>();// 严格指定集合的数据类型
    // intList.add(new String("ABC"));//因为集合中的数据已经指定了泛型的类型，泛型集合的数据类型是统一的。
    intList.add(10);// 这里自动装箱的
    intList.add(20);

    // for(Object obj:intList) {//迭代非泛型集合的语法
    // }
    for (Integer obj : intList) {// 迭代非泛型集合的语法
      // 获取的时候，就不需要再去判断数据类型以及强制类型转换了！所以说出口宽松。
      System.out.println("数字是：" + obj);
    }
  }

  class Student {
    public void sayHello() {
      System.out.println("内部类的方法说你好！");
    }
  }

}
