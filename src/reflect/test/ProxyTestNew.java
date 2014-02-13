package reflect.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTestNew {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Class c=Class.forName("reflect.test.Dog");
			reflect.test.Dog dog=(reflect.test.Dog)c.newInstance();
			dog.info();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final IAnimal animal = new Dog();
		MyInvocationHandler myInvocationHandler=new MyInvocationHandler(animal);
		Object proxyObj = Proxy.newProxyInstance(animal.getClass()
				.getClassLoader(), animal.getClass().getInterfaces(),myInvocationHandler);
		if (proxyObj instanceof IAnimal) {
			System.out.println("the proxyObj is an animal!");
		} else {
			System.out.println("the proxyObj isn't an animal!");
		}

		if (proxyObj instanceof Dog) {
			System.out.println("the proxyObj is a dog!");
		} else {
			System.out.println("the proxyObj isn't a dog!");
		}

		IAnimal animalProxy = (IAnimal) proxyObj;
		animalProxy.info();
		animalProxy.hashCode();
		System.out.println(animalProxy.getClass().getName().toString());
	}

}
