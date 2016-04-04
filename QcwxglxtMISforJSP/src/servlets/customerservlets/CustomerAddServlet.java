package servlets.customerservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.Customer;

import dao.CustomerDAO;

/**
 * Servlet implementation class CustomerAddServlet
 */
@WebServlet(name = "CustomerAdd", description = "CustomerAdd", urlPatterns = { "/CustomerAdd" })
public class CustomerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		CustomerDAO cdao=new CustomerDAO();
		Customer customer=new Customer();
		customer.setCusName(request.getParameter("cusName"));
	    customer.setCuspwd(request.getParameter("cuspwd"));
	    customer.setCusphone(request.getParameter("cusphone"));
	    customer.setQuestion(request.getParameter("question"));
	    customer.setAnswer(request.getParameter("answer"));
	    customer.setCusadd(request.getParameter("cusadd"));
		try{
			
			cdao.insert(customer);
			out.print("<script language='javascript'>alert('注册成功:请牢记您的账号"+customer.getCusID()+"，请使用账号登陆');window.location='index.jsp';</script>");
	
			
		}catch(Exception e)	
		{
			e.printStackTrace();
		}
		
	}

}
