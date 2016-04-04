package servlets.supplierservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckSupplierServlet
 */
@WebServlet(name = "CheckSupplier", urlPatterns = { "/CheckSupplier"})
public class CheckSupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckSupplierServlet() {
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
			String supName = request.getParameter("supName");
			String message="";
            String supadd,supphone;

			RequestDispatcher dispatcher =	request.getRequestDispatcher("supplierEdit.jsp");
            
			if (supName == null) {
				// 重定向到supplierEdit.jsp页面
				message="供应商名称不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}else
			{
				if (supName.trim().length()==0)
				{
					message="供应商名称不能为空字符串！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
				}
			}

			try{
				supadd = request.getParameter("supadd");
				if(supadd==""){
					message="供应商地址不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="供应商地址不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
			
			
			
			try{
				supphone =request.getParameter("supphone");
				if(supphone==""){
					message="供应商号码不能为空！";
					request.setAttribute("message", message);	
					dispatcher.forward(request, response);
					return;
					}			
			}catch (Exception e) {
				// 重定向到productEdit.jsp页面
				message="供应商号码不能为空！";
				request.setAttribute("message", message);	
				dispatcher.forward(request, response);
				return;
			}
		
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Supplier"); 
			dispatcher1.forward(request, response);
			return;
			
		}else{
			RequestDispatcher dispatcher1 =	request.getRequestDispatcher("Supplier"); 
			dispatcher1.forward(request, response);
			return;
		}
			
	}

}
