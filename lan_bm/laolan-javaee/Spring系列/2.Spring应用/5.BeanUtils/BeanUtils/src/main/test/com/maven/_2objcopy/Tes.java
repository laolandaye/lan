package com.maven._2objcopy;

import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class Tes {

    @Test
    public void testFatherToChild(){
        Person p=new Person();
        p.setAvgScore(3.0);
        p.setCourseName("courseName");
        p.setCreateTime(new Date());
        p.setNum(2);

        Student s=new Student();
        s.setStudentName("studentName");
        BeanUtils.copyProperties(p, s);

        System.out.println(p);
        System.out.println("------类似js深拷贝：父类属性复制到新子类对象-------------");
        System.out.println(s);
    }

    @Test
    public void testChildToFather(){
        Student s=new Student();
        s.setStudentName("studentName");
        s.setAvgScore(3.0);
        s.setCourseName("courseName");
        s.setCreateTime(new Date());
        s.setNum(2);

        Person p = new Person();
        BeanUtils.copyProperties(s, p);

        System.out.println(s);
        System.out.println("------类似js深拷贝：子类复制到新父类对象-------------");
        System.out.println(p);
    }
}
