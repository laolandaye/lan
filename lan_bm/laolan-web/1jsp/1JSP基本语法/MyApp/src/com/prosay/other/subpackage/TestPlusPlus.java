package com.prosay.other.subpackage;

import com.prosay.other.InterfaceMySamplePlus;
import com.prosay.other.Teacher;

public class TestPlusPlus implements InterfaceMySamplePlus<Teacher> {
  // 可以用一个泛型的类实现一个泛型的接口，泛型的模板也将会被继承下来。
  @Override
  public void sayHello(Teacher obj) {
    System.out.println(obj.getTeacherName() + "说：你好！");
  }

}
