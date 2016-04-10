package com.sys.web.download;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IDownload {
	public void download(String fileName,HttpServletRequest request,HttpServletResponse response);
}
