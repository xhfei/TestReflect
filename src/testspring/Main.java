package testspring;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource(
				"applicationContext.xml"));
		System.out.println(factory.getBean("random").toString());
		System.out.println(factory.getBean("currentTime1"));
		System.out.println(factory.getBean("currentTime2"));
		System.out.println(factory.getBean("pi"));
	}

}
