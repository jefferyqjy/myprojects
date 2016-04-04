package servlets.checkoutsservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCheckoutsServlet
 */
@WebServlet(name = "CheckCheckouts", urlPatterns = { "/CheckCheckouts"})
public class CheckCheckoutsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCheckoutsServlet() {
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
			String aID = request.getParameter("aID");
			
			String message="";

			RequestDispatcher dispatcher =	request.getRequestDispatcher("checkoutsEdit.jsp");
            
			if (aID == null) {
				// 重定向到productEdit.jsp页面
				message="维修单编号不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (aID.trim().length()==0)
				{
					message="维修单编号不能为空字符！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}
			try{
				String repaircost = request.getParameter("repaircost");
				if(repaircost==""){
					message="修理费用不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="修理费用不能为空字符串！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			try{
				String partkcprice = request.getParameter("partkcprice");
				if(partkcprice==""){
					message="零件费不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="零件费不能为空字符串！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			try{
				String checknum = request.getParameter("checknum");
				if(checknum==""){
					message="零件数量不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="零件数量不能为空字符串！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
		

			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Checkouts"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Checkouts"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
