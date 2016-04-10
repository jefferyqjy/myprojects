package com.pro.errhandling;

import java.util.logging.Logger;

import com.pro.exception.ProException;

public class ErrorHandler {

	private static final Logger logger = Logger.getLogger(ErrorHandler.class.getName());

	public static void pushError(ProException error) {
		ErrorHandler.log(error);
	}

	private static void log(ProException error) {

		if (error != null) {
			logger.info("[ERROR-CODE:" + error.getErrCode() + "][ERROR-MSG:" + error.getErrMsg() + "]");
		}
	}
}
