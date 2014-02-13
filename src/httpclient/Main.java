package httpclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("hostid", "223157279"));
		qparams.add(new BasicNameValuePair("uploadid", String.valueOf(System.currentTimeMillis()%10000)));
		qparams.add(new BasicNameValuePair("pagetype", "addtestplain"));
		URI uri = URIUtils.createURI("http", "upload.renren.com", -1, "/upload.fcgi",
		URLEncodedUtils.format(qparams, "UTF-8"), null);
		HttpPost httppost = new HttpPost(uri);
		System.out.println(httppost.getURI());
	}

}
