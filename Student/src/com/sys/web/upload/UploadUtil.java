package com.sys.web.upload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.sys.common.util.UUIDGenerator;
import com.sys.log.LogFactory;

public class UploadUtil implements IUpload {
	private static UploadUtil util = null;
	public static IUpload getInstance(){
		if(util == null) {
			util = new UploadUtil();
		}
		return util;
	}
	
	private UploadUtil(){}


	public void upload(String fileName, HttpServletRequest request,
			HttpServletResponse response) {
		String newFileName = UUIDGenerator.genFileName();
		
	}

}
