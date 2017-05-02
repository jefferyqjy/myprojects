package com.base.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public final static String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

	public static Date strToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date strToDateByPattern(String date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String dateToStrByPattern(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String convertSQLDate2String(java.sql.Date sqlDate, String pattern) {
		Date date = new Date(sqlDate.getTime());
		return convDate2String(date, pattern);
	}

	public static java.sql.Date toSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Timestamp toTimestamp(java.util.Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	public static java.util.Date convString2Date(String strDate, String pattern) {
		try {
			SimpleDateFormat df1 = new SimpleDateFormat(pattern);
			java.util.Date date = df1.parse(strDate);
			return date;
		} catch (ParseException e) {
			throw new IllegalArgumentException(strDate + " is not Date.");
		}
	}

	public static java.util.Date convString2Date(String strDate) {
		if (strDate == null) {
			return null;
		}
		if (strDate.length() > 1)
			return convString2Date(strDate, DEFAULT_DATE_PATTERN);
		return null;
	}

	public static String convDate2String(java.util.Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formator = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		return formator.format(date);
	}

	public static String convDate2String(java.util.Date date, String dateFormat) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formator = new SimpleDateFormat(dateFormat);
		return formator.format(date);
	}

	public static String getDate(String strDateFormat, String sFldValue) throws Exception {
		try {
			Date date = getDate(sFldValue);
			String strDateShow = new SimpleDateFormat(strDateFormat).format(date);
			return strDateShow;
		} catch (Exception e) {
			throw e;
		}
	}

	public static Date getDate(String str) throws Exception {
		try {
			Date date = null;
			DateFormat df = null;
			df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINESE);
			date = df.parse(str);
			return date;
		} catch (Exception e) {
			throw e;
		}
	}

	public static int getYear(Object date) {
		try {
			java.util.Date dt = null;
			if (date instanceof String) {
				String d = (String) date;
				dt = getDate(d);
			} else if (date instanceof java.util.Date) {
				dt = (java.util.Date) date;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			return cal.get(cal.YEAR);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int getMonth(Object date) {
		try {
			java.util.Date dt = null;
			if (date instanceof String) {
				String d = (String) date;
				dt = getDate(d);
			} else if (date instanceof java.util.Date) {
				dt = (java.util.Date) date;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			return cal.get(cal.MONTH) + 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static int getDay(Object date) {
		try {
			java.util.Date dt = null;
			if (date instanceof String) {
				String d = (String) date;
				dt = getDate(d);
			} else if (date instanceof java.util.Date) {
				dt = (java.util.Date) date;
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			return cal.get(cal.DAY_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static String getCurrentDateTimeString() {
		return convDate2String(new Date(), DEFAULT_DATE_TIME_PATTERN);
	}

	public static String getCurrentDateString() {
		return convDate2String(new Date(), DEFAULT_DATE_PATTERN);
	}

	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}

	public static String getTimeString() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		StringBuilder sb = new StringBuilder();
		sb.append(cal.get(Calendar.YEAR));
		sb.append(cal.get(Calendar.MONTH));
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(cal.get(Calendar.HOUR_OF_DAY));
		sb.append(cal.get(Calendar.MINUTE));
		sb.append(cal.get(Calendar.SECOND));
		sb.append(cal.get(Calendar.MILLISECOND));
		return sb.toString();
	}
	
	/*public static String getCurrentDateTimeString() {
		return convDate2String(new Date(), DEFAULT_DATE_TIME_PATTERN);
	}*/
	
	public static Date getCurrentDateTime() {
		return convString2Date(getCurrentDateTimeString(), DEFAULT_DATE_TIME_PATTERN);
	}
}
