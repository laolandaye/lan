package com.prosay.other.subpackage;

import com.prosay.other.BusDriver;
import com.prosay.other.InterfaceMySample;
import com.prosay.other.Student;
import com.prosay.other.Teacher;

public class Test implements InterfaceMySample {
  // 如果在接口中定义的方法是默认的访问修饰符，在实现的时候，将会自动提升访问修饰符的范围到public。

  @Override
  public void sayHello(Teacher teacher) {
    System.out.println(teacher.getTeacherName() + "老师说：你好！");
  }

  @Override
  public void sayHello(Student student) {
    System.out.println(student.getStudentName() + "同学说：你好！");
  }

  @Override
  public void sayHello(BusDriver busDriver) {
    System.out.println(busDriver.getBusDriverName() + "司机说：你好！");
  }

}
