package com.base.common.util;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.manager.xml.util.XMLUtil;

public class AjaxReturnInfo {
	
	private String errCode;
	private String errMsg;
	private String errLevel;
	private boolean isSuccess = true;;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.isSuccess = false;
		this.errMsg = errMsg;
	}
	public String getErrLevel() {
		return errLevel;
	}
	public void setErrLevel(String errLevel) {
		this.errLevel = errLevel;
	}
	
	public Document genReturnXMLInfo() {
		Document doc = new DocumentImpl();
		Element ndRoot = doc.createElement("root");
		doc.appendChild(ndRoot);
		if(isSuccess) {
			ndRoot.setAttribute("hasError", "N");
		} else {
			ndRoot.setAttribute("hasError", "Y");
			Element errCode = doc.createElement("errCode");
			Element errMsg = doc.createElement("errMsg");
			Element errLevel = doc.createElement("errLevel");
			XMLUtil.SetNodeValue(doc, errCode, this.errCode);
			XMLUtil.SetNodeValue(doc, errMsg, this.errMsg);
			XMLUtil.SetNodeValue(doc, errLevel, this.errLevel);
			ndRoot.appendChild(errCode);
			ndRoot.appendChild(errMsg);
			ndRoot.appendChild(errLevel);
		}
		return doc;
	}

}
