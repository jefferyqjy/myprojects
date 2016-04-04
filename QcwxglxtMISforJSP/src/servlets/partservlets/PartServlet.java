package servlets.partservlets;

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
import bean.*;
import dao.*;

/**
 * Servlet implementation class PartServlet
 */
@WebServlet(name = "Part",urlPatterns = { "/Part" }, description = "Part")
public class PartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartServlet() {
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
		// TODO Auto-generated method stubString operator=request.getParameter("operator");
		String operator=request.getParameter("operator");
		List<Part> parts=new ArrayList<Part>();
		PartDAO dao=new PartDAO();
		Part part=new Part();
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
						queryStr="select partID,partname,partstandard,partpackaging,part.kindID,kindName from part,category where part.kindID=category.kindID";    
						countStr="select count(partID) from part"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("partID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select partID,partname,partstandard,partpackaging,part.kindID,kindName from part,category where part.kindID=category.kindID and  "+queryName+"='"+queryValue+"'";
						countStr="select count(partID) from part,category where part.kindID=category.kindID and "+queryName+"='"+queryValue+"'";
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
					parts=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   

					if(parts.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='part.jsp';</script>");
						return;
					}
					
					
					request.setAttribute("parts", parts);
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
					out.print("<script language='javascript'>alert('查找信息失败！');window.location='part.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/part.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by part id.
			{
				List<Category> categorys=new ArrayList<Category>();
				CategoryDAO cdao=new CategoryDAO();
				try
				{
					categorys=cdao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找零件信息失败！');window.location='part.jsp';</script>");
					
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String partID=request.getParameter("partID");
					try
					{
						part=dao.findById(partID);
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找零件信息失败！');window.location='part.jsp';</script>");
						
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到零件后，把该零件的零件编码放入session，修改该零件信息再保存时可以从session中取出零件编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("partID",part.getPartID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("categorys", categorys);//修改或添加零件时，若输入信息通不过检测，需要返回维护零件页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("part", part);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/partEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从partEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Part part1=new Part();
				if (operatorStr.equals("modify"))
				part1.setPartID((String)(request.getSession().getAttribute("partID")));
				/*这个是先创建一个session对象.. 你才能把你的对象放入session中.. 
				要是 没有getSession() 则request.SetAttribute("a",a);的意思 是把a这个对象放入request范围内..不是在session范围内..*/
				part1.setPartname(request.getParameter("partname"));
				part1.setPartstandard(request.getParameter("partstandard"));
				part1.setPartpackaging(request.getParameter("partpackaging"));
				part1.setKindID(Integer.parseInt(request.getParameter("kindID")));
				
				PartDAO pdao=new PartDAO();
				try
				{
					
					if (operatorStr.equals("add"))
					{	
						pdao.insert(part1);
						out.print("<script language='javascript'>alert('成功添加零件！');window.location='part.jsp';</script>");
						
					}
					else
					{
						pdao.update(part1);
						out.print("<script language='javascript'>alert('成功修改零件信息！');window.location='part.jsp';</script>");
						
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加零件失败！');window.location='part.jsp';</script>");
					
					else
						out.print("<script language='javascript'>alert('修改零件失败！');window.location='part.jsp';</script>");
				
				}
				
			}else if (operatorStr.equals("delete"))
			{
				PartDAO pdao=new PartDAO();
				String partID=request.getParameter("partID");
	
			    	try
			    	{
			    		pdao.deleteById(partID);
			    		out.print("<script language='javascript'>alert('成功删除零件！');window.location='part.jsp';</script>");
						
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除零件失败！');window.location='part.jsp';</script>");
					}
			    
				}

			}

		
	}

}

