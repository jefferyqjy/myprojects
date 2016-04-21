package com.cz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.cz.common.CommonDAO;
import com.cz.common.Info;
import com.cz.entity.Sreader;
import com.cz.entity.Sysuser;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5050412314452189136L;
	
	public String service() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
		String username = request.getParameter("uname");
		String password = request.getParameter("upass");
		String utype = request.getParameter("utype");
		if (utype.equals("管理员")) {
			String sql = " from Sysuser where uname='" + username
					+ "' and upass='" + password + "'";
			List<Sysuser> userlist = dao.findByHql(sql);
			if (userlist.size() != 1) {
				request.setAttribute("error", "");
				return "login";
			} else {
				request.getSession().setAttribute("admin", userlist.get(0));
				return "index";
			}
		} else {
			String sql = " from Sreader where uname='" + username
					+ "' and upass='" + password + "'";
			List<Sreader> userlist = dao.findByHql(sql);
			if (userlist.size() != 1) {
				request.setAttribute("error", "");
				return "login";
			} else {
				request.getSession().setAttribute("reader", userlist.get(0));
				return "index";
			}
		}
	}

}
