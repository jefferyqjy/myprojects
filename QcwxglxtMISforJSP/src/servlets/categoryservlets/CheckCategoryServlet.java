package servlets.categoryservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCategoryServlet
 */
@WebServlet(name = "CheckCategory", urlPatterns = { "/CheckCategory"})
public class CheckCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCategoryServlet() {
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
			String kindName = request.getParameter("kindName");
			String message="";

			RequestDispatcher dispatcher =	request.getRequestDispatcher("categoryEdit.jsp");
            
			if (kindName == null) {
				// 重定向到productEdit.jsp页面
				message="零件类别名称不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (kindName.trim().length()==0)
				{
					message="零件类别名称不能为空字符串！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Category"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Category"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
