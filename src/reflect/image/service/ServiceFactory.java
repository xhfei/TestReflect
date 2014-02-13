package reflect.image.service;

public interface ServiceFactory {
	 
	public <T> T getService(Class<T> serviceInterface);

}
