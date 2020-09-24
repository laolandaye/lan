package com.prosay.other.subpackage;

import com.prosay.other.BusDriver;
import com.prosay.other.InterfaceMySamplePlus;
import com.prosay.other.Student;
import com.prosay.other.Teacher;

public class TestPlus<T> implements InterfaceMySamplePlus<T> {
  // 可以用一个泛型的类实现一个泛型的接口，泛型的模板也将会被继承下来。
  @Override
  public void sayHello(T obj) {
    if (obj instanceof Teacher) {
      System.out.println(((Teacher) obj).getTeacherName() + "老师说：你好！");
    } else if (obj instanceof Student) {
      System.out.println(((Student) obj).getStudentName() + "同学说：你好！");
    } else if (obj instanceof BusDriver) {
      System.out.println(((BusDriver) obj).getBusDriverName() + "司机说：你好！");
    }
  }

}
