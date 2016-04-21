package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AllBean;
import bean.InsertUpdateDelBean;

public class PrescribeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PrescribeServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=gb2312");
		request.setCharacterEncoding("gb2312");

		int responseText = 0;
		InsertUpdateDelBean ib = new InsertUpdateDelBean();
		String patient = request.getParameter("patient");
		String drug = request.getParameter("drug");
		String sums = request.getParameter("sums");
		String sql = "update drug set stock=stock-"+sums+" where id="+drug;
		responseText = ib.insertANDupdateANDdel(sql);
		if(responseText != -1){
			AllBean ab = new AllBean();
			ArrayList drugAll = ab.getDrug(drug);
			sql = "insert into prescribe(patient,drug,name,sums,price,total) values('"+patient+"','"+drug+"','"+drugAll.get(1)+"','"+sums+"','"+drugAll.get(8)+"','"+Integer.parseInt(sums)*Integer.parseInt(drugAll.get(8).toString())+"')";
			responseText = ib.insertANDupdateANDdel(sql);
		}

		PrintWriter out = response.getWriter();
		out.print(responseText);
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
