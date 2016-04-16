package servlets.stocktakeservlets;

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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import bean.Category;
import bean.Stocktake;
import dao.CategoryDAO;
import dao.StocktakeDAO;
import util.ChangeToGBK;
import util.RowCount;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet(name ="/Stocktake" ,urlPatterns = { "/Stocktake"})
public class StocktakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 int pageCount=7; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
	StocktakeDAO stocktakedao = new StocktakeDAO();
    public StocktakeServlet() {
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
		List<Stocktake> stocktakes = new ArrayList<Stocktake>();
		StocktakeDAO dao = new StocktakeDAO();
		Stocktake stocktake = new Stocktake();
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
						queryStr="select id, kindid, createtime, number, value from stocktake";    
						countStr="select count(1) from stocktake"; 
					}else  //查询部分
					{
						queryName=request.getParameter("queryName");
						queryValue=request.getParameter("queryValue");
						if (!queryName.equals("kindID"))  //纠正中文乱码
						{	
							ChangeToGBK chGBK=new ChangeToGBK();
							queryValue=chGBK.change("queryValue",request);
						}

						queryStr="select id, kindid, createtime, number, value from stocktake where 1=1 " ;
						countStr = "select count(1) from stocktake where 1=1 ";
						if(StringUtils.isNotEmpty(queryValue)) {
							queryStr += ("and " + queryName + " ='" + queryValue + "'");
							countStr += (" and " + queryName + " ='" + queryValue + "'");
						}
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
					stocktakes = dao.findByPage(queryStr, currentPage, pageCount);  //通过查询字符串、当前页和每页纪录数查找产品纪录；   
    			
					if(stocktakes.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='stocktake.jsp';</script>");
						return;
					}
					
					if(!CollectionUtils.isEmpty(stocktakes)) {
						CategoryDAO ctgdao;
						for(Stocktake c : stocktakes) {
							ctgdao = new CategoryDAO();
							Category category = ctgdao.findById(c.getKindID());
							c.setDescription(category.getDescription());
							c.setKindName(category.getKindName());
						}
					}
					request.setAttribute("stocktakes", stocktakes);
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
					out.print("<script language='javascript'>alert('查找零件盘点信息失败！');window.location='stocktake.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/stocktake.jsp").forward(request, response);	
				return;
			}else if (operator.equals("2") || operator.equals("3")||operator.equals("4")) 
			{
				List<Stocktake> stocktakes1 = new ArrayList<Stocktake>();
				StocktakeDAO cdao=new StocktakeDAO();
				try
				{
					stocktakes1 = cdao.findAll();
				}catch(Exception e)
				{
					out.print("<script language='javascript'>alert('查找零件盘点信息失败！');window.location='stocktake.jsp';</script>");
				}
			
				if (operator.equals("3")||operator.equals("4"))
				{	
					String id = request.getParameter("id");
					try
					{
						stocktake = dao.findById(Integer.valueOf(id.trim()));
   			
					}catch(Exception e2)
					{
						out.print("<script language='javascript'>alert('查找零件盘点信息失败！');window.location='stocktake.jsp';</script>");
					}
				}
			
				HttpSession session=request.getSession();  
			
				if (operator.equals("3"))   //找到零件类别后，把该零件类别的零件类别编码放入session，修改该零件类别信息再保存时可以从session中取出零件类别编码。
				{	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("id", stocktake.getId());
				}	
				else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else 
					session.setAttribute("operatorStr", "add");

			
				if(!CollectionUtils.isEmpty(stocktakes)) {
					CategoryDAO dao1;
					for(Stocktake c : stocktakes1) {
						dao1 = new CategoryDAO();
						Category category;
						try {
							category = dao1.findById(c.getKindID());
							c.setDescription(category.getDescription());
							c.setKindName(category.getKindName());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				session.setAttribute("stocktakes", stocktakes1);//修改或添加零件类别时，若输入信息通不过检测，需要返回维护零件类别页面，必须把类别信息放到session，否则会丢失
				
				CategoryDAO dao2 = new CategoryDAO();
				Category category;
				try {
					category = dao2.findById(stocktake.getKindID());
					stocktake.setDescription(category.getDescription());
					stocktake.setKindName(category.getKindName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.setAttribute("stocktake", stocktake);
				
				dao2 = new CategoryDAO();
				try {
					List<Category> categorys = dao2.findAll();
					request.setAttribute("categorys", categorys);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				request.setAttribute("operator", operator);
				
				getServletConfig().getServletContext().getRequestDispatcher("/stocktakeEdit.jsp").forward(request, response);
				return;
			}
		}
		 
		
		String operatorStr=request.getParameter("operatorStr");
		
		if (operatorStr!=null)  //operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		{	
			if (operatorStr.equals("add") ||operatorStr.equals("modify"))
			{
				Stocktake stocktake1 = new Stocktake();
				if (operatorStr.equals("modify")){
					stocktake1.setId((int)(request.getSession().getAttribute("id")));
				}
				String kindID = request.getParameter("kindID");
				stocktake1.setKindID(StringUtils.isEmpty(kindID) ? 0 : Integer.valueOf(kindID.trim()));
				String number = request.getParameter("number");
				stocktake1.setNumber(StringUtils.isEmpty(number) ? 0 : Integer.valueOf(number.trim()));
				String value = request.getParameter("value");
				stocktake1.setValue(StringUtils.isEmpty(value) ? 0 : Integer.valueOf(value.trim()));
				String dateStr = request.getParameter("createtime");
				stocktake1.setCreateTime(dateStr);
				StocktakeDAO cdao=new StocktakeDAO();
				try
				{
					if (operatorStr.equals("add"))
					{	
						cdao.insert(stocktake1);
						out.print("<script language='javascript'>alert('成功添加零件盘点信息！');window.location='stocktake.jsp';</script>");
					}
					else
					{
						cdao.update(stocktake1);
						out.print("<script language='javascript'>alert('成功修改零件盘点信息！');window.location='stocktake.jsp';</script>");
					}
				}catch(Exception e)	
				{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加零件盘点零件类别信息！');window.location='stocktake.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改零件盘点零件类别信息！');window.location='stocktake.jsp';</script>");
				}
				
			}else if (operatorStr.equals("delete"))
			{
				StocktakeDAO cdao=new StocktakeDAO();
				int id = Integer.parseInt(request.getParameter("id"));
	
			    	try
			    	{
			    		cdao.deleteById(id);
			    		out.print("<script language='javascript'>alert('成功删除零件盘点信息！');window.location='stocktake.jsp';</script>");
			    	}catch(Exception e)	
			    	{
			    		out.print("<script language='javascript'>alert('删除零件盘点信息失败！');window.location='stocktake.jsp';</script>");
			    	}
			    
			}
		}

	}	
}
