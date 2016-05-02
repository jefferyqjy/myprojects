package com.cz.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.common.Info;
import com.cz.dao.BookhjDAO;
import com.cz.dao.BooksDAO;
import com.cz.dao.SreaderDAO;
import com.cz.entity.Bookhj;
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
		} else if(StringUtils.equals("add", operate)) {
			doAdd(request, response);
			return;
		} else if(StringUtils.equals("hbook", operate)) {
			doHBook(request, response);
			return;
		} else if(StringUtils.equals("shbook", operate)) {
			doSHBook(request, response);
			return;
		} else if(StringUtils.equals("updatebookhj", operate)) {
			doUpdate(request, response);
			return;
		} else if(StringUtils.equals("rshbook", operate)) {
			doRenew(request, response);
			return;
		}
	}
	
	/**
	 * @param request
	 * @param response
	 */
	private void doRenew(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			BookhjDAO bookhjdao = new BookhjDAO();
			Bookhj data = bookhjdao.findById(Integer.valueOf(id));
			data.setSjtime(request.getParameter("htime"));
			data.setSjstatus("待审批");
			bookhjdao = new BookhjDAO();
			bookhjdao.update(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/allcontrol.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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
	
	/**
	 * add bookhj info
	 * @param request
	 * @param response
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		try {
			String readername = request.getParameter("readername");
			SreaderDAO sdao = new SreaderDAO();
			Sreader reader = sdao.findByUName(readername);
			int kjnum = Integer.parseInt(reader.getKjnum().trim());
			
			BookhjDAO bdao = new BookhjDAO();
			String sql = "select * from bookhj where 1=1 and readername = '" + readername + "'";
			sql += " and (hbtime is null or hbtime = '')";
			List<Bookhj> list = bdao.findBySql(sql);
			if(list != null && list.size() == kjnum) {
				request.setAttribute("kjnum", "");
				getServletConfig().getServletContext().getRequestDispatcher("/admin/addbookhj.jsp").forward(request, response);
				return;
			} else {
				sql = "select * from bookhj where 1=1 and htime <= '" + Info.getDateStr().substring(0, 10) + "'";
				sql += " and (hbtime is null or hbtime = '')";
				bdao = new BookhjDAO();
				list = bdao.findBySql(sql);
				if(list != null && list.size() > 0) {
					request.setAttribute("htime", "");
					getServletConfig().getServletContext().getRequestDispatcher("/admin/addbookhj.jsp").forward(request, response);
					return;
				}
			}
			
			String jtime = request.getParameter("jtime");
			String htime = request.getParameter("htime");
			String bookname = request.getParameter("bookname");
			
			String yjin = request.getParameter("yjin");
			String bei = request.getParameter("bei");
			Bookhj bookhj = new Bookhj();
			bookhj.setJtime(jtime);
			bookhj.setHtime(htime);
			bookhj.setReadername(readername);
			bookhj.setBookname(bookname);
			bookhj.setYjin(yjin);
			bookhj.setBei(bei);
			bdao = new BookhjDAO();
			bdao.insert(bookhj);
			
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addbookhj.jsp").forward(request, response);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return;
	}
	
	/**
	 * return books
	 * @param request
	 * @param response
	 */
	private void doHBook(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		BookhjDAO dao = new BookhjDAO();
		Bookhj bookhj;
		try {
			bookhj = dao.findById(Integer.valueOf(id.trim()));
			bookhj.setHbbei(request.getParameter("hbbei"));
			bookhj.setHbtime(request.getParameter("hbtime"));
			bookhj.setHbkou(request.getParameter("hbkou"));
			dao = new BookhjDAO();
			dao.update(bookhj);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/hbook.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * confrim renew books
	 * @param request
	 * @param response
	 */
	private void doSHBook(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		BookhjDAO dao = new BookhjDAO();
		Bookhj bookhj;
		try {
			bookhj = dao.findById(Integer.valueOf(id.trim()));
			bookhj.setSjtime(request.getParameter("sjtime"));
			bookhj.setSjstatus("已通过");
			dao = new BookhjDAO();
			dao.update(bookhj);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/shbook.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String jtime = request.getParameter("jtime");
		String htime = request.getParameter("htime");
		String yjin = request.getParameter("yjin");
		String bei = request.getParameter("bei");
		
		try {
			BookhjDAO bookhjdao = new BookhjDAO();
			Bookhj bookhj = bookhjdao.findById(Integer.valueOf(id));
			bookhj.setJtime(jtime);
			bookhj.setHtime(htime);
			bookhj.setYjin(yjin);
			bookhj.setBei(bei);
			
			bookhjdao = new BookhjDAO();
			bookhjdao.update(bookhj);
			
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/bookhj.jsp?1=1").forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
