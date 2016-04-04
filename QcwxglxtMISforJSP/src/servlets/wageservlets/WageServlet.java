package servlets.wageservlets;

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

import dao.EmployeeDAO;
import dao.WageDAO;
import bean.*;

/**
 * Servlet implementation class WageServlet
 */
@WebServlet("/WageServlet")
public class WageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	WageDAO wage=new WageDAO();
    public WageServlet() {
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
		List<Wage> wages=new ArrayList<Wage>();
		WageDAO dao=new WageDAO();
		Wage wage=new Wage();
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
						queryStr="select wID,wage.empID,empName,month,basepay,commission,salary from wage,employee where wage.empID=employee.empID";    
						countStr="select count(wID) from  wage where wage.wID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("wID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select wID,wage.empID,empName,month,basepay,commission,salary from wage,employee where wage.empID=employee.empID and "+queryName+"='"+queryValue+"'";
						countStr="select count(wID) from  wage where wage.wID and "+queryName+"='"+queryValue+"'";
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
					wages=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   
					if(wages.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='wage.jsp';</script>");
						return;
					}
					request.setAttribute("wages", wages);
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
					out.print("<script language='javascript'>alert('查找工资单信息失败！');window.location='wage.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/wage.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by product id.
			{
				List<Wage> wages1=new ArrayList<Wage>();
				WageDAO wdao=new WageDAO();
				List<Employee> employees=new ArrayList<Employee>();
				EmployeeDAO edao=new EmployeeDAO();
				try
				{
					wages1=wdao.findAll();
					employees=edao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找工资单信息失败！');window.location='wage.jsp';</script>");
					
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String wID=request.getParameter("wID");
					try
					{
						wage=dao.findById(Integer.parseInt(wID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找工资单信息失败！');window.location='wage.jsp';</script>");
						
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到商品后，把该商品的商品编码放入session，修改该商品信息再保存时可以从session中取出商品编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("wID",wage.getwID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("wages", wages1);//修改或添加商品时，若输入信息通不过检测，需要返回维护商品页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("employees", employees);
				session.setAttribute("wage", wage);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/wageEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Wage wage1=new Wage();
				if (operatorStr.equals("modify"))
					wage1.setwID((int)(request.getSession().getAttribute("wID")));
					//product1.setProID(Integer.parseInt(request.getParameter("proID")));
				wage1.setEmpID(Integer.parseInt(request.getParameter("empID")));	
				wage1.setMonth(request.getParameter("month"));
				wage1.setBasepay(Float.parseFloat(request.getParameter("basepay")));
				wage1.setCommission(Float.parseFloat(request.getParameter("commission")));
				wage1.setSalary(Float.parseFloat(request.getParameter("salary")));
				
				
				WageDAO wdao=new WageDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						wdao.insert(wage1);
						out.print("<script language='javascript'>alert('成功添加工资单信息！');window.location='wage.jsp';</script>");
						
					}
					else
					{
						wdao.update(wage1);
						out.print("<script language='javascript'>alert('成功修改工资单信息！');window.location='wage.jsp';</script>");
						
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加商品工资单信息！');window.location='wage.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改商品工资单信息！');window.location='wage.jsp';</script>");
					
				}
				
			}else if (operatorStr.equals("delete"))
			{
				WageDAO wdao=new WageDAO();
				int wID=Integer.parseInt(request.getParameter("wID"));
	
			    	try
			    	{
			    		wdao.deleteById(wID);
			    		out.print("<script language='javascript'>alert('成功删除工资单信息！');window.location='wage.jsp';</script>");
						
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除工资单信息失败！');window.location='wage.jsp';</script>");
					}
			    
			}
		
		}

	}
}
