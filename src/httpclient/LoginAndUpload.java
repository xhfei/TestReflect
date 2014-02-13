package httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import util.IResult;
import util.ResultSupport;

public class LoginAndUpload {
	private static String userName = "feile1999@tom.com";
	private static String password = "ronaldo2008";
	private static String redirectURL = "http://blog.renren.com/blog/223157279/485841424";
//	private static String redirectURL = "http://www.renren.com/223157279";

	private static String renRenLoginURL = "http://www.renren.com/PLogin.do";

	private HttpResponse response;
	private DefaultHttpClient httpclient = new DefaultHttpClient();
	private String tic = null;

	public static void main(String... args) {
		try {
			LoginAndUpload lg = new LoginAndUpload();
			lg.login();
//			lg.printText();
			lg.upload();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login() {
		HttpPost httpost = new HttpPost(renRenLoginURL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("origURL", redirectURL));
		nvps.add(new BasicNameValuePair("domain", "renren.com"));
		nvps.add(new BasicNameValuePair("isplogin", "true"));
		nvps.add(new BasicNameValuePair("formName", ""));
		nvps.add(new BasicNameValuePair("method", ""));
		nvps.add(new BasicNameValuePair("submit", "登录"));
		nvps.add(new BasicNameValuePair("email", userName));
		nvps.add(new BasicNameValuePair("password", password));
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			response = httpclient.execute(httpost);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("statusCode:" + statusCode);
			HttpEntity entity = response.getEntity();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println("content:" + sb.toString());
			String content = sb.toString();
			if (content.indexOf("t=") > -1) {
				tic = content.substring(content.indexOf("t=") + 2, content
						.indexOf("&"));
			}
			CookieStore cookieStore = httpclient.getCookieStore();
			BasicClientCookie tCookie = new BasicClientCookie("t", tic);
			tCookie.setDomain("renren.com");
			tCookie.setPath("/");
			cookieStore.addCookie(tCookie);
			httpclient.setCookieStore(cookieStore);
			List<Cookie> cookies = cookieStore.getCookies();
			for (Cookie c : cookies) {
				System.out.println("cookie--" + c.getName() + ":"
						+ c.getValue());
			}
			System.out.println("=========");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			httpost.abort();
		}
		return true;
	}

	public void upload() throws Exception {
		List<String> list=new ArrayList<String>();
		list.add("E:/photo/home/IMG_1481.JPG");
		list.add("E:/photo/home/IMG_1482.JPG");
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("hostid", "223157279"));
		qparams.add(new BasicNameValuePair("uploadid", String.valueOf(System
				.currentTimeMillis() % 10000)));
		qparams.add(new BasicNameValuePair("pagetype", "xiaozuclient"));
		// qparams.add(new BasicNameValuePair("pagetype", "addtestplain"));
		URI uri = URIUtils.createURI("http", "upload.renren.com", -1,
				"/upload.fcgi", URLEncodedUtils.format(qparams, "UTF-8"), null);
		System.out.println(uri.toString());
		for(String fileName:list){
			HttpPost httppost = new HttpPost(uri);
			httppost.setHeader("enctype", "multipart/form-data");
			// 创建待处理的文件
			FileBody file1 = new FileBody(new File("E:/photo/home/IMG_1481.JPG"));
			MultipartEntity reqEntity = new MultipartEntity();
			reqEntity.addPart("file1", file1);
			// 设置请求
			httppost.setEntity(reqEntity);
			// 执行
			HttpResponse response = httpclient.execute(httppost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				// 显示内容
				if (entity != null) {
					String jsonStr = EntityUtils.toString(entity);
					JSONObject jObject = JSONObject.fromObject(jsonStr);
					System.out.println(jObject);
					IResult result = getImgUrlFromJson(jObject);
					List<String> imageUrl = (List<String>) result
							.getDefaultResult();
					if (null != imageUrl) {
						for (String s : imageUrl) {
							System.out.println(s);
						}
					}
				}
				if (entity != null) {
					entity.consumeContent();
				}
			}
		}
	}

	// 处理上传返回的json结果
	private IResult getImgUrlFromJson(JSONObject json) {
		IResult result = new ResultSupport();
		result.setSuccess(false);
		String code = json.getString("code");
		if (!"0".equals(code)) {
			return result;
		}
		JSONArray fileJson = json.getJSONArray("files");
		List<String> imageUrl = new ArrayList<String>();
		for (int i = 0; i < fileJson.size(); i++) {
			JSONObject imgarr = fileJson.getJSONObject(i);
			JSONArray images = (JSONArray) imgarr.get("images");
			JSONObject imgJson = JSONObject.fromObject(images.get(0));
			Object object = imgJson.get("url");
			if (null != object || "".equals(object.toString())) {
				imageUrl.add(object.toString());
				// String url=AlbumUtil.getPhotoURL(object.toString());
				// if(!StringUtils.isBlank(url)){
				// imageUrl.add(url);
				// }
			}
		}
		result.setDefaultResult(imageUrl);
		result.setSuccess(true);
		return result;
	}

	private String getRedirectLocation() {
		Header locationHeader = response.getFirstHeader("Location");
		if (locationHeader == null) {
			return null;
		}
		return locationHeader.getValue();
	}

	private String getText(String redirectLocation) {
		HttpGet httpget = new HttpGet(redirectLocation);
		// Create a response handler
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = "";
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (Exception e) {
			e.printStackTrace();
			responseBody = null;
		} finally {
			httpget.abort();
			httpclient.getConnectionManager().shutdown();
		}
		return responseBody;
	}

	public void printText() {
		String redirectLocation = getRedirectLocation();
		if (redirectLocation != null) {
			System.out.println(getText(redirectLocation));
		}
	}
}
