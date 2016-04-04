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

import net.sf.json.JSONArray;


import bean.*;
import dao.*;

/**
 * Servlet implementation class PartkckcBillServlet
 */
@WebServlet(name = "PartkcBill", urlPatterns = { "/PartkcBill" }, description = "PartkcBill")
public class PartkcBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartkcBillServlet() {
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
		 	//Object partname = request.getParameter("partname");
		 	List<Partkc> partkcs = new ArrayList<Partkc>();
			PartkcDAO dao = new PartkcDAO();
			//int partkcID = Integer.parseInt(request.getParameter("partkcID"));
			try{
				partkcs = dao.findAll();
				JSONArray jsonStr = JSONArray.fromObject(partkcs);
				PrintWriter out = response.getWriter();
				out.print(jsonStr);
				out.flush();
				out.close();
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
		
	}
}
