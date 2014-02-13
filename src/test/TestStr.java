package test;

public class TestStr {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String wapDownloadUrl="http://apkdl.xnimg.cn/developers/android/245261-1386666551041.apk";
		System.out.println(wapDownloadUrl.substring(wapDownloadUrl.lastIndexOf("/")).replaceAll("/", ""));
	}

}
