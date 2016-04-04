package servlets.partservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckPartServlets
 */
@WebServlet(name = "CheckPart", description = "CheckPart", urlPatterns = { "/CheckPart" })
public class CheckPartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckPartServlet() {
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
		String operatorStr=request.getParameter("operatorStr");
		if (operatorStr.equals("add") || operatorStr.equals("modify"))
		{	
			String partname = request.getParameter("partname");
			String message="";

			RequestDispatcher dispatcher =	request.getRequestDispatcher("partEdit.jsp");
            
			if (partname == null) {
				// 重定向到partEdit.jsp页面
				message="零件名称不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (partname.trim().length()==0)
				{
					message="零件名称不能为空字符串！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}
		
			try{
				String partstandard = request.getParameter("partstandard");
				if(partstandard==""){
					message="零件规格不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到partEdit.jsp页面
				message="零件规格不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			try{
				String partpackaging = request.getParameter("partpackaging");
				if(partpackaging==""){
					message="零件包装不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到partEdit.jsp页面
				message="零件包装不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Part"); 
			dispatcher1.forward(request, response);
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Part"); 
			dispatcher1.forward(request, response);
		}
	}
}
