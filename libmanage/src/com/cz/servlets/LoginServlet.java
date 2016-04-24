package com.cz.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.SreaderDAO;
import com.cz.dao.SysuserDAO;
import com.cz.entity.Sreader;
import com.cz.entity.Sysuser;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5050412314452189136L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("uname");
		String password = request.getParameter("upass");
		String utype = request.getParameter("utype");
		
		String returnpage = "/login.jsp";
		request.setAttribute("error", "");
		if(StringUtils.equals("管理员", utype)) {
			try {
				SysuserDAO dao = new SysuserDAO();
				boolean success = dao.CheckPassword(username, password);
				if(success) {
					Sysuser sysuser = dao.findLoginUser(username, password);
					request.getSession().setAttribute("admin", sysuser);
					returnpage = "/admin/index.jsp";
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				SreaderDAO dao = new SreaderDAO();
				boolean success = dao.CheckPassword(username, password);
				if(success) {
					Sreader reader = dao.findLoginUser(username, password);
					request.getSession().setAttribute("reader", reader);
					returnpage = "/admin/index.jsp";
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			getServletConfig().getServletContext().getRequestDispatcher(returnpage).forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
