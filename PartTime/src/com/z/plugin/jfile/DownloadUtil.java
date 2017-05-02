package com.z.plugin.jfile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadUtil {
	private static DownloadUtil util = new DownloadUtil();

	public static DownloadUtil getInstance() {
		return util;
	}

	public void download(String filePath,String fileName, String contentType, HttpServletResponse response) {
		File file = new File(filePath);
		if (file.exists()) {
			try {
				FileInputStream in = new FileInputStream(filePath);
				ByteArrayOutputStream ob = new ByteArrayOutputStream();
				byte[] b = new byte[10240];
				int len = in.read(b);
				while (len > 0) {
					ob.write(b, 0, len);
					len = in.read(b);
				}
				b = ob.toByteArray();
				ob.close();
				in.close();
				response.setCharacterEncoding("UTF-8");
				if (contentType == null || "".equals(contentType)) {
					response.setContentType("html/text");
				} else {
					response.setContentType(contentType);
				}
				fileName = new String(fileName.getBytes("GBK"),"ISO8859_1");   
				response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
				OutputStream out1 = response.getOutputStream();
				try {
					out1.write(b);
					out1.close();
				} catch (Exception e) {
					out1.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
