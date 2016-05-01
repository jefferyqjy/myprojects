package com.cz.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.BooksDAO;
import com.cz.entity.Books;

public class BooksServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("updatebooks", operate)) {
			doUpdate(request, response);
			return;
		} else if(StringUtils.equals("addbooks", operate)) {
			doAdd(request, response);
			return;
		}
	}

	/**
	 * update books
	 * @param request
	 * @param response
	 */
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		try {
			BooksDAO booksdao = new BooksDAO();
			Books data = booksdao.findById(Integer.valueOf(id));
			data.setAuthor(request.getParameter("author"));
			data.setBookname(request.getParameter("bookname"));
			data.setCbrq(request.getParameter("cbrq"));
			data.setCbs(request.getParameter("cbs"));
			data.setIsbn(request.getParameter("isbn"));
			data.setJianj(request.getParameter("jianj"));
			data.setKucun(request.getParameter("kucun"));
			data.setPrice(request.getParameter("price"));
			data.setTslb(request.getParameter("tslb"));
			data.setFilename(request.getParameter("filename"));
			booksdao = new BooksDAO();
			booksdao.udpate(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addbooks.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add books
	 * @param request
	 * @param response
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		Books data = new Books();
		data.setAuthor(request.getParameter("author"));
		data.setBookname(request.getParameter("bookname"));
		data.setCbrq(request.getParameter("cbrq"));
		data.setCbs(request.getParameter("cbs"));
		data.setIsbn(request.getParameter("isbn"));
		data.setJianj(request.getParameter("jianj"));
		data.setKucun(request.getParameter("kucun"));
		data.setPrice(request.getParameter("price"));
		data.setTslb(request.getParameter("tslb"));
		data.setFilename(request.getParameter("filename"));
		
		BooksDAO booksdao = new BooksDAO();
		try {
			booksdao.insert(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addbooks.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
