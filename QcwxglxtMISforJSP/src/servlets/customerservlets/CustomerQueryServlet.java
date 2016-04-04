package servlets.customerservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.CustomerDAO;
import bean.*;

/**
 * Servlet implementation class CustomerQueryServlet
 */
@WebServlet(name = "CustomerQuery", description = "CustomerQuery", urlPatterns = { "/CustomerQuery" })
public class CustomerQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	 CustomerDAO Customer=new CustomerDAO();
    public CustomerQueryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /* Ser 销毁的时候调用此方法 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		String operator=request.getParameter("operator");
		CustomerDAO dao=new CustomerDAO();
		Customer customer=new Customer();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。

		
		if (operator!=null)
		{	
			if (operator.equals("0"))  //查询全部或查询部分
			{
				int totalRows=0;
				int totalPage=0;
				try
				{
					if (operator.equals("0")) //查询部分
					{
						
					
					int cusID =Integer.parseInt(request.getParameter("cusID"));
					customer=dao.findById(cusID);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   
    			
					request.setAttribute("customers", customer);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("operator", operator);
					}

				}catch(Exception e2)
				{
					out.print("<script language='javascript'>alert('查找客户信息失败！');window.location='customer_cus.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/customer_cus.jsp").forward(request, response);	
				return;
			}else if (operator.equals("1"))  //find by customer id.
			{	
					String cusID=request.getParameter("cusID");
					try
					{
						customer=dao.findById(Integer.parseInt(cusID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找客户信息失败！');window.location='customer_cus.jsp';</script>");
					}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("1"))   //找到客户后，把该客户的客户编码放入session，修改该客户信息再保存时可以从session中取出客户编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("cusID",customer.getCusID());
				}	
			
				session.setAttribute("customer",customer);//修改或添加客户时，若输入信息通不过检测，需要返回维护客户页面，必须把类别信息放到session，否则会丢失
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/customerEdit_cus.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从customerEdit_cus.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("modify"))
			{
				Customer customer1=new Customer();
				if (operatorStr.equals("modify"))
					customer1.setCusID((int)(request.getSession().getAttribute("cusID")));
				    customer1.setCusName(request.getParameter("cusName"));
				    customer1.setCuspwd(request.getParameter("cuspwd"));
				    customer1.setCusphone(request.getParameter("cusphone"));
				    customer1.setQuestion(request.getParameter("question"));
				    customer1.setAnswer(request.getParameter("answer"));
				    customer1.setCusadd(request.getParameter("cusadd"));
				    CustomerDAO cdao=new CustomerDAO();
				try
				{
					if (operatorStr.equals("modify"))
					{	
						cdao.updatecus(customer1);
						out.print("<script language='javascript'>alert('成功修改客户信息！');window.location='customer_cus.jsp';</script>");
					}
				}catch(Exception e)	
				{
						out.print("<script language='javascript'>alert('修改客户信息！');window.location='customer_cus.jsp';</script>");
				}
				
			}

	}

}
}
