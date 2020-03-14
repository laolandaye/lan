package com.lan.javase._11反射.reflection.class类以及类加载器;

import com.lan.javase._11反射.reflection.Person;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Properties;

public class TestClassLoader类加载器 {

    //关于类的加载器：ClassLoader
    @Test
    public void test5() throws Exception{
        ClassLoader loader1 = ClassLoader.getSystemClassLoader();
        System.out.println(loader1);

        ClassLoader loader2 = loader1.getParent();
        System.out.println(loader2);

        ClassLoader loader3 = loader2.getParent();
        System.out.println(loader3);

        Class clazz1 = Person.class;
        ClassLoader loader4 = clazz1.getClassLoader();
        System.out.println(loader4);

        String className = "java.lang.String";
        Class clazz2 = Class.forName(className);
        ClassLoader loader5 = clazz2.getClassLoader();
        System.out.println(loader5);


    }


    //关于类的加载器：ClassLoader
    @Test(timeOut = 1000)
    public void test6() throws Exception{
        //掌握如下
        //法一：
        ClassLoader loader = this.getClass().getClassLoader();
        InputStream is = loader.getResourceAsStream("db.properties");
        //法二：
//		FileInputStream is = new FileInputStream(new File("jdbc1.properties"));

        Properties pros = new Properties();
        pros.load(is);
        String name = pros.getProperty("user");
        System.out.println(name);

        String password = pros.getProperty("password");
        System.out.println(password);

    }
}

