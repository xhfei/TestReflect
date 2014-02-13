package reflect.image;

import reflect.image.service.ServiceFactory;
import reflect.image.service.impl.DefaultServiceFactory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ServiceFactory serviceFactory = DefaultServiceFactory.getInstance();
		GetImgService imgService=serviceFactory.getService(GetImgService.class);
		imgService.downloadImage("http://images.sohu.com/uiue/sohu_logo/beijing2008/2008sohu.gif", "e:/2008sohu.gif");

	}

}
