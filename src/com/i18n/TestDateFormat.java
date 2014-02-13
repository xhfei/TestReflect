package com.i18n;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


public class TestDateFormat {
	public static void main(String... args) {
		Locale locale = new Locale("en", "US");  
		Date date = new Date();  
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);  
		System.out.println(df.format(date));  
	}
}
