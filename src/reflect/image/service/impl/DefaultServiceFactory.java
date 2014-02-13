package reflect.image.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

import reflect.image.annotation.PicService;
import reflect.image.service.MyInvocationHandler;
import reflect.image.service.ServiceFactory;


public class DefaultServiceFactory implements ServiceFactory{
	private static DefaultServiceFactory INSTANCE=new DefaultServiceFactory();
	private DefaultServiceFactory(){
		
	}
	public static DefaultServiceFactory getInstance(){
		return INSTANCE;
	}
	public <T> T getService(Class<T> serviceInterface){
		boolean isPicService=false;
		for (Annotation annotation : serviceInterface.getAnnotations()) {
			if (annotation instanceof PicService) {
				isPicService=true;
				break;
			}
		}
		if(isPicService){
			MyInvocationHandler handler=new MyInvocationHandler();
			T proxy = (T)Proxy.newProxyInstance(
					Thread.currentThread().getContextClassLoader(), 
					new Class<?>[]{serviceInterface}, handler);
			return proxy;
		}else{
			return null;
		}
	}
}
