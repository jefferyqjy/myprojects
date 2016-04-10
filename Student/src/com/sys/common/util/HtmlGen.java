package com.sys.common.util;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class HtmlGen {
	
	public static String genSelect(List<?> list,String name, String event, HttpServletResponse response) {
		try {
			if(list != null && list.size() > 0) {
				response.setCharacterEncoding("UTF-8");
				response.setHeader("charset","UTF-8");
				response.setContentType("text/xml;charset=UTF-8");
				PrintWriter out = response.getWriter();
				StringBuffer sb = new StringBuffer();
				sb.append("<select name=\"").append(name).append("\" id=\"").append(name).append("\" ");
				if(CommonUtil.isNotEmpty(event)) {
					sb.append(event);
				}
				sb.append(" >");
				int len = list.size();
				Object[] objs = null;
				for(int i=0;i<len;i++) {
					objs = (Object[])list.get(i);
					sb.append("<option value=\"").append(objs[0]).append("\">");
					sb.append(objs[1]).append("</option>");
				}
				sb.append("</select>");
				out.print(sb.toString());
				System.out.println(response.getCharacterEncoding());
				out.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
