package httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

import util.IResult;
import util.ResultSupport;

public class GetImageUtils {
	
	
	public static void main(String[] args) throws Exception{
		String urlStr="http://tieba.baidu.com/p/2100641351?see_lz=1";
		try {
			URL url = new URL(urlStr);
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
					}
					if (line.indexOf("共有<span class=\"red\">") > 0) {
						String pagecount = line.substring(line.indexOf("red\">") + 5, line.indexOf("</"));
						System.out.println("共有:" + pagecount);
						page = Integer.parseInt(pagecount);
					}
				}
				StringBuffer pageContent = null;
				for (int i = 1; i <= page; i++) {
					pageContent = new StringBuffer();
					URL urlPage =null;
					if(urlStr.indexOf("?")>=0){
						if((urlStr.lastIndexOf("?")+1)==urlStr.length()){
							urlPage =new URL(urlStr + "pn=" + i);
						}else{
							urlPage =new URL(urlStr + "&pn=" + i);
						}
					}else{
						urlPage =new URL(urlStr + "?pn=" + i);
					}
					URLConnection connPage = urlPage.openConnection();
					BufferedReader readerPage = new BufferedReader(new InputStreamReader(connPage.getInputStream()));
					while ((line = readerPage.readLine()) != null) {
						pageContent.append(line + "\n");
					}
					String htmlcode = pageContent.toString();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static IResult getImageUrl(String urlStr){
		IResult result=new ResultSupport();
		List<String> imgList=new ArrayList<String>();
		try {
			URL url = new URL(urlStr);
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
//						System.out.println("title:" + title);
						result.setResult("title", title);
					}
					if (line.indexOf("共有<span class=\"red\">") > 0) {
						String pagecount = line.substring(line.indexOf("red\">") + 5, line.indexOf("</"));
						System.out.println("共有:" + pagecount);
						page = Integer.parseInt(pagecount);
					}
				}
				StringBuffer pageContent = null;
				for (int i = 1; i <= page; i++) {
					pageContent = new StringBuffer();
					URL urlPage =null;
					if(urlStr.indexOf("?")>=0){
						if((urlStr.lastIndexOf("?")+1)==urlStr.length()){
							urlPage =new URL(urlStr + "pn=" + i);
						}else{
							urlPage =new URL(urlStr + "&pn=" + i);
						}
					}else{
						urlPage =new URL(urlStr + "?pn=" + i);
					}
					URLConnection connPage = urlPage.openConnection();
					BufferedReader readerPage = new BufferedReader(new InputStreamReader(connPage.getInputStream()));
					while ((line = readerPage.readLine()) != null) {
						pageContent.append(line + "\n");
					}
					String htmlcode = pageContent.toString();
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
							imgList.add(img.getImageURL());
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.setResult("list", imgList);
		return result;
	}
}
