package servlets.yuyueservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckYuyueServlet
 */
@WebServlet(name = "CheckYuyue", urlPatterns = { "/CheckYuyue"})
public class CheckYuyueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckYuyueServlet() {
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
			String cusID = request.getParameter("cusID");
			String message="";
            String cusphone;
            RequestDispatcher dispatcher =	request.getRequestDispatcher("yuyueEdit.jsp");
            
			if (cusID == null) {
				// 重定向到supplierEdit.jsp页面
				message="客户编号不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (cusID.trim().length()==0)
				{
					message="客户编号不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}

			try{
				String carnum = request.getParameter("carnum");
				if(carnum==""){
					message="车牌号不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到yuyueEdit.jsp页面
				message="车牌号不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			try{
				String carmoder = request.getParameter("carmoder");
				if(carmoder==""){
					message="车辆类型不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="车辆类型不能为空！";
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
				
				message="客户电话不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				String repairitem = request.getParameter("repairitem");
				if(repairitem==""){
					message="修理项目不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				
				message="修理项目不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			try{
				String yuyuetime = request.getParameter("yuyuetime");
				if(yuyuetime==""){
					message="预约时间不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				
				message="预约时间不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Yuyue"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Yuyue"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
