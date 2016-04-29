package com.base.log.log4j.util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class LogManager {
	private static Logger logger = null;
	private static boolean isInit = false;
	
	private static void initConfig() {
		System.setProperty("log.path","c:\\");
		String path = LogManager.class.getResource("").getPath()+"log4j.xml";
		DOMConfigurator.configure(path);
		logger = Logger.getLogger("sys.logger");
		isInit = true;
	}
	
	public static Logger getLogger() {
		if(isInit) {
			return logger;
		}else {
			initConfig();
			return logger;
		}
	}
	
}
