package com.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static boolean checkEmpty(String input) {
		return input == null || "".equals(input.trim());
	}

	public static boolean isVaildEmail(String email) {
		String emailPattern = "[a-zA-Z0-9][a-zA-Z0-9._-]{2,16}[a-zA-Z0-9]@[a-zA-Z0-9]+.[a-zA-Z0-9]+";
		boolean result = Pattern.matches(emailPattern, email);
		return result;
	}

	public static boolean checkEqual(String arg1, String arg2) {
		boolean f1 = checkEmpty(arg1);
		boolean f2 = checkEmpty(arg2);
		if (!f1 && !f2) {
			if (arg1.equals(arg2)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean checkUserName(String str) {
		Pattern p = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5_]+$");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static String toUTF8(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception ex) {
			return null;
		}
	}

	public static boolean isNum(String str) {
		try {
			Integer.parseInt(str.trim());
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public static String getRandomString(int len) {

		String chars [] = {"0","1","2","3","4",
				"5","6","7","8","9"};

		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		for (int i = 0; i< len; i++) {
			sb.append(chars[r.nextInt(10)]);
		}
		return sb.toString();
	}

}
