package com.pro.db;

import java.util.HashMap;

public class DaoConvertManager {

	private static HashMap<String, String> converts;
	private static boolean isInit = false;
	private static Object lock = new Object();
	public static void init(){
		synchronized(lock) {
			try {
				loadConverts();
				isInit = true;
			} catch (Exception e) {
				converts = null;
				isInit = false;
			}
		}
	}

	private static void loadConverts() {
		converts = new HashMap<String, String>();
		converts.put("ACXDB_CT_USER", "com.CT.convert.impl.UserConvert");
	}

	public static boolean isInit() {
		return isInit;
	}

	public static String getConvert(String key) {
		if (!isInit) {
			init();
		}
		if (converts.containsKey(key)) {
			return (String)converts.get(key);
		} else {
			return null;
		}
	}

	public static InquireConvert createConvert(String convertClass) throws Exception{
		InquireConvert ic = null;
		try {
			ic = (InquireConvert)Class.forName(convertClass).newInstance();
		} catch (Exception e) {
			//throw DaoHelperException
		}
		return ic;
	}
}
