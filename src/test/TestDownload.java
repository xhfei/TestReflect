package test;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TestDownload {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			HttpClient httpClient = new HttpClient();
			GetMethod getMethod = new GetMethod(
					"http://fmn.rrimg.com/fmn064/20120503/1145/original_kml1_289d00000076125c.png");
			try {
				// 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
				getMethod.getParams().setParameter(
						HttpMethodParams.RETRY_HANDLER,
						new DefaultHttpMethodRetryHandler());
				getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,
						5 * 1000);

				// 执行getMethod
				int statusCode = httpClient.executeMethod(getMethod);
				System.out.println("statusCode:" + statusCode);
				byte[] picture = getMethod.getResponseBody();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				getMethod.releaseConnection();
				getMethod.abort();
				getMethod = null;
				httpClient = null;
			}
		}
	}

}
