package servlets.partrkservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;


import bean.*;
import dao.*;

/**
 * Servlet implementation class PartrkkcBillServlet
 */
@WebServlet(name = "PartrkBill", urlPatterns = { "/PartrkBill" }, description = "PartrkBill")
public class PartrkBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartrkBillServlet() {
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
		List<Partrkmingxi> partrkmingxis = new ArrayList<Partrkmingxi>();
		PartrkDAO dao = new PartrkDAO();
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setCharacterEncoding("UTF-8");//设置输出的编码格式
		try {
			String beginMonth = request.getParameter("beginMonth");
			String endMonth = request.getParameter("endMonth");
			partrkmingxis = dao.findByPartrkAndMonthRange(beginMonth, endMonth);//调用方法
			
			JSONArray jsonStr = JSONArray.fromObject(partrkmingxis);
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
