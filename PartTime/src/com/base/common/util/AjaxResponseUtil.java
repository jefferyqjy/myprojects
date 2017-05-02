package com.base.common.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.manager.xml.util.XMLUtil;

public class AjaxResponseUtil {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private AjaxReturnInfo rtnInfo = null;

	private AjaxResponseUtil(HttpServletRequest request,
			HttpServletResponse reseponse, AjaxReturnInfo info) {
		this.request = request;
		this.response = reseponse;
		this.rtnInfo = info;
		response.setCharacterEncoding("UTF-8");
		response.setHeader("charset", "UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
	}

	public static AjaxResponseUtil getInstance(HttpServletRequest request,
			HttpServletResponse response, AjaxReturnInfo info) {
		return new AjaxResponseUtil(request, response, info);
	}

	public static AjaxResponseUtil getInstance(AjaxReturnInfo info) {
		return getInstance(ServletActionContext.getRequest(), ServletActionContext.getResponse(), info);
	}

	public void setRtnInfo(AjaxReturnInfo rtnInfo) {
		this.rtnInfo = rtnInfo;
	}

	public String respToClient() {
		try {
			PrintWriter out = response.getWriter();
			if (this.rtnInfo == null) {
				rtnInfo = new AjaxReturnInfo();
			}
			String rtnXml = XMLUtil.convertToString(rtnInfo.genReturnXMLInfo());
			out.print(rtnXml);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
