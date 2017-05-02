package com.base.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.base.common.util.SessionManager;
import com.base.sys.entity.Shortcut;
import com.base.sys.manager.ShortcutManager;

public class BTAGGetShortcut extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8401828202755766300L;

	@Override
	public int doStartTag() throws JspException {
		try {
			ShortcutManager manager = (ShortcutManager) WebApplicationContextUtils.getRequiredWebApplicationContext(
					this.pageContext.getServletContext()).getBean("shortcutManager");
			/*String hql = "from Shortcut where username = '" + SessionManager.getAdminUsername() + "'";
			List<Shortcut> list = manager.queryByHql(hql);
			if (list != null && list.size() > 0) {
				JspWriter out = this.pageContext.getOut();
				StringBuilder sb = new StringBuilder();
				for (Shortcut shortcut : list) {
					sb.append("<li><a class=\"shortcut-button\" href=\"").append(shortcut.getUrl()).append("\"><span>");
					sb.append("<img width=\"48px\" height=\"48px\" src=\"resources/images/icons/shortcut.jpg\" alt=\"icon\" /><br />");
					sb.append(shortcut.getName());
					sb.append("</span></a></li>");
				}
				out.print(sb.toString());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
}
