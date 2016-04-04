package servlets.customerservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCustomerServlet
 */
@WebServlet(name = "CheckCustomer", urlPatterns = { "/CheckCustomer"})
public class CheckCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCustomerServlet() {
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
		String operatorStr=request.getParameter("operatorStr");
		if (operatorStr.equals("add") || operatorStr.equals("modify"))
		{	
			String cusName = request.getParameter("cusName");
			String message="";
            String cuspwd,cusphone,cusadd;
            RequestDispatcher dispatcher =	request.getRequestDispatcher("customerEdit.jsp");
            
			if (cusName == null) {
				// 重定向到supplierEdit.jsp页面
				message="客户姓名不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (cusName.trim().length()==0)
				{
					message="客户姓名不能为空字符串！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}

			try{
				cuspwd = request.getParameter("cuspwd");
				if(cuspwd==""){
					message="密码不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到customerEdit.jsp页面
				message="密码不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			try{
				cusphone =request.getParameter("cusphone");
				if(cusphone==""){
					message="客户电话不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="客户电话不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			try{
				cusadd =request.getParameter("cusadd");
				if(cusadd==""){
					message="地址不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				
				message="地址不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}

			try{
				String vip = request.getParameter("vip");
				if(vip==""){
					message="是否会员不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="是否会员不能为空字符串！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}try{
				String zongxiaofei = request.getParameter("zongxiaofei");
				if(zongxiaofei==""){
					message="总消费不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="总消费不能为空字符串！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Customer"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Customer"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
