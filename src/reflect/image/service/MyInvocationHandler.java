package reflect.image.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import reflect.image.annotation.GetPic;

public class MyInvocationHandler implements InvocationHandler{
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if (Object.class == method.getDeclaringClass()) {
			return method.invoke(this, args);
        }
		GetPic getPic = method.getAnnotation(GetPic.class);
		if(getPic!=null){
			String methodType=getPic.type();
			String methodValue=getPic.value();
			String url=args[0].toString();
			String localFile=args[1].toString();
			System.out.println("methodType:"+methodType);
			System.out.println("methodValue:"+methodValue);
			System.out.println("url:"+url);
			System.out.println("localFile:"+localFile);
			ImageDefinition imgDef=new ImageDefinition(url,localFile,methodType);
			if(imgDef!=null){
				DownLoadImage downLoadImage=new DownLoadImage(imgDef);
				downLoadImage.download();
			}
		}
		return null;
	}
}
