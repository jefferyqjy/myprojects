package com.cz.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.common.Info;
import com.cz.utils.CommonUtils;

public class CommonServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("list", operate)) {
			return;
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("upload", operate)) {
			doUpdload(request, response);
			return;
		} 
	}
	
	private File fujian;
	private String fujianFileName;

	/**
	 * do upload
	 * @param request
	 * @param response
	 */
	public void doUpdload(HttpServletRequest request, HttpServletResponse response) {
		//fujianFileName = "a.jpg";
		fujianFileName = request.getParameter("fujianFileName");
		String newFujianName = Info.generalFileName(fujianFileName);
		String dstPath = request.getSession().getServletContext().getRealPath("upfile") + "\\" + newFujianName;
		File dstFile = new File(dstPath);
		String test = request.getParameter("fujian");
		CommonUtils.copy(this.getFujian(), dstFile);
		request.setAttribute("newFujianName", newFujianName);
		request.setAttribute("oldFujianName", fujianFileName);
		request.setAttribute("fujianPath", "/upload" + "/" + newFujianName);
		fujianFileName = newFujianName;
		try {
			getServletConfig().getServletContext().getRequestDispatcher("/admin/uploadimg.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public File getFujian() {
		return fujian;
	}

	public void setFujian(File fujian) {
		this.fujian = fujian;
	}

	public String getFujianFileName() {
		return fujianFileName;
	}

	public void setFujianFileName(String fujianFileName) {
		this.fujianFileName = fujianFileName;
	}
}
