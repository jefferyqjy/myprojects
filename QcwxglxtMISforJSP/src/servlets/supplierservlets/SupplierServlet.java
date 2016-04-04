package servlets.supplierservlets;

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

import dao.SupplierDAO;
import bean.*;

/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet(name = "Supplier", description = "Supplier", urlPatterns = { "/Supplier" })
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	 SupplierDAO supplier=new SupplierDAO();
    public SupplierServlet() {
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
		List<Supplier> suppliers=new ArrayList<Supplier>();
		SupplierDAO dao=new SupplierDAO();
		Supplier supplier=new Supplier();
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
						queryStr="select supID,supname,supadd,supphone from supplier where supplier.supID ";    
						countStr="select count(supID) from  supplier where supplier.supID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("supID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select supID,supname,supadd,supphone from supplier where supplier.supID and "+queryName+"='"+queryValue+"'";
						countStr="select count(supID) from  supplier where supplier.supID and "+queryName+"='"+queryValue+"'";
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
					suppliers=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   

					if(suppliers.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='supplier.jsp';</script>");
						return;
					}
					
					
					request.setAttribute("suppliers", suppliers);
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
					out.print("<script language='javascript'>alert('查找供应商信息失败！');window.location='supplier.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/supplier.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by employee id.
			{
				List<Supplier> supplier1=new ArrayList<Supplier>();
				SupplierDAO sdao=new SupplierDAO();
				try
				{
					supplier1=sdao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找供应商信息失败！');window.location='supplier.jsp';</script>");
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String supID=request.getParameter("supID");
					try
					{
						supplier=dao.findById(Integer.parseInt(supID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找供应商信息失败！');window.location='supplier.jsp';</script>");
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到供应商后，把该供应商的供应商编码放入session，修改该供应商信息再保存时可以从session中取出供应商编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("supID",supplier.getSupID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("suppliers", supplier1);//修改或添加供应商时，若输入信息通不过检测，需要返回维护供应商页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("supplier", supplier);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/supplierEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Supplier supplier1=new Supplier();
				if (operatorStr.equals("modify"))
					supplier1.setSupID((int)(request.getSession().getAttribute("supID")));
				    supplier1.setSupName(request.getParameter("supName"));
				    supplier1.setSupadd(request.getParameter("supadd"));;
				    supplier1.setSupphone(request.getParameter("supphone"));

				SupplierDAO sdao=new SupplierDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						sdao.insert(supplier1);
						out.print("<script language='javascript'>alert('成功添加供应商信息！');window.location='supplier.jsp';</script>");
					}
					else
					{
						sdao.update(supplier1);
						out.print("<script language='javascript'>alert('成功修改供应商信息！');window.location='supplier.jsp';</script>");
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						
					out.print("<script language='javascript'>alert('添加供应商信息！');window.location='supplier.jsp';</script>");
					
					else
						
					out.print("<script language='javascript'>alert('修改供应商信息！');window.location='supplier.jsp';</script>");
					
				}
				
			}else if (operatorStr.equals("delete"))
			{
				SupplierDAO sdao=new SupplierDAO();
				int supID=Integer.parseInt(request.getParameter("supID"));
	
			    	try
			    	{
			    		sdao.deleteById(supID);
			    		out.print("<script language='javascript'>alert('成功删除供应商信息！');window.location='supplier.jsp';</script>");
						
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除供应商信息失败！');window.location='supplier.jsp';</script>");
					}
			   
			}

		}

	}
}
