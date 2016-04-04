package servlets.adminservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckAdminServlet
 */
@WebServlet(name = "CheckAdmin", urlPatterns = { "/CheckAdmin"})
public class CheckAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAdminServlet() {
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
		if (operatorStr.equals("add"))
		{	
			String admName = request.getParameter("admName");
			String message="";
            String admpwd;
            RequestDispatcher dispatcher =	request.getRequestDispatcher("adminEdit.jsp");
            
			if (admName == null) {
				// 重定向到adminEdit.jsp页面
				message="系统用户姓名不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (admName.trim().length()==0)
				{
					message="系统用户姓名不能为空字符串！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}
			try{
				admpwd = request.getParameter("admpwd");
				if(admpwd==""){
					message="密码不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到adminEdit.jsp页面
				message="密码不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Admin"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Admin"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
