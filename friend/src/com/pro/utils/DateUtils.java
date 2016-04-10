package com.pro.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
	private static final SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * Returns the current date time in the following Format yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static final String getCurrentDate() {
		try {
			Date date = new Date();
			return SDF.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static final String getCurrentDateTime() {
		try {
			Date date = new Date();
			return SDF2.format(date);
		} catch (Exception e) {
			return "";
		}
	}
}
