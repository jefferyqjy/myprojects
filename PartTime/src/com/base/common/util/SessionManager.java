package com.base.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class SessionManager {

	public static String getAdminUsername() {
		return (String) getHttpSession().getAttribute(Const.Session.ADMIN_USER_NAME);
	}
	
	public static String getFrontUsername() {
		return (String) getHttpSession().getAttribute(Const.Session.FRONT_USER_NAME);
	}
	
	public static String getAdminUserType() {
		return (String)getHttpSession().getAttribute(Const.Session.ADMIN_USER_TYPE);
	}
	
	public static String getFrontUserType() {
		return (String)getHttpSession().getAttribute(Const.Session.FRONT_USER_TYPE);
	}

	public static void setAdminUsername(String username) {
		getHttpSession().setAttribute(Const.Session.ADMIN_USER_NAME, username);
	}
	
	public static void setFrontUsername(String username) {
		getHttpSession().setAttribute(Const.Session.FRONT_USER_NAME, username);
	}

	public static void setAdminUserType(String userType) {
		getHttpSession().setAttribute(Const.Session.ADMIN_USER_TYPE, userType);
	}
	
	public static void setAdminUserFlag(String flag) {
		getHttpSession().setAttribute(Const.Session.ADMIN_USER_FlAG, flag);
	}
	
	public static void setFrontUserType(String userType) {
		getHttpSession().setAttribute(Const.Session.FRONT_USER_TYPE, userType);
	}

	public static HttpSession getHttpSession() {
		return ServletActionContext.getRequest().getSession();
	}
	
	public static String getAttachPath() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request.getSession().getServletContext().getRealPath("") + "\\attachment\\";
	}
	
	public static String getAttachFilePath(String fileName) {
		return getAttachPath() + fileName;
	}
	
	public static String getOrder() {
		String order =(String)getHttpSession().getAttribute("ordertype");
		if(order != null){
			if("DESC".equals(order)){
				getHttpSession().setAttribute("ordertype", "ASC");
			}else{
				getHttpSession().setAttribute("ordertype", "DESC");
			}
		}else{
			order = "DESC";
		}
		return order;
	}

}
