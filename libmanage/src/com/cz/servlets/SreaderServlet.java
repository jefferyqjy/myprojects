package com.cz.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.SreaderDAO;
import com.cz.entity.Sreader;

public class SreaderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("addsreader", operate)) {
			doAdd(request, response);
			return;
		} else if(StringUtils.equals("updatesreader", operate)) {
			doUpdate(request, response);
			return;
		} else if(StringUtils.equals("rupdatesreader", operate)) {
			doUpdateReader(request, response);
			return;
		} else if(StringUtils.equals("puppass", operate)) {
			doUpdatePassword(request, response);
		}
	}

	/**
	 * update reader password
	 * @param request
	 * @param response
	 */
	private void doUpdatePassword(HttpServletRequest request, HttpServletResponse response) {
		try {
			Sreader reader = (Sreader) request.getSession().getAttribute("reader");
			String newpwd = request.getParameter("newpwd");
			int id = reader.getId();
			SreaderDAO dao = new SreaderDAO();
			Sreader u = dao.findById(id);
			u.setUpass(newpwd);
			dao = new SreaderDAO();
			dao.update(u);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/pupdatepwd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * update reader
	 * @param request
	 * @param response
	 */
	private void doUpdateReader(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			SreaderDAO dao = new SreaderDAO();
			Sreader data = dao.findById(Integer.valueOf(id));
			data.setEmail(request.getParameter("email"));
			data.setTel(request.getParameter("tel"));
			data.setTname(request.getParameter("tname"));
			data.setUpass(request.getParameter("upass"));
			data.setXueli(request.getParameter("xueli"));
			data.setZiye(request.getParameter("ziye"));
			dao = new SreaderDAO();
			dao.update(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/rupdatesreader.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add reader
	 * @param request
	 * @param response
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			Sreader data = new Sreader();
			String uname = request.getParameter("uname"); 
			data.setUname(uname);
			SreaderDAO sreaderdao = new SreaderDAO();
			Sreader sreader = sreaderdao.findByUName(uname);
			if(sreader != null && sreader.getId() != null) {
				request.setAttribute("duplicate", "");
				getServletConfig().getServletContext().getRequestDispatcher("/admin/addsreader.jsp").forward(request, response);
				return;
			}
			
			data.setEmail(request.getParameter("email"));
			data.setKjnum(request.getParameter("kjnum"));
			data.setTel(request.getParameter("tel"));
			data.setTname(request.getParameter("tname"));
			data.setUpass(request.getParameter("upass"));
			data.setXueli(request.getParameter("xueli"));
			data.setZiye(request.getParameter("ziye"));
			sreaderdao = new SreaderDAO();
		
			sreaderdao.insert(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addsreader.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * update reader
	 * @param request
	 * @param response
	 */
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			SreaderDAO sreaderdao = new SreaderDAO();
			Sreader data = sreaderdao.findById(Integer.valueOf(id));
			data.setEmail(request.getParameter("email"));
			data.setKjnum(request.getParameter("kjnum"));
			data.setTel(request.getParameter("tel"));
			data.setTname(request.getParameter("tname"));
			data.setUname(request.getParameter("uname"));
			data.setUpass(request.getParameter("upass"));
			data.setXueli(request.getParameter("xueli"));
			data.setZiye(request.getParameter("ziye"));
			sreaderdao = new SreaderDAO();
			sreaderdao.update(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addsreader.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
