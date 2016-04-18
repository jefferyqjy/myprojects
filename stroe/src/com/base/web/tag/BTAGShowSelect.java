package com.base.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.base.common.util.CommonUtil;
import com.base.sys.manager.AdminManager;

public class BTAGShowSelect extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hql;
	private String name;
	private String event;
	private String empty;

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if ("month".equalsIgnoreCase(hql)) {
				JspWriter out = this.pageContext.getOut();
				StringBuffer sb = new StringBuffer();
				sb.append("<select name=\"").append(name).append("\" id=\"").append(name).append("\" ");
				if (CommonUtil.isNotEmpty(event)) {
					sb.append(event);
				}
				sb.append(" >");
				if ("true".equalsIgnoreCase(this.empty)) {
					sb.append("<option></option>");
				}
				String[] strs = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
				for (String str : strs) {
					sb.append("<option value=\"").append(str).append("\">");
					sb.append(str).append("</option>");
				}
				sb.append("</select>");
				out.print(sb.toString());
			} else if("hour".equalsIgnoreCase(hql)){
				JspWriter out = this.pageContext.getOut();
				StringBuffer sb = new StringBuffer();
				sb.append("<select name=\"").append(name).append("\" id=\"").append(name).append("\" ");
				if (CommonUtil.isNotEmpty(event)) {
					sb.append(event);
				}
				sb.append(" >");
				if ("true".equalsIgnoreCase(this.empty)) {
					sb.append("<option></option>");
				}
				String[] strs = new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12","13","14","15","16","17","18","19","20","21","22","23","00" };
				for (String str : strs) {
					sb.append("<option value=\"").append(str).append("\">");
					sb.append(str).append("</option>");
				}
				sb.append("</select>");
				out.print(sb.toString());
			} else if("minute".equalsIgnoreCase(hql)){
				JspWriter out = this.pageContext.getOut();
				StringBuffer sb = new StringBuffer();
				sb.append("<select name=\"").append(name).append("\" id=\"").append(name).append("\" ");
				if (CommonUtil.isNotEmpty(event)) {
					sb.append(event);
				}
				sb.append(" >");
				if ("true".equalsIgnoreCase(this.empty)) {
					sb.append("<option></option>");
				}
				String[] strs = new String[60];
				for(int i=0;i<60;i++) {
					if(i<10) {
						strs[i] = "0"+i;
					} else {
						strs[i] = ""+i;
					}
				}
				for (String str : strs) {
					sb.append("<option value=\"").append(str).append("\">");
					sb.append(str).append("</option>");
				}
				sb.append("</select>");
				out.print(sb.toString());
			} else {
				AdminManager manager = (AdminManager) WebApplicationContextUtils.getRequiredWebApplicationContext(
						this.pageContext.getServletContext()).getBean("adminManager");
				List list = manager.queryByHql(hql);
				if (list != null && list.size() > 0) {
					JspWriter out = this.pageContext.getOut();
					StringBuffer sb = new StringBuffer();
					sb.append("<select name=\"").append(name).append("\" id=\"").append(name).append("\" ");
					if (CommonUtil.isNotEmpty(event)) {
						sb.append(event);
					}
					sb.append(" >");
					int len = list.size();
					Object[] objs = null;
					if ("true".equalsIgnoreCase(this.empty)) {
						sb.append("<option></option>");
					}
					for (int i = 0; i < len; i++) {
						objs = (Object[]) list.get(i);
						sb.append("<option value=\"").append(objs[0]).append("\">");
						sb.append(objs[1]).append("</option>");
					}
					sb.append("</select>");
					out.print(sb.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
