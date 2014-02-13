package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TestDate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date testGmt=new Date();
		Calendar cc=Calendar.getInstance();
		SimpleDateFormat sdfTestGmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		sdfTestGmt.setTimeZone(TimeZone.getTimeZone("GMT+9"));
		System.out.println("GMT+9="+cc.getTime());
		System.out.println("GMT+9="+sdfTestGmt.format(cc.getTime()));
		sdfTestGmt.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		System.out.println("GMT+8="+cc.getTime());
		System.out.println("GMT+8="+sdfTestGmt.format(cc.getTime()));
		long dateNum=1359561599140L;
		Date GUIDE_TIME=new Date(dateNum);
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,30);
		calendar.set(Calendar.HOUR_OF_DAY,23);
		calendar.set(Calendar.MINUTE,59);
		calendar.set(Calendar.SECOND,59);
		Date d=calendar.getTime();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(d));
		System.out.println(d.getTime());
		Date popDate=new Date(dateNum);
		System.out.println(sdf.format(popDate));
		System.out.println(new Date().before(popDate));
		System.out.println(sdf.format(GUIDE_TIME));
	}

}
