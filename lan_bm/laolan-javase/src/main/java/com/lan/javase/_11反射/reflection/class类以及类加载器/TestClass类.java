package com.lan.javase._11反射.reflection.class类以及类加载器;

import com.lan.javase._11反射.reflection.Person;
import org.testng.annotations.Test;

public class TestClass类 {

    /*
     * java.lang.Class:是反射的源头。
     * 我们创建了一个类，通过编译（javac.exe）,生成对应的.class文件。之后我们使用java.exe加载（JVM的类加载器完成的）
     * 此.class文件，此.class文件加载到内存以后，就是一个运行时类，存在在缓存区。那么这个运行时类本身就是一个Class的实例！
     * 1.每一个运行时类只加载一次！
     * 2.有了Class的实例以后，我们才可以进行如下的操作：
     *     1）*创建对应的运行时类的对象
     *     2）获取对应的运行时类的完整结构（属性、方法、构造器、内部类、父类、所在的包、异常、注解、...）
     *     3）*调用对应的运行时类的指定的结构(属性、方法、构造器)
     *     4）反射的应用：动态代理
     */
    //如何获取Class的实例（4种）
    @Test
    public void test4() throws ClassNotFoundException{
        //1.调用运行时类本身的.class属性
        Class clazz1 = Person.class;
        System.out.println(clazz1.getName());

        Class clazz2 = String.class;
        System.out.println(clazz2.getName());

        //2.通过运行时类的对象获取
        Person p = new Person();
        Class clazz3 = p.getClass();
        System.out.println(clazz3.getName());

        //3.通过Class的静态方法获取.通过此方式，体会一下，反射的动态性。
        String className = "com.lan.javase._11反射.reflection.Person";


        Class clazz4 = Class.forName(className);
//		clazz4.newInstance();
        System.out.println(clazz4.getName());

        //4.（了解）通过类的加载器
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class clazz5 = classLoader.loadClass(className);
        System.out.println(clazz5.getName());

        System.out.println(clazz1 == clazz3);//true
        System.out.println(clazz1 == clazz4);//true
        System.out.println(clazz1 == clazz5);//true
    }



}

