package testspring;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanimpl implements FactoryBean {
	
	public Object getObject() throws Exception {
		return new Double(3.14159265358979);
	}

	public Class getObjectType() {
		return Double.class;
	}

	public boolean isSingleton() {
		return true;
	}
}