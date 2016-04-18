package com.base.log.log4j.util;

import org.apache.log4j.Logger;

public class LogFactory {
	public static Logger getLogger() {
		try {
			return LogManager.getLogger();
		}catch(Exception e) {
			return null;
		}
	}

}
