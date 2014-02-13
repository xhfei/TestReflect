package com.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestResourceBundle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    ResourceBundle rb1 = ResourceBundle.getBundle("com.i18n.resource", Locale.US);  
	    ResourceBundle rb2 = ResourceBundle.getBundle("com.i18n.resource", Locale.CHINA);  
	    ResourceBundle rb3 = ResourceBundle.getBundle("com.i18n.resource");  
	    System.out.println("us:"+rb1.getString("greeting.common"));  
	    System.out.println("cn:"+rb2.getString("greeting.common"));  
	    System.out.println("rb3:"+rb3.getString("greeting.common"));  
	}

}
