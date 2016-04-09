package servlets.profitservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bean.Checkouts;
import bean.Profit;
import dao.CheckoutsDAO;
import dao.ProfitDAO;
import util.RowCount;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "Profit", description = "Profit", urlPatterns = { "/Profit" })
public class ProfitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfitServlet() {
		super();
	}

	/* Ser 销毁的时候调用此方法 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operator = request.getParameter("operator");
		List<Profit> ps = new ArrayList<Profit>();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		if (StringUtils.isNotEmpty(operator)) {
			// 查询全部或查询部分
			if (StringUtils.equals(operator, "0") || StringUtils.equals(operator, "1")) {
				String queryStr = "";
				String countStr = "";
				String queryName = "";
				String queryValue = "";
				int totalRows = 0;
				int totalPage = 0;
				int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				int pagerMethod = Integer.parseInt(request.getParameter("pagerMethod"));
				try {
					// 查询全部
					if(StringUtils.equals(operator, "1")) {
						queryStr="select p.pId, p.cId, p.profit, p.cost, p.createTime from profit p, checkouts c where p.cId = c.cID ";
						countStr = "select count(*) from profit p ";
					}
					if (operator.equals("0")) {
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						queryStr="select p.pId, p.cId, p.profit, p.cost, p.createTime from profit p, checkouts c where p.cId = c.cID";
						countStr = "select count(*) from profit p where 1=1";
						if(StringUtils.isNotEmpty(queryValue)) {
							queryStr += (" and p.cId = " + queryValue);
							countStr += (" and p.cId = " + queryValue);
						}
					} 
					
					totalRows = (new RowCount()).getTotalrow(countStr); // 获得查询总纪录数；
					totalPage = (int) Math.ceil(1.0 * totalRows / pageCount);

					if (pagerMethod == 1) {
						currentPage = 1;
					} else if (pagerMethod == 2) {
						currentPage = currentPage - 1;
					} else if (pagerMethod == 3) {
						currentPage = currentPage + 1;
					} else if (pagerMethod == 4) {
						currentPage = totalPage;
					}

					if (currentPage <= 0) {
						currentPage = 1;
					}

					if (currentPage > totalPage) {
						currentPage = totalPage;
					}
					ProfitDAO pdao = new ProfitDAO();
					ps = pdao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；
					if(ps.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='profit.jsp';</script>");
						return;
					} else {
						for(Profit m : ps){
							// 设置非数据库字段的值
							CheckoutsDAO cdao = new CheckoutsDAO();
							Checkouts c = cdao.findById(m.getcId());
							m.setCheckouts(c);
						}
					}
					
					request.setAttribute("profits", ps);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找结账利润信息失败！');window.location='profit.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/profit.jsp").forward(request, response);
				return;
			}  else if(StringUtils.equals(operator, "2")) {
				// 计算并生成该结账单的利润
				String cId = request.getParameter("cId");
				
				try {
					ProfitDAO pdao = new ProfitDAO();
					List<Profit> list = pdao.findByCheckoutId(cId);
					if(list != null && list.size() > 0) {
						out.print("<script language='javascript'>alert('生成利润记录失败！该结账单已计算！');window.location='Profit?operator=1&&currentPage=0&&pagerMethod=1';</script>");
						return;
					}
					
					CheckoutsDAO cdao = new CheckoutsDAO();
					Checkouts co = cdao.findById(Integer.valueOf(cId));
					Profit p = new Profit();
					p.setcId(Integer.valueOf(cId));
					BigDecimal cost = new BigDecimal(co.getPartcost());
					cost.setScale(2);
					p.setCost(cost);
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					p.setCreateTime(df.format(new Date()));
					
					BigDecimal consume = new BigDecimal(co.getXiaofei());
					consume.setScale(2);
					
					BigDecimal profit = consume.subtract(cost);
					p.setProfit(profit);
					pdao = new ProfitDAO();
					pdao.insert(p);
					
					out.print("<script language='javascript'>alert('生成利润记录成功！');window.location='Profit?operator=1&&currentPage=0&&pagerMethod=1';</script>");
					return;
				} catch (Exception e) {
					out.print("<script language='javascript'>alert('生成利润记录失败！');window.location='profit.jsp';</script>");
					e.printStackTrace();
					return;
				}
				
			}
		} 
		
	}
	
}
