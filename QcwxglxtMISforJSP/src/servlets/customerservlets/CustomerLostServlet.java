package servlets.customerservlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Customer;

import dao.*;

/**
 * Servlet implementation class PasswordupdateServlet
 */
@WebServlet(name = "CustomerLost", description = "CustomerLost", urlPatterns = { "/CustomerLost" })
public class CustomerLostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerLostServlet() {
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
		// TODO Auto-generated method stub
		String cusName = request.getParameter("cusName");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String Password = request.getParameter("userPassword1");
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		CustomerDAO cdao=new CustomerDAO();
		Customer customer=new Customer();

		try {
			try{
				customer=cdao.findByCusName(cusName);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			if (customer.getQuestion().equals(question)) 
			{
				if(customer.getAnswer().equals(answer))
				{
					cdao.Update(cusName, Password);
					out.print("<script language='javascript'>alert('修改成功:请牢记您的账号"+customer.getCusID()+"，请使用账号登陆');window.location='login_cus.jsp';</script>");
					return;
				}
				else{
					out.print("<script language='javascript'>alert('答案不正确！');window.location='lost.jsp';</script>");
					
				}
				
			} else {
				out.print("<script language='javascript'>alert('问题不正确！');window.location='lost.jsp';</script>");
			}
		} catch (Exception e) {
			out.print("<script language='javascript'>alert('修改不成功！');window.location='lost.jsp';</script>");
			e.printStackTrace();
		}
	}

}
