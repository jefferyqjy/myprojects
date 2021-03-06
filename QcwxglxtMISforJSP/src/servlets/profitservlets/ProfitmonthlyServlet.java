package servlets.profitservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;

import bean.Partinner;
import bean.Profit;
import bean.ProfitMonthly;
import bean.Wage;
import dao.PartinnerDAO;
import dao.ProfitDAO;
import dao.WageDAO;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "Profitmonthly", description = "Profitmonthly", urlPatterns = { "/Profitmonthly" })
public class ProfitmonthlyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfitmonthlyServlet() {
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
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		// 查询全部或查询部分
		String queryStr = "";
		try {
			// 查询全部
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();  
			
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
			String createTimeLte = format.format(calendar.getTime());
			
			calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
			String createTimeGte = format.format(calendar.getTime());
			
			/* 计算结账单利润 */
			ProfitDAO pdao = new ProfitDAO();
			queryStr = "select p.pId, p.cId, p.profit, p.cost, p.createTime from profit p where p.createTime <= '" + createTimeLte + "' and p.createTime >= '" + createTimeGte + "'";
			List<Profit> plist = pdao.findByPage(queryStr, 0, 1000);
			BigDecimal checkoutProfit = BigDecimal.ZERO;
			if(plist != null && plist.size() > 0) {
				for(Profit m : plist) {
					BigDecimal p = m.getProfit();
					checkoutProfit = checkoutProfit.add(p);
				}
			}
			
			/* 计算内配利润 */
			PartinnerDAO pdao2 = new PartinnerDAO();
			queryStr = "select p.iId, p.empId, p.company, p.pCost, p.pPrice, p.pId, p.pNum, p.date from partinner p where p.date <= '" + createTimeLte + "' and p.date >= '" + createTimeGte + "'";
			List<Partinner> pis =  pdao2.findByPage(queryStr, 0, 1000);
			BigDecimal innerProfit = BigDecimal.ZERO;
			if(pis != null && pis.size() > 0) {
				for(Partinner m : pis) {
					BigDecimal cost = m.getpCost();
					BigDecimal price = m.getpPrice();
					Integer num = m.getpNum();
					BigDecimal profit = price.subtract(cost);
					BigDecimal totalProfit = profit.multiply(new BigDecimal(num));
					innerProfit = innerProfit.add(totalProfit);
				}
			}
			
			/*计算退货损失*/
			BigDecimal returnCost = new BigDecimal(0);
			
			/*计算工资支出*/
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
			String month = df.format(date);
			WageDAO wagedao = new WageDAO();
			String sql = "select * from wage where month = '" + month + "'";
			List<Wage> wagelist = wagedao.findByPage(sql, 1, 100);
			BigDecimal salaryCost = new BigDecimal(0.00);
			if(CollectionUtils.isNotEmpty(wagelist)) {
				for(Wage w : wagelist) {
					Float s = w.getSalary();
					salaryCost = salaryCost.add(new BigDecimal(s));
				}
			}
			
			/*计算总利润*/
			BigDecimal totalProfit = checkoutProfit.add(innerProfit).subtract(returnCost) .subtract(salaryCost);
			
			ProfitMonthly pm = new ProfitMonthly();
			pm.setCheckoutProfit(checkoutProfit);
			pm.setInnerProfit(innerProfit);
			pm.setReturnCost(returnCost);
			pm.setSalaryCost(salaryCost);
			pm.setTotalProfit(totalProfit);;
			
			request.setAttribute("profitmonthly", pm);
			request.setAttribute("totalRows", 1);
			request.setAttribute("totalPage", 1);
			request.setAttribute("currentPage", 0);
			request.setAttribute("operator", "1");
		} catch (Exception e2) {
			out.print("<script language='javascript'>alert('统计本月利润失败！');window.location='profitmonthly.jsp';</script>");
		}
		getServletConfig().getServletContext().getRequestDispatcher("/profitmonthly.jsp").forward(request, response);
		return;
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();  
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
		System.out.println(format.format(calendar.getTime()));  
		
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		System.out.println(format.format(calendar.getTime()));
		
		BigDecimal a = new BigDecimal(1);
		BigDecimal b = new BigDecimal(2);
		System.out.println(a.add(b));
	}
}
