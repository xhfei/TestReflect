package httpclient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestGetImage {
	 public static void main(String[] args) throws IOException{  
		 	DefaultHttpClient  client = new DefaultHttpClient();  
		 	HttpGet get = new HttpGet("http://tieba.baidu.com/p/2023112628?see_lz=1");  
	        HttpResponse response =client.execute(get);  
	        HttpEntity entity = response.getEntity();   
	        File storeFile = new File("e:/2008sohu.gif");  
	        FileOutputStream output = new FileOutputStream(storeFile);  
	        //得到网络资源的字节数组,并写入文件  
	        InputStream in=entity.getContent();
	        int len=0;
	        byte[] bs=new byte[1024];
	        while((len=in.read(bs))>0){
	        	output.write(bs);
	        }
	        output.close();  
	        entity.consumeContent();
	    }  
}
