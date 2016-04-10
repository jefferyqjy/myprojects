package com.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sys.admin.entity.SysAdminManager;
import com.sys.common.util.CommonUtil;

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
			SysAdminManager manager = (SysAdminManager) WebApplicationContextUtils
			.getRequiredWebApplicationContext(
					this.pageContext.getServletContext()).getBean(
					"sysAdminManager");
			List list = manager.queryByHql(hql);
			if(list != null && list.size() > 0) {
				JspWriter out = this.pageContext.getOut();
				StringBuffer sb = new StringBuffer();
				sb.append("<select name=\"").append(name).append("\" id=\"").append(name).append("\" ");
				if(CommonUtil.isNotEmpty(event)) {
					sb.append(event);
				}
				sb.append(" >");
				int len = list.size();
				Object[] objs = null;
				if("true".equalsIgnoreCase(this.empty)) {
					sb.append("<option></option>");
				}
				for(int i=0;i<len;i++) {
					objs = (Object[])list.get(i);
					sb.append("<option value=\"").append(objs[0]).append("\">");
					sb.append(objs[1]).append("</option>");
				}
				sb.append("</select>");
				out.print(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
