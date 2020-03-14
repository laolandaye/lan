package com.lan.javase._1基本语法._2方法._4参数传值;

import java.util.Arrays;

public class Exam4 {
	public static void main(String[] args) {
		int i = 1;
		String str = "hello";
		StringBuffer strB = new StringBuffer("hello");
		Integer num = 200;
		int[] arr = {1,2,3,4,5};
		MyData my = new MyData();

		change(i,str,strB,num,arr,my);

		System.out.println("i = " + i);
		System.out.println("str = " + str);
		System.out.println("strB = " + strB.toString());
		System.out.println("num = " + num);
		System.out.println("arr = " + Arrays.toString(arr));
		System.out.println("my.a = " + my.a);
	}
	public static void change(int j, String s, StringBuffer strB, Integer n, int[] a,MyData m){
		j += 1;
		s += "world";
		strB.append(" world");
		n += 1;
		a[0] += 1;
		m.a += 1;
	}
}

class MyData{
	int a = 10;
}
