package com.lan.java8._4默认方法;

public class SubClass /*extends MyClass*/ implements MyFun, MyInterface{

	@Override
	public String getName() {
		return MyInterface.super.getName();
	}

}
