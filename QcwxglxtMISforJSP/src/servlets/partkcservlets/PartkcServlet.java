package servlets.partkcservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ChangeToGBK;
import util.RowCount;
import bean.*;
import dao.*;

/**
 * Servlet implementation class PartkckcServlet
 */
@WebServlet(name = "Partkc", urlPatterns = { "/Partkc" }, description = "Partkc")
public class PartkcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartkcServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubString
		String operator = request.getParameter("operator");
		List<Partkc> partkcs = new ArrayList<Partkc>();
		PartkcDAO dao = new PartkcDAO();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。

		if (operator != null) {
			if (operator.equals("1") || operator.equals("0")) // 查询全部或查询部分b
			{
				String queryStr = "";
				String countStr = "";
				String queryName = "";
				String queryValue = "";
				int totalRows = 0;
				int totalPage = 0;
				int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				int pagerMethod = Integer.parseInt(request.getParameter("pagerMethod"));
				try {
					if (operator.equals("1")) // 查询全部
					{

						queryStr = "select partkcID,partID,partname,partstandard,partpackaging,partkcnum from partkc";
						countStr = "select count(partkcID) from partkc";
					} else // 查询部分
					{
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");

						if (!queryName.equals("partID")) // 纠正中文乱码
						{
							ChangeToGBK chGBK = new ChangeToGBK();
							queryValue = chGBK.change("queryValue", request);
						}


							queryStr = "select partkcID,partID,partname,partstandard,partpackaging,partkcnum from partkc where "+ queryName
									+ "='" + queryValue + "'";

							countStr = "select count(partkcID) from partkc where "
									+ queryName + "='" + queryValue + "'";
						
					}

					totalRows = (new RowCount()).getTotalrow(countStr); // 获得查询总纪录数；
					totalPage = (int) Math.ceil(1.0 * totalRows / pageCount);

					if (pagerMethod == 1) {
						currentPage = 1;
					} else if (pagerMethod == 2) {
						currentPage = currentPage - 1;
					} else if (pagerMethod == 3) {
						currentPage = currentPage + 1;
					} else if (pagerMethod == 4) {
						currentPage = totalPage;
					}

					if (currentPage <= 0) {
						currentPage = 1;
					}

					if (currentPage > totalPage) {
						currentPage = totalPage;
					}
					partkcs = dao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；

					if(partkcs.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='partkc.jsp';</script>");
						return;
					}

					request.setAttribute("partkcs", partkcs);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找信息失败！');window.location='partkc.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/partkc.jsp").forward(request, response);
				return;
			}
		}
	}
}
