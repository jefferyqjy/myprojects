/**
 *
 */
package com.pro.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pro.cfg.ErrHandlerConfig;
import com.pro.errhandling.ErrorHandler;
import com.pro.utils.CommonUtils;

/**
 * @author sezhao
 * 
 */
@Service
public class ExceptionBuilder {

	@Autowired
	private ErrHandlerConfig errConfig;

	public ProException buildException(String errCode) {

		ProException exception = new ProException();
		exception.setErrCode(errCode);
		exception.setErrMsg(errConfig.getErrorMsg(errCode));
		ErrorHandler.pushError(exception);
		return exception;
	}

	public ProException buildException(String errCode, String parameter) {
		return buildException(errCode, new String[] { parameter });
	}

	public ProException buildException(String errCode, String[] parameters) {

		ProException exception = new ProException();
		exception.setErrCode(errCode);
		exception.setErrMsg(CommonUtils.getDetailMsg(errConfig.getErrorMsg(errCode), parameters));
		ErrorHandler.pushError(exception);
		return exception;
	}
}
