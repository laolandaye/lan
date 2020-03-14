package com.lan.javase._3面向对象.扩展._6多态后方法执行顺序;

public class 多态后方法执行顺序 {
    public static void main(String[] args) {
        // 在所有实列初始化之前， 先类初始化（父类初始化 静态 1次） （子类初始化 静态1次）
        Son s1 = new Son();	// （父实列初始化） （子实列初始化）
        System.out.println();
        Son s2 = new Son();	// （父实列初始化） （子实列初始化）
        System.out.println();
        Father f1 = new Father();	// （父实列初始化）
    }
}
/*
 * 父类的初始化<clinit>：
 * （1）j = method();
 * （2）父类的静态代码块
 *
 *  父类的实例化方法：
 * （1）super()（最前）
 * （2）i = test();
 * （3）父类的非静态代码块
 * （4）父类的无参构造（最后）
 *
 * 非静态方法前面其实有一个默认的对象this
 * this在构造器（或<init>）它表示的是正在创建的对象，因为这里是在创建Son对象，所以
 * test()执行的是子类重写的代码（面向对象多态）
 *
 * 这里i=test()执行的是子类重写的test()方法
 */
class Father{
    private int i = test();
    private static int j = method();

    static{
        System.out.print("(1)");
    }
    Father(){
        System.out.print("(2)");
    }
    {
        System.out.print("(3)");
    }

    public int test(){
        System.out.print("(4)");
        return 1;
    }
    public static int method(){
        System.out.print("(5)");
        return 1;
    }
}
/*
 * 子类的初始化<clinit>：
 * （1）j = method();
 * （2）子类的静态代码块
 *
 * 先初始化父类：(5)(1)
 * 初始化子类：（10）(6)
 *
 * 子类的实例化方法<init>：
 * （1）super()（最前）      （9）（3）（2）
 * （2）i = test();    （9）
 * （3）子类的非静态代码块    （8）
 * （4）子类的无参构造（最后） （7）
 *
 * 因为创建了两个Son对象，因此实例化方法<init>执行两次
 *
 * （9）（3）（2）（9）（8）（7）
 */
class Son extends Father{
    private int i = test();
    private static int j = method();
    static{
        System.out.print("(6)");
    }
    Son(){
//		super();//写或不写都在，在子类构造器中一定会调用父类的构造器
        System.out.print("(7)");
    }
    {
        System.out.print("(8)");
    }
    public int test(){
        System.out.print("(9)");
        return 1;
    }
    public static int method(){
        System.out.print("(10)");
        return 1;
    }
}
