package com.base.common.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.base.log.log4j.util.LogFactory;
import com.opensymphony.xwork2.ActionContext;



public class CommonUtil {
	/**
	 * 如果字符为空或者null 那么返回true反之false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || "".equals(str)) ? true : false;
	}

	/**
	 * 如果字符不为空和null 那么返回true反之false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static boolean isListEmpty(List list) {
		return (list != null && list.size() > 0) ? false : true;
	}
	
	public static boolean isListNotEmpty(List list) {
		return !isListEmpty(list);
	}
	
	/**
	 * 防止出现中文乱码问题，把String重新生成UTF-8编码
	 * @param str
	 * @return
	 */
	public static String genUTF8String(String str) {
		String newStr = null;
		try {
			newStr = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		}catch(Exception e) {
			LogFactory.getLogger().error(e);
		}
		return newStr;
	}
	
	public static String getFileSuffix(String fileName) {
		String suffix = "";
		int index = fileName.lastIndexOf(".");
		if (index != -1) {
			suffix = fileName.substring(index, fileName.length());
		}
		return suffix;
	}
	
	public static String returnErrorMsg(String message) {
		ActionContext.getContext().put(Const.Notification.ERROR, message);
		return Const.Pages.MAPPING_URL;
	}
	
	public static String getSystemTempFolder() {
		return System.getProperty("java.io.tmpdir");
	}
	
	public static void initExcelResponseHeader(HttpServletResponse response, String fileName) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setHeader( "Pragma", "no-cache" );
	    response.addHeader( "Cache-Control", "must-revalidate" );
	    response.addHeader( "Cache-Control", "no-cache" );
	    response.addHeader( "Cache-Control", "no-store" );
	    response.setDateHeader("Expires", 0);
		response.setContentType("application/vnd.ms-excel");
		fileName = new String(fileName.getBytes("GBK"),"ISO8859_1");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
	}
	
	public static boolean isImgFile(String fileName) {
		return (fileName.indexOf(".jpg") != -1 || fileName.indexOf(".jpeg") != -1
				|| fileName.indexOf(".png") != -1 || fileName.indexOf(".gif") != -1
				|| fileName.indexOf(".bmp") != -1
				);
	}
	
	public static String genActionError(String message) {
		ActionContext.getContext().put(Const.ACTION_PUT_ERROR_MSG, message);
		return Const.ACTION_RETURN_ERROR;
	}
}
