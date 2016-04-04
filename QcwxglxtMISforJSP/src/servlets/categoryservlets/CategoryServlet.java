package servlets.categoryservlets;

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

import dao.CategoryDAO;
import bean.*;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet(name ="/Category" ,urlPatterns = { "/Category"})
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	CategoryDAO category=new CategoryDAO();
    public CategoryServlet() {
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
		List<Category> categorys=new ArrayList<Category>();
		CategoryDAO dao=new CategoryDAO();
		Category category=new Category();
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
						queryStr="select kindID,kindName,description from category where category.kindID";    
						countStr="select count(kindID) from  category where category.kindID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("kindID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select kindID,kindName,description from category  where category.kindID and "+queryName+"='"+queryValue+"'";
						countStr="select count(kindID) from  category where category.kindID and "+queryName+"='"+queryValue+"'";
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
					categorys=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   
    			
					if(categorys.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='category.jsp';</script>");
						return;
					}
					
					request.setAttribute("categorys", categorys);
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
					out.print("<script language='javascript'>alert('查找零件类别信息失败！');window.location='category.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/category.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4")) 
			{
				List<Category> categorys1=new ArrayList<Category>();
				CategoryDAO cdao=new CategoryDAO();
				try
				{
					categorys1=cdao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找零件类别信息失败！');window.location='category.jsp';</script>");
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String kindID=request.getParameter("kindID");
					try
					{
						category=dao.findById(Integer.parseInt(kindID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找零件类别信息失败！');window.location='category.jsp';</script>");
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到零件类别后，把该零件类别的零件类别编码放入session，修改该零件类别信息再保存时可以从session中取出零件类别编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("kindID",category.getKindID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("categorys", categorys1);//修改或添加零件类别时，若输入信息通不过检测，需要返回维护零件类别页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("category", category);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/categoryEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Category category1=new Category();
				if (operatorStr.equals("modify"))
					category1.setKindID((int)(request.getSession().getAttribute("kindID")));
					category1.setKindName(request.getParameter("kindName"));
					category1.setDescription(request.getParameter("description"));
				
				
				CategoryDAO cdao=new CategoryDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						cdao.insert(category1);
						out.print("<script language='javascript'>alert('成功添加零件类别信息！');window.location='category.jsp';</script>");
					}
					else
					{
						cdao.update(category1);
						out.print("<script language='javascript'>alert('成功修改零件类别信息！');window.location='category.jsp';</script>");
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加零件类别零件类别信息！');window.location='category.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改零件类别零件类别信息！');window.location='category.jsp';</script>");
				}
				
			}else if (operatorStr.equals("delete"))
			{
				CategoryDAO cdao=new CategoryDAO();
				int kindID=Integer.parseInt(request.getParameter("kindID"));
	
			    	try
			    	{
			    		cdao.deleteById(kindID);
			    		out.print("<script language='javascript'>alert('成功删除零件类别信息！');window.location='category.jsp';</script>");
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除零件类别信息失败！');window.location='category.jsp';</script>");
			    	}
			    
			}
		}

	}	
}
