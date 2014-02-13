package reflect.my;

import java.lang.reflect.Constructor;

public class MyReflect {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws Throwable{
		Class clazz=Thread.currentThread().getContextClassLoader().loadClass("reflect.my.Cat");
		Constructor[] defaultCons=clazz.getDeclaredConstructors();
		System.out.println(defaultCons.length);
	}

}
