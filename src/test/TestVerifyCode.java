package test;

import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class TestVerifyCode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String code=getCode();
		String verString=verfyCode("400361098","1363750374687","19477287");
		System.out.println("`````````````````````");
		System.out.println("code     :"+code);
		System.out.println("verString:"+verString);
		System.out.println("verString:"+"1483e265ea49aa7457f6118b2913f362");
	}
	public static String getCode(){
		int randnum =19477287;
        long senddate = 1363750374687L;
//        int randnum = new Random().nextInt();
//        long senddate = new Date().getTime();
        String randomStr = 400361098 + "" + senddate + randnum;
        String code = DigestUtils.md5Hex(randomStr);
        System.out.println("randnum:"+randnum);
        System.out.println("senddate:"+senddate);
        System.out.println("randomStr:"+randomStr);
        System.out.println("code:"+code);
        return code;
	}
	public static String verfyCode(String userId,String senddate,String randnum){
		  String randomStr = userId + senddate + randnum;
          String code = DigestUtils.md5Hex(randomStr);
          return code;
	}
}
