package servlets.partssservlets;

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
 * Servlet implementation class PartsskcBillServlet
 */
@WebServlet(name = "PartssBill", urlPatterns = { "/PartssBill" }, description = "PartssBill")
public class PartssBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartssBillServlet() {
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
		List<Partssmingxi> partssmingxis = new ArrayList<Partssmingxi>();
		PartssDAO dao = new PartssDAO();
		request.setCharacterEncoding("UTF-8");//设置请求的编码格式
		response.setCharacterEncoding("UTF-8");//设置输出的编码格式
		try {
			String beginMonth = request.getParameter("beginMonth");
			String endMonth = request.getParameter("endMonth");
			partssmingxis = dao.findByPartssAndMonthRange(beginMonth, endMonth);//调用方法
			
			JSONArray jsonStr = JSONArray.fromObject(partssmingxis);
			PrintWriter out = response.getWriter();
			out.print(jsonStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
