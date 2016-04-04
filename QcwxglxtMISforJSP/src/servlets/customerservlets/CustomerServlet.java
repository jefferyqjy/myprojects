package servlets.customerservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ChangeToGBK;
import util.RowCount;

import dao.CustomerDAO;
import bean.*;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet(name = "Customer", description = "Customer", urlPatterns = { "/Customer" })
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	 CustomerDAO Customer=new CustomerDAO();
    public CustomerServlet() {
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
		List<Customer> customers=new ArrayList<Customer>();
		CustomerDAO dao=new CustomerDAO();
		Customer customer=new Customer();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。

		
		if (operator!=null)
		{	
			if (operator.equals("1")||operator.equals("0"))  //查询全部或查询部分
			{
				String queryStr="";
				String countStr="";
				String queryName="";
				String queryValue="";
				int totalRows=0;
				int totalPage=0;
				int currentPage=Integer.parseInt(request.getParameter("currentPage"));
				int pagerMethod=Integer.parseInt(request.getParameter("pagerMethod"));
				try
				{
					if (operator.equals("1")) //查询全部
					{	
						queryStr="select cusID,cusName,cuspwd,cusphone,question,answer,cusadd,vip,zongxiaofei from customer where customer.cusID ";    
						countStr="select count(cusID) from  customer where customer.cusID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("cusID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select cusID,cusName,cuspwd,cusphone,question,answer,cusadd,vip,zongxiaofei from customer where customer.cusID and "+queryName+"='"+queryValue+"'";
						countStr="select count(cusID) from  customer where customer.cusID and "+queryName+"='"+queryValue+"'";
					}
    		
					totalRows=(new RowCount()).getTotalrow(countStr);  //获得查询总纪录数；
					totalPage = (int) Math.ceil(1.0 * totalRows / pageCount);
    	
					if (pagerMethod==1)
					{
						currentPage=1;
					}else if (pagerMethod==2)
					{
						currentPage=currentPage-1;
					}else if (pagerMethod==3)	
					{	
						currentPage=currentPage+1;
					}else if (pagerMethod==4)		
					{
						currentPage=totalPage;
					}

					if (currentPage <= 0) {
    					currentPage = 1;
					}

					if (currentPage > totalPage) {
						currentPage = totalPage;
					}
					customers=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   
					if(customers.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='customer.jsp';</script>");
						return;
					}
					
					
					request.setAttribute("customers", customers);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0"))
					{
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				}catch(Exception e2)
				{
					out.print("<script language='javascript'>alert('查找客户信息失败！');window.location='customer.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/customer.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by employee id.
			{
				List<Customer> customer1=new ArrayList<Customer>();
				CustomerDAO cdao=new CustomerDAO();
				try
				{
					customer1=cdao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找客户信息失败！');window.location='customer.jsp';</script>");
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String cusID=request.getParameter("cusID");
					try
					{
						customer=dao.findById(Integer.parseInt(cusID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找客户信息失败！');window.location='customer.jsp';</script>");
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到客户后，把该客户的客户编码放入session，修改该客户信息再保存时可以从session中取出客户编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("cusID",customer.getCusID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("customers",customer1);//修改或添加客户时，若输入信息通不过检测，需要返回维护客户页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("customer", customer);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/customerEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从customerEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
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
					if (operatorStr.equals("add"))
					{	
						cdao.insert(customer1);
						out.print("<script language='javascript'>alert('成功添加客户信息！');window.location='customer.jsp';</script>");
				}
					else
					{
						cdao.update(customer1);
						out.print("<script language='javascript'>alert('成功修改客户信息！');window.location='customer.jsp';</script>");
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加客户信息！');window.location='customer.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改客户信息！');window.location='customer.jsp';</script>");
				}
				
			}else if (operatorStr.equals("delete"))
			{
				CustomerDAO cdao=new CustomerDAO();
				int cusID=Integer.parseInt(request.getParameter("cusID"));
	
			    	try
			    	{
			    		cdao.deleteById(cusID);
			    		out.print("<script language='javascript'>alert('成功删除客户信息！');window.location='customer.jsp';</script>");
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除客户信息失败！');window.location='customer.jsp';</script>");
			    	}
			   
			}

	}

}
}
