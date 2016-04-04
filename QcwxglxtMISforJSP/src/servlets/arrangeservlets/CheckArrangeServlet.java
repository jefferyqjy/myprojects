package servlets.arrangeservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckArrangeServlet
 */
@WebServlet(name = "CheckArrange", urlPatterns = { "/CheckArrange"})
public class CheckArrangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckArrangeServlet() {
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
			String empID = request.getParameter("empID");
			String message="";
            RequestDispatcher dispatcher =	request.getRequestDispatcher("arrangeEdit.jsp");
            
			if (empID == null) {
				// 重定向到supplierEdit.jsp页面
				message="员工编号不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (empID.trim().length()==0)
				{
					message="员工编号不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}

			try{
				String aID = request.getParameter("aID");
				if(aID==""){
					message="预约单编号不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到yuyueEdit.jsp页面
				message="预约单编号不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			try{
				String anpaitime = request.getParameter("anpaitime");
				if(anpaitime==""){
					message="安排时间不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="安排时间不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			try{
				String wangongtime = request.getParameter("wangongtime");
				if(wangongtime==""){
					message="完工时间不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				
				message="完工时间不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				String partname = request.getParameter("partname");
				if(partname==""){
					message="所需零件名称不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				
				message="所需零件名称不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				String checknum = request.getParameter("checknum");
				if(checknum==""){
					message="所需零件数量不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				
				message="所需零件数量不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Arrange"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Arrange"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
