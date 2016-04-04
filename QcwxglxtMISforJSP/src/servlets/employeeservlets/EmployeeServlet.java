package servlets.employeeservlets;

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
import bean.*;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet(name = "Employee", description = "Employee", urlPatterns = { "/Employee" })
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	 EmployeeDAO employee=new EmployeeDAO();
    public EmployeeServlet() {
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
		List<Employee> employees=new ArrayList<Employee>();
		EmployeeDAO dao=new EmployeeDAO();
		Employee employee=new Employee();
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
						queryStr="select empID,empname,emppwd,empadd,worktime,empphone from employee where employee.empID ";    
						countStr="select count(empID) from  employee where employee.empID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("empID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select empID,empname,emppwd,empadd,worktime,empphone from employee  where employee.empID and "+queryName+"='"+queryValue+"'";
						countStr="select count(empID) from  employee where employee.empID and "+queryName+"='"+queryValue+"'";
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
					employees=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   
    			
					if(employees.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='employee.jsp';</script>");
						return;
					}
					
					
					request.setAttribute("employees", employees);
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
					out.print("<script language='javascript'>alert('查找员工信息失败！');window.location='employee.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/employee.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by employee id.
			{
				List<Employee> employee1=new ArrayList<Employee>();
				EmployeeDAO edao=new EmployeeDAO();
				try
				{
					employee1=edao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找员工信息失败！');window.location='employee.jsp';</script>");
					
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String empID=request.getParameter("empID");
					try
					{
						employee=dao.findById(Integer.parseInt(empID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找员工信息失败！');window.location='employee.jsp';</script>");
						
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到员工后，把该员工的员工编码放入session，修改该员工信息再保存时可以从session中取出员工编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("empID",employee.getEmpID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("employees", employee1);//修改或添加员工时，若输入信息通不过检测，需要返回维护员工页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("employee", employee);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/employeeEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Employee employee1=new Employee();
				if (operatorStr.equals("modify"))
					employee1.setEmpID((int)(request.getSession().getAttribute("empID")));
					employee1.setEmpName(request.getParameter("empName"));
					employee1.setEmppwd(request.getParameter("emppwd"));
					employee1.setEmpadd(request.getParameter("empadd"));
					employee1.setWorktime(request.getParameter("worktime"));
					employee1.setEmpphone(request.getParameter("empphone"));

				EmployeeDAO edao=new EmployeeDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						edao.insert(employee1);
						out.print("<script language='javascript'>alert('成功添加员工信息！');window.location='employee.jsp';</script>");
						
					}
					else
					{
						edao.update(employee1);
						out.print("<script language='javascript'>alert('成功修改员工信息!');window.location='employee.jsp';</script>");
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加员工信息！');window.location='employee.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改员工信息！');window.location='employee.jsp';</script>");
				
				}
				
			}else if (operatorStr.equals("delete"))
			{
				EmployeeDAO edao=new EmployeeDAO();
				int empID=Integer.parseInt(request.getParameter("empID"));
	
			    	try
			    	{
			    		edao.deleteById(empID);
			    		out.print("<script language='javascript'>alert('成功删除员工信息！');window.location='employee.jsp';</script>");
					}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除员工信息失败！');window.location='employee.jsp';</script>");
					}
			   
			}
		}

	}
}
