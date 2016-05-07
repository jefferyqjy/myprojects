package com.cz.servlets;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cz.dao.SysprosDAO;
import com.cz.entity.Syspros;

public class SysprosServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6439543277481908201L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String operate = request.getParameter("operate");
		if(StringUtils.equals("updatepros", operate)) {
			doUpdate(request, response);
			return;
		} else if(StringUtils.equals("addpros", operate)) {
			doAdd(request, response);
			return;
		} else if(StringUtils.equals("loadinfo", operate)) {
			doLoadInfo(request, response);
			return;
		}
	}
	
	/**
	 * load info
	 * @param request
	 * @param response
	 */
	private void doLoadInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			String infob = "";
			String type = request.getParameter("type");
			String zhiye = request.getParameter("zhiye");
			zhiye = new String(zhiye.getBytes("ISO8859-1"), "UTF-8");
			SysprosDAO sysprosdao = new SysprosDAO();
			if(StringUtils.equals("infob", type)) {
				String sql = "select * from syspros where infoa = '职业' and proname = '" + zhiye + "'";
				List<Syspros> list = sysprosdao.findBySql(sql);
				if(list != null && list.size() > 0) {
					Syspros syspros = list.get(0);
					infob = syspros.getInfob();
				}
				PrintWriter out = response.getWriter();
				out.write(infob);
			} else if(StringUtils.equals("infoc", type)) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add syspros
	 * @param request
	 * @param response
	 */
	private void doAdd(HttpServletRequest request, HttpServletResponse response) {
		Syspros data = new Syspros();
		String type = request.getParameter("type");
		if (type.equals("1"))
			type = "出版社";
		if (type.equals("2"))
			type = "图书类别";
		if (type.equals("3"))
			type = "学历";
		if (type.equals("4")) {
			type = "职业";
			
			String infob = request.getParameter("infob"); // 该职业可借数量
			String infoc = request.getParameter("infoc"); // 该职业可借天数
			data.setInfob(infob);
			data.setInfoc(infoc);
		}
			
		data.setProname(request.getParameter("proname"));
		data.setInfoa(type);
		SysprosDAO sysprosdao = new SysprosDAO();
		try {
			sysprosdao.insert(data);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addpros.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update syspros
	 * @param request
	 * @param response
	 */
	private void doUpdate(HttpServletRequest request, HttpServletResponse response) {
		try {
			SysprosDAO dao = new SysprosDAO();
			String id = request.getParameter("id");
			String infob = request.getParameter("infob");
			String infoc = request.getParameter("infoc");
			Syspros u = dao.findById(Integer.valueOf(id.trim()));
			u.setProname(request.getParameter("proname"));
			u.setInfob(infob);
			u.setInfoc(infoc);
			dao = new SysprosDAO();
			dao.update(u);
			
			request.setAttribute("id", id);
			request.setAttribute("suc", "");
			getServletConfig().getServletContext().getRequestDispatcher("/admin/addpros.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
