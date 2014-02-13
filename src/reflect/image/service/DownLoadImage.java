package reflect.image.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class DownLoadImage {
	private ImageDefinition imageDefinition;
	public DownLoadImage(ImageDefinition imageDefinition){
		this.imageDefinition=imageDefinition;
	}
	public void download()throws IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(imageDefinition.getUrl());
		HttpResponse response = client.execute(get);
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		HttpEntity entity = response.getEntity();
		File storeFile = new File(imageDefinition.getLocalFile());
		FileOutputStream output = new FileOutputStream(storeFile);
		// 得到网络资源的字节数组,并写入文件
		entity.writeTo(output);
		// InputStream in=entity.getContent();
		// int len=0;
		// byte[] bs=new byte[1024];
		// while((len=in.read(bs))>0){
		// output.write(bs);
		// }
		// output.close();
		entity.consumeContent();
	}
}
