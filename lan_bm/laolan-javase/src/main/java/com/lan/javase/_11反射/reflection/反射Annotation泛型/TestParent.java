package com.lan.javase._11反射.reflection.反射Annotation泛型;

import com.lan.javase._11反射.reflection.Person;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TestParent {
    //6.获取注解
    @Test
    public void test6(){
        Class clazz = Person.class;
        Annotation[] anns = clazz.getAnnotations();
        for(Annotation a : anns){
            System.out.println(a);
        }
    }

    //4.获取实现的接口
    @Test
    public void test4(){
        Class clazz = Person.class;
        Class[] interfaces = clazz.getInterfaces();
        for(Class i : interfaces){
            System.out.println(i);
        }
    }

    //3*.获取父类的泛型
    @Test
    public void test3(){
        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();

        ParameterizedType param = (ParameterizedType)type1;
        Type[] ars = param.getActualTypeArguments();

        System.out.println(((Class)ars[0]).getName());
    }

    //2.获取带泛型的父类
    @Test
    public void test2(){
        Class clazz = Person.class;
        Type type1 = clazz.getGenericSuperclass();
        System.out.println(type1);
    }

    //1.获取运行时类的父类
    @Test
    public void test1(){
        Class clazz = Person.class;
        Class superClass = clazz.getSuperclass();
        System.out.println(superClass);
    }
}

