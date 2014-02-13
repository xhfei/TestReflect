package reflect.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

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
		Object proxyObj = Proxy.newProxyInstance(animal.getClass()
				.getClassLoader(), animal.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method,
							Object[] args) {
						try {
							System.out.println("被拦截的方法:" + method.getName());
							return method.invoke(animal, args);
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return null;
						}
					}
				});
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
