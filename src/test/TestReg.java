package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {
	private static Pattern URL_PATTERN =Pattern.compile("^(https|http|ftp|rtsp|mms)://\\w+(\\.\\w+)*(:\\d+)?(/[.\\w]+)*/?$");
	private static Pattern ID_PATTERN=Pattern.compile(".*\\d+.*");
	public static void main(String...args){
		String url="https://itunes.apple.com/cn/app/wanpai/id618952474.apk";
		Matcher m = URL_PATTERN.matcher(url);         
        System.out.println(m.matches());  
        
        String line="=========appid:242225=========school_id:1005=========";
        Matcher lineMatcher = URL_PATTERN.matcher(line);
        if(lineMatcher.matches()){
        	System.out.println(lineMatcher.group());  
        }
	}
}
