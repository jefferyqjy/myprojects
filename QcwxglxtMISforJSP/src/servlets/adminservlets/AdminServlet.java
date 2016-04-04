package servlets.adminservlets;

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

import dao.*;
import bean.*;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet(name = "Admin", description = "Admin", urlPatterns = { "/Admin" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	 AdminDAO Admin=new AdminDAO();
	public AdminServlet() {
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
		String operator=request.getParameter("operator");//定义operator,通过get方法从Parameter中取得operator的值
		List<Admin> admins=new ArrayList<Admin>();//创建一个 存放Admin类型的 泛型
		AdminDAO dao=new AdminDAO();////实例化一个父类的对象AdminDAO
		Admin admin=new Admin();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		
		if (operator!=null)//
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
						queryStr="select * from admin";    
						countStr="select count(admID) from  admin where admin.admID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("empID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select * from admin  where admin.admID and "+queryName+"='"+queryValue+"'";
						countStr="select count(admID) from  admin where admin.admID and "+queryName+"='"+queryValue+"'";
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
					admins=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   

					if(admins.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='admin.jsp';</script>");
						return;
					}
					
					request.setAttribute("admins", admins);//admins这个泛型,存放到request里面,这样 在jsp的页面 就能把admins从request里取出来
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
					out.print("<script language='javascript'>alert('查找系统用户信息失败！');window.location='admin.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);	
				// forward方法  就是跳转到页面时  能把 request, response  一并带过去
				// ServletContext.getRequestDispather    绝对路径
				//request.getRequestDispatcher   相对路径或者绝对路径
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by admin id.
			{
				List<Admin> admin1=new ArrayList<Admin>();//创建一个 存放Admin类型的 泛型
				AdminDAO adao=new AdminDAO();//实例化父类adminDAO
				try
				{
					admin1=adao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找系统用户信息失败！');window.location='admin.jsp';</script>");
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String admID=request.getParameter("admID");
					try
					{
						admin=dao.findById(Integer.parseInt(admID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找系统用户信息失败！');window.location='admin.jsp';</script>");
					}
				}
			
				HttpSession  session=request.getSession(); //获取当前该用户的session信息。
			
				if (operator.equals("3"))   //找到管理员后，把该管理员的管理员编码放入session，修改该管理员信息再保存时可以从session中取出管理员编码。
				{	
					session.setAttribute("operatorStr", "modify");//存放的是一个modify字样的字符串
					session.setAttribute("admID",admin.getAdmID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("admins", admin1);//修改或添加管理员时，若输入信息通不过检测，需要返回维护管理员页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("admin", admin);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/adminEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从adminEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Admin admin1=new Admin();
				if (operatorStr.equals("modify"))
					admin1.setAdmID((int)(request.getSession().getAttribute("admID")));
					admin1.setAdmName(request.getParameter("admName"));
					admin1.setAdmpwd(request.getParameter("admpwd"));
					admin1.setSex(request.getParameter("sex"));
					admin1.setAdmphone(request.getParameter("admphone"));
					admin1.setAdmadd(request.getParameter("admadd"));
					AdminDAO adao=new AdminDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						adao.insert(admin1);
						out.print("<script language='javascript'>alert('成功添加系统用户信息！');window.location='admin.jsp';</script>");
					}
					else
					{
						adao.update(admin1);
						out.print("<script language='javascript'>alert('成功修改系统用户信息！');window.location='admin.jsp';</script>");
						
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加系统用户信息！');window.location='admin.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改系统用户信息！');window.location='admin.jsp';</script>");
				}
				
			}else if (operatorStr.equals("delete"))
			{
				AdminDAO adao=new AdminDAO();
				int admID=Integer.parseInt(request.getParameter("admID"));
	
			    	try
			    	{
			    		adao.deleteById(admID);
			    		out.print("<script language='javascript'>alert('成功删除系统用户信息！');window.location='admin.jsp';</script>");
				}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除系统用户信息失败！');window.location='admin.jsp';</script>");
				}
			   
			}
	}

}
}
