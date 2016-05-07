package com.cz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.SysuserDAO;
import com.cz.entity.Sysuser;

public class SysuserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("updatesysusers", operate)) {
			doUpdate(request, response);
			return;
		} else if(StringUtils.equals("addsysusers", operate)) {
			doAdd(request, response);
			return;
		} else if(StringUtils.equals("updatesysuser", operate)) {
			doUpdateOne(request, response);
			return;
		} else if(StringUtils.equals("uppass", operate)) {
			doUpdatePassword(request, response);
			return;
		}
	}
	
	/**
	 * update password
	 * @param request
	 * @param response
	 */
	private void doUpdatePassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			Sysuser user = (Sysuser) request.getSession().getAttribute("admin");
			String newpwd = request.getParameter("newpwd");
			int id = user.getId();
			SysuserDAO dao = new SysuserDAO();
			Sysuser u = dao.findById(id);
			u.setUpass(newpwd);
			dao = new SysuserDAO();
			dao.update(u);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/updatepwd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * update one certain user
	 * @param request
	 * @param response
	 */
	private void doUpdateOne(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			SysuserDAO dao = new SysuserDAO();
			Sysuser u = dao.findById(Integer.valueOf(id));
			u.setEmail(request.getParameter("email"));
			u.setTel(request.getParameter("tel"));
			u.setTname(request.getParameter("tname"));
			u.setUpass(request.getParameter("upass"));
			dao = new SysuserDAO();
			dao.update(u);
			request.setAttribute("id", id);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/pupdatesysusers.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add sysusers
	 * @param request
	 * @param response
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			Sysuser u = new Sysuser();
			String uname = request.getParameter("uname");
			SysuserDAO dao = new SysuserDAO();
			String sql = "select * from sysuser where uname = '" + uname + "'";
			List<Sysuser> list = dao.findBySql(sql);
			if(list != null && list.size() > 0) {
				request.setAttribute("duplicate", "");
				getServletConfig().getServletContext().getRequestDispatcher("/admin/addsysusers.jsp").forward(request, response);
				return;
			}
			u.setUname(uname);
			u.setEmail(request.getParameter("email"));
			u.setTel(request.getParameter("tel"));
			u.setTname(request.getParameter("tname"));
			u.setUpass(request.getParameter("upass"));
			dao = new SysuserDAO();
			dao.insert(u);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addsysusers.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update system user info.
	 * @param request
	 * @param response
	 */
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			SysuserDAO dao = new SysuserDAO();
			Sysuser u = dao.findById(Integer.valueOf(id));
			u.setEmail(request.getParameter("email"));
			u.setTel(request.getParameter("tel"));
			u.setTname(request.getParameter("tname"));
			u.setUpass(request.getParameter("upass"));
			dao = new SysuserDAO();
			dao.update(u);
			request.setAttribute("id", id);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/updatesysusers.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
