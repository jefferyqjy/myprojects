/**
 *
 */
package com.pro.exception;

/**
 * @author sezhao
 * 
 */
public class ProException extends Exception {

	/**
     *
     */
	private static final long serialVersionUID = 1L;

	private String errMsg;
	private String errCode;

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
