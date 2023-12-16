package com.tutorialninja.qa.utils;

import java.util.Date;

public class Utilities {
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String dateTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "manoharkantjoshi"+dateTimeStamp+"@gmail.com";
	}
}
