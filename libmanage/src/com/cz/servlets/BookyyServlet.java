package com.cz.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.SysuserDAO;
import com.cz.entity.Sysuser;

public class BookyyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("updatesysuser", operate)) {
			doUpdate(request, response);
			return;
		}
	}

	/**
	 * update system user info.
	 * @param request
	 * @param response
	 */
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		SysuserDAO dao = new SysuserDAO();
		String id = request.getParameter("id");
		Sysuser u;
		boolean success = false;
		try {
			u = dao.findById(Integer.valueOf(id.trim()));
			u.setEmail(request.getParameter("email"));
			u.setTel(request.getParameter("tel"));
			u.setTname(request.getParameter("tname"));
			u.setUpass(request.getParameter("upass"));
			dao = new SysuserDAO();
			dao.update(u);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("id", id);
		request.setAttribute("suc", success);
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/admin/pupdatesysusers.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
