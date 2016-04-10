package com.sys.web.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUpload {
	public void upload(String fileName,HttpServletRequest request,HttpServletResponse response);
	
	
}
