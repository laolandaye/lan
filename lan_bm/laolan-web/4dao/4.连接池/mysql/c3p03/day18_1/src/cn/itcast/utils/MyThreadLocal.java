package cn.itcast.utils;

import java.util.HashMap;
import java.util.Map;
/**
 * 没啥用；只是为了 看清ThreadLocal 的方法
 * @author Administrator
 *
 * @param <T>
 */
public class MyThreadLocal<T> {

	private Map<Thread, T> map;

	private MyThreadLocal() {
		map = new HashMap<Thread, T>();
	}

	public void set(T t) {
		map.put(Thread.currentThread(), t);
	}

	public T get() {
		return map.get(Thread.currentThread());
	}
}
