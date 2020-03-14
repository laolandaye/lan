package com.lan.javase._11反射.reflection.反射Annotation泛型;

import com.lan.javase._11反射.reflection.Person;
import org.testng.annotations.Test;

public class TestOthers {

    //5.获取所在的包
    @Test
    public void test5(){
        Class clazz = Person.class;
        Package pack = clazz.getPackage();
        System.out.println(pack);
    }

}

