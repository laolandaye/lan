package com.lan.java8._1lambda.策略设计模式;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
