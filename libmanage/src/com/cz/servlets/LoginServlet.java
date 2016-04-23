package com.cz.servlets;

import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.SysuserDAO;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5050412314452189136L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		SysuserDAO dao = new SysuserDAO();
		String username = request.getParameter("uname");
		String password = request.getParameter("upass");
		String utype = request.getParameter("utype");
		
		String returnpage = "/index.jsp";
		if(StringUtils.equals("管理员", utype)) {
			try {
				boolean success = dao.CheckPassword(username, password);
				if(success) {
					returnpage = "";
				} else {
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/*CommonDAO dao = (CommonDAO) Info.getDao(request, "CommonDAO");
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
		}*/
	}
	
}
