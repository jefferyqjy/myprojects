package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SelectBean;

public class AdminLoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AdminLoginServlet() {
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
		
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String status = request.getParameter("status");
		String table = "";
		String str = "name";
		if(status.equals("0")){
			table = "admin";
		}else if(status.equals("1")){
			table = "users";
		}else if(status.equals("2")){
			table = "patient";
			str = "id";
		}
		String sql = "select * from "+table+" where "+str+"='"+name+"' and pwd='"+pwd+"'";
		String args[] = {"id","name","pwd","status"};
		String args1[] = {"id","pwd","status"};
		SelectBean sb = new SelectBean();		
		ArrayList al = null;
		if(status.equals("2")){
			al = sb.selectRow(args1, sql);
			al.add(2, "111111");
		}else{
			al = sb.selectRow(args, sql);
		}
        String responseText = "";
		
		if(al == null || al.size() == 0){
			responseText = "1";
//			request.setAttribute("message", responseText);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("adminlogin", al);
			responseText = "2";
//			request.setAttribute("message", responseText);
		}
//		response.sendRedirect("/twtweb/users/index.jsp?message="+responseText);
//		request.getRequestDispatcher("/users/index.jsp").forward(request, response);
		PrintWriter out = response.getWriter();
		out.print(responseText);
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
