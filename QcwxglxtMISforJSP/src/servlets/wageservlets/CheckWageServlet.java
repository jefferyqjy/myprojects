package servlets.wageservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckWageServlet
 */
@WebServlet(name = "CheckWage", urlPatterns = { "/CheckWage"})
public class CheckWageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckWageServlet() {
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
			String EmpID = request.getParameter("empID");
			String month;
			float salary;
			float commission;
			float basepay;
			String message="";

			RequestDispatcher dispatcher =	request.getRequestDispatcher("wageEdit.jsp");
            
			if (EmpID == null) {
				// 重定向到productEdit.jsp页面
				message="员工编号不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (EmpID.trim().length()==0)
				{
					message="员工编号不能为空字符！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}
			try{
				month = request.getParameter("month");
				if(month==""){
					message="月份不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="月份不能为空字符串！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				basepay = Float.parseFloat(request.getParameter("basepay"));
				if(basepay<=0){
					message="没有基本工资不能是0或负数！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (NumberFormatException e) {
				// 重定向到productEdit.jsp页面
				message="没有基本工资是大于0的数而不能是其他字符！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				commission = Float.parseFloat(request.getParameter("commission"));
				if(commission<0){
					message="提成不能负数！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (NumberFormatException e) {
				// 重定向到productEdit.jsp页面
				message="提成只能是大于0或等于0的数而不能是其他字符！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				salary = Float.parseFloat(request.getParameter("salary"));
				if(salary<=0){
					message="员工工资不能是0或负数！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (NumberFormatException e) {
				// 重定向到productEdit.jsp页面
				message="员工工资只能是大于0的数而不能是其他字符！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Wage"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Wage"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
