package servlets.employeeservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckEmployeeServlet
 */
@WebServlet(name = "CheckEmployee", urlPatterns = { "/CheckEmployee"})
public class CheckEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckEmployeeServlet() {
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
			String empName = request.getParameter("empName");
			String message="";
            String emppwd,empadd,worktime,empphone;
            RequestDispatcher dispatcher =	request.getRequestDispatcher("employeeEdit.jsp");
            
			if (empName == null) {
				// 重定向到productEdit.jsp页面
				message="员工姓名不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
			}else
			{
				if (empName.trim().length()==0)
				{
					message="员工姓名不能为空字符串！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}
			try{
				emppwd = request.getParameter("emppwd");
				if(emppwd==""){
					message="员工密码不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="员工密码不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			try{
				empadd = request.getParameter("empadd");
				if(empadd==""){
					message="员工地址不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="员工地址不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			
			try{
				worktime = request.getParameter("worktime");
				if(worktime==""){
					message="员工工作时间不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="员工工作时间不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				empphone =request.getParameter("empphone");
				if(empphone==""){
					message="电话号码不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="电话号码不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Employee"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Employee"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
