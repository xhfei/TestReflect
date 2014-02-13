package proxy.aop;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import proxy.dto.User;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
		"applicationContext.xml"));
		IMyService myService=(IMyService)factory.getBean("myService");
		User u=new User(10,"name10");
		myService.m1(u, "11");
	}

}
