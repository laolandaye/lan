package com.lan.java8._1lambda.策略设计模式;


import com.lan.java8._1lambda.bean.Employee;

public class FilterEmployeeForAge implements MyPredicate<Employee> {

	@Override
	public boolean test(Employee t) {
		return t.getAge() <= 35;
	}

}
