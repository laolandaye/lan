package com.prosay.other;

import com.prosay.other.subpackage.Test;
import com.prosay.other.subpackage.TestPlus;
import com.prosay.other.subpackage.TestPlusPlus;

/**
 * @author 动脑猿道 Carman老师
 * @year 2018
 * @date 2018年8月26日
 *
 *       如何使用泛型编程，定义泛型
 */
public class Sample02 {

  public static void main(String[] args) {
    Teacher teacher = new Teacher();
    teacher.setTeacherName("苍老师");
    teacher.setTeacherAge(33);

    Student student = new Student();
    student.setStudentName("小曹");
    student.setMan(true);

    Test test = new Test();
    test.sayHello(student);

    TestPlusPlus testPlusPlus = new TestPlusPlus();
    testPlusPlus.sayHello(teacher);

    System.out.println("-------------------------");
    TestPlus<Teacher> testPlusTeacher = new TestPlus<Teacher>();
    testPlusTeacher.sayHello(teacher);
    TestPlus<Student> testPlusStudednt = new TestPlus<Student>();
    testPlusStudednt.sayHello(student);
    TestPlus<BusDriver> testPlusBusDriver = new TestPlus<BusDriver>();
    BusDriver obj = new BusDriver();
    obj.setBusDriverName("隔壁老王");
    testPlusBusDriver.sayHello(obj);
  }

}
