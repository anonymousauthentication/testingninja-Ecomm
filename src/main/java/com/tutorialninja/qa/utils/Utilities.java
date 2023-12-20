package com.tutorialninja.qa.utils;

import java.util.Date;

public class Utilities {
	public static final int IMPLICITE_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;
	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String dateTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "manoharkantjoshi"+dateTimeStamp+"@gmail.com";
	}
}
