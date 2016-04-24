package com.cz.servlets;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.BooksDAO;
import com.cz.dao.SreaderDAO;
import com.cz.entity.Books;
import com.cz.entity.Sreader;

public class BookhjServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("list", operate)) {
			doShowList(request, response);
			return;
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("list", operate)) {
			doShowList(request, response);
			return;
		}
	}

	/**
	 * update system user info.
	 * @param request
	 * @param response
	 */
	private void doShowList(HttpServletRequest request, HttpServletResponse response) {
		try {
			BooksDAO dao = new BooksDAO();
			List<Books> lblist = dao.findAll();
			SreaderDAO sdao = new SreaderDAO();
			List<Sreader> cbslist = sdao.findAll();
			request.setAttribute("lblist", lblist);
			request.setAttribute("cbslist", cbslist);
			getServletConfig().getServletContext().getRequestDispatcher("/admin/bookhj.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
