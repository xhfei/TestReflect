package httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class Testhtmlparser {

	public static void main(String[] args) {
		try {
			String strUrl = "http://tieba.baidu.com/p/2023112628?see_lz=1";
			URL url = new URL(strUrl);
			int page = 0;
			try {
				URLConnection conn = url.openConnection();
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"GBK"));
				String line = null;
				StringBuffer document = new StringBuffer();
				while ((line = reader.readLine()) != null) {
					document.append(line + "\n");
					if (line.indexOf("core_title_txt") > 0) {
						String title = line.substring(line.indexOf("\">") + 2, line.indexOf("</"));
						System.out.println("title:" + title);
//						System.out.println("title1:" + new String(title.getBytes("UTF-8"),"gbk"));
						System.out.println("line:" + line);
//						System.out.println("line1:" + new String(line.getBytes("UTF-8"),"gb2312"));
					}
					if (line.indexOf("共有<span class=\"red\">") > 0) {
						String pagecount = line.substring(line.indexOf("red\">") + 5, line.indexOf("</"));
						System.out.println("共有:" + pagecount);
						page = Integer.parseInt(pagecount);
					}
				}
//				System.out.println("document:" + document.toString());
				for (int i = 1; i <= page; i++) {
					URL urlPage = new URL(strUrl + "&pn=" + 1);
					URLConnection connPage = urlPage.openConnection();
					BufferedReader readerPage = new BufferedReader(new InputStreamReader(connPage.getInputStream()));
					while ((line = readerPage.readLine()) != null) {
						document.append(line + "\n");
					}
					String htmlcode = document.toString();
					// 创建Parser对象根据传给字符串和指定的编码
					Parser parser = Parser.createParser(htmlcode, "GBK");
					HtmlPage htmlpage = new HtmlPage(parser);
					try {
						parser.visitAllNodesWith(htmlpage);
					} catch (ParserException e1) {
						e1 = null;
					}
					NodeList nodelist = htmlpage.getBody();
					NodeFilter filter = new TagNameFilter("IMG");
					nodelist = nodelist.extractAllNodesThatMatch(filter, true);
					for (int j = 0; j < nodelist.size(); j++) {
						ImageTag img = (ImageTag) nodelist.elementAt(j);
						if (img.getImageURL().indexOf("imgsrc.baidu.com") > 0) {
							System.out.println(img.getImageURL());
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void downloadImage(String url){
		try {
			DefaultHttpClient  client = new DefaultHttpClient();  
//	 	String url="http://images.sohu.com/uiue/sohu_logo/beijing2008/2008sohu.gif";
			HttpGet get = new HttpGet(url);  
			HttpResponse response =client.execute(get);  
			System.out.println(response.getStatusLine().getStatusCode());
			System.out.println(response.getStatusLine().getReasonPhrase());
			HttpEntity entity = response.getEntity();   
			File storeFile = new File("e:/2008sohu.gif");  
			FileOutputStream output = new FileOutputStream(storeFile);  
			//得到网络资源的字节数组,并写入文件  
			entity.writeTo(output);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
