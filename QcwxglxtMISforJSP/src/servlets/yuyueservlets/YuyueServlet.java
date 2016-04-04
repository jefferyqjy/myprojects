package servlets.yuyueservlets;

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
import dao.YuyueDAO;
import bean.*;

/**
 * Servlet implementation class YuyueServlet
 */
@WebServlet(name = "Yuyue", description = "Yuyue", urlPatterns = { "/Yuyue" })
public class YuyueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	 YuyueDAO Yuyue=new YuyueDAO();
    public YuyueServlet() {
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
		List<Yuyue> yuyues=new ArrayList<Yuyue>();
		YuyueDAO dao=new YuyueDAO();
		Yuyue yuyue=new Yuyue();
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
						queryStr="select yuyue.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyuetime,!isnull(arrange.aID) as modifyflag from yuyue left join arrange on yuyue.yID=arrange.yID join customer on customer.cusID=yuyue.cusID order by yID";    
						countStr="select count(yID) from  yuyue where yuyue.yID"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("yID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select yuyue.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyuetime,!isnull(arrange.aID) as modifyflag from yuyue left join arrange on yuyue.yID=arrange.yID join customer on customer.cusID=yuyue.cusID and "+queryName+"='"+queryValue+"' order by yID";
						countStr="select count(yID) from  yuyue where yuyue.yID and "+queryName+"='"+queryValue+"'";
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
					yuyues=dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   

					if(yuyues.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='yuyue.jsp';</script>");
						return;
					}
					
					request.setAttribute("yuyues", yuyues);
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
					out.print("<script language='javascript'>alert('查找预约单信息失败！');window.location='yuyue.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/yuyue.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4"))  //find by employee id.
			{
				List<Yuyue> yuyue1=new ArrayList<Yuyue>();
				YuyueDAO ydao=new YuyueDAO();
				List<Customer> customers =new ArrayList<Customer>();
				CustomerDAO cdao =new CustomerDAO();
				try
				{
					yuyue1=ydao.findAll();
					customers=cdao.findAll();
					
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找预约单信息失败！');window.location='yuyue.jsp';</script>");
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String yID=request.getParameter("yID");
					try
					{
						yuyue=dao.findById(Integer.parseInt(yID));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找预约单信息失败！');window.location='yuyue.jsp';</script>");
						
					}
				}
			
				HttpSession  session=request.getSession();  
			
				if (operator.equals("3"))   //找到预约单后，把该预约单的预约单编码放入session，修改该预约单信息再保存时可以从session中取出预约单编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("yID",yuyue.getyID());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			

				session.setAttribute("yuyues",yuyue1);//修改或添加预约单时，若输入信息通不过检测，需要返回维护预约单页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("customers", customers);
				session.setAttribute("yuyue", yuyue);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/yuyueEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Yuyue yuyue1=new Yuyue();
				if (operatorStr.equals("modify"))
					yuyue1.setyID((int)(request.getSession().getAttribute("yID")));
					yuyue1.setCusID(Integer.parseInt(request.getParameter("cusID")));
					yuyue1.setCusName(request.getParameter("cusName"));
					yuyue1.setCusphone(request.getParameter("cusphone"));
					yuyue1.setCarnum(request.getParameter("carnum"));
					yuyue1.setCarmoder(request.getParameter("carmoder"));
					yuyue1.setRepairitem(request.getParameter("repairitem"));
					yuyue1.setYuyuetime(request.getParameter("yuyuetime"));
				    YuyueDAO ydao=new YuyueDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						ydao.insert(yuyue1);
						out.print("<script language='javascript'>alert('成功添加预约单信息！');window.location='yuyue.jsp';</script>");
						
					}
					else
					{
						ydao.update(yuyue1);
						out.print("<script language='javascript'>alert('成功修改预约单信息！');window.location='yuyue.jsp';</script>");
						
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
					out.print("<script language='javascript'>alert('添加预约单信息！');window.location='yuyue.jsp';</script>");
					
					else
					out.print("<script language='javascript'>alert('修改预约单信息！');window.location='yuyue.jsp';</script>");
					
				}
				
			}else if (operatorStr.equals("delete"))
			{
				YuyueDAO ydao=new YuyueDAO();
				int yID=Integer.parseInt(request.getParameter("yID"));
	
			    	try
			    	{
			    		ydao.deleteById(yID);
			    		out.print("<script language='javascript'>alert('成功删除预约单信息!');window.location='yuyue.jsp';</script>");
						
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除预约单失败！');window.location='yuyue.jsp';</script>");
						
			    	}
			   
			}
		}

	}
}
