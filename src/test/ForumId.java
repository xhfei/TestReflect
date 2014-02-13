package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForumId {
	private static final String img_prefix = "<img src=\"";
	private static final String fullImg = "<img src=\"http://[^\"]*(jpg|jpeg|png|gif|bmp)";
//	private static final String regImg = "http://[^\"]*(jpg|jpeg|png|gif|bmp)";
	private static final String regEmotions = "http://s.xnimg.cn";
	private static final String suffixImg = "[\\w]+.(jpg|jpeg|png|gif|bmp)";
	private static final String forumUrl = "http://xiaozu.renren.com/xiaozu/\\d+";
	private static final String forumId = "\\d+";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String forumUrl1="http://xiaozu.renren.com/xiaozu/258409";
		String forumUrl2="http://xiaozu.renren.com/xiaozu/258409/358110144?ref=discover";
		Pattern forumUrlPattern = Pattern.compile(forumUrl,Pattern.CASE_INSENSITIVE);
		Matcher m1 = forumUrlPattern.matcher(forumUrl2);
		String res1=null;
		String res2=null;
		while (m1.find()) {
			res1=m1.group();
		}
		Pattern forumIdPattern = Pattern.compile(forumId,Pattern.CASE_INSENSITIVE);
		Matcher m2 = forumIdPattern.matcher(res1);
		while (m2.find()) {
			res2=m2.group();
		}
		System.out.println(res2);
	}

}
