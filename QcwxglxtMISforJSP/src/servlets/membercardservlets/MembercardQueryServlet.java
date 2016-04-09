package servlets.membercardservlets;

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
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bean.Customer;
import bean.Membercard;
import dao.CustomerDAO;
import dao.MembercardDAO;
import util.RowCount;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "MembercardQuery", description = "MembercardQuery", urlPatterns = { "/MembercardQuery" })
public class MembercardQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MembercardQueryServlet() {
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
		Customer cus = (Customer) request.getSession().getAttribute("customer");
		Integer cusId = null;
		if(cus != null) {
			cusId = cus.getCusID();
		}
		List<Membercard> mcs = new ArrayList<Membercard>();
		MembercardDAO dao = new MembercardDAO();
		Membercard membercard = new Membercard();
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
						queryStr="select m.mId, m.cusId, c.cusName, m.amount, m.cardNo, m.createTime from membercard m, customer c where m.cusId = c.cusID ";
						countStr = "select count(*) from membercard m ";
					}
					if (operator.equals("0")) {
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						queryStr="select m.mId, m.cusId, c.cusName, m.amount, m.cardNo, m.createTime from membercard m, customer c where m.cusId = c.cusID ";
						countStr = "select count(*) from membercard m where 1=1 ";
						if(cusId != null) {
							queryStr += (" and m.cusId = " + cusId);
							countStr += (" and m.cusId = " + cusId);
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
					mcs = dao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；
					if(mcs.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='membercard_cus.jsp';</script>");
						return;
					} else {
						for(Membercard m : mcs){
							CustomerDAO cdao = new CustomerDAO();
							Integer cusId1 = m.getCusId();
							Customer c = cdao.findById(cusId1);
							m.setCusName(c.getCusName());
						}
					}
					
					request.setAttribute("membercards", mcs);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找会员卡失败！');window.location='membercard_cus.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/membercard_cus.jsp").forward(request, response);
				return;
			} else if(StringUtils.equals(operator, "2") || StringUtils.equals(operator, "3") || StringUtils.equals(operator, "4")) {
				List<Membercard> mcs1 = new ArrayList<Membercard>();
				MembercardDAO mdao=new MembercardDAO();
				try {
					mcs1 = mdao.findAll();
				}catch(Exception e) {
					out.print("<script language='javascript'>alert('查找会员卡失败！');window.location='membercardEdit.jsp';</script>");
				}
			
				if(operator.equals("3")||operator.equals("4")) {	
					String mId = request.getParameter("mId");
					try {
						membercard = dao.findByMembercardId(mId);
					}catch(Exception e2) {
						out.print("<script language='javascript'>alert('查找会员卡失败！');window.location='membercardEdit.jsp';</script>");
					}
				}
			
				HttpSession  session=request.getSession();  
				//找到留言后，把该留言的Id放入session，修改该留言信息再保存时可以从session中取出id。
				if (operator.equals("3")) {	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("mId", membercard.getmId());
				} else if (operator.equals("4")) {
					session.setAttribute("operatorStr", "delete");
				} else {
					session.setAttribute("operatorStr", "add");
				}

				session.setAttribute("mcs", mcs1);//修改或添加留言时，若输入信息通不过检测，需要返回维护留言页面，必须把类别信息放到session，否则会丢失
				Integer cusId2 = membercard.getCusId();
				CustomerDAO cdao = new CustomerDAO();
				Customer cus2;
				try {
					cus2 = cdao.findById(cusId2);
					if(cus2 != null) {
						membercard.setCusName(cus2.getCusName());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				session.setAttribute("membercard", membercard);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/membercardEdit.jsp").forward(request, response);
				return;
			}
		} 
		
		String operatorStr=request.getParameter("operatorStr");
		//operatorStr!=null，说明请求从messageEdit.jsp来，准备进行数据的插入、删除和修改
		if(operatorStr != null) {	
			if(operatorStr.equals("add") ||operatorStr.equals("modify")) {
				Membercard membercard1 = new Membercard();
				if (operatorStr.equals("modify")) {
					membercard1.setmId((Integer) request.getSession().getAttribute("mId"));
				}
				String amountStr = request.getParameter("amount");
				BigDecimal amount = new BigDecimal(amountStr);
				amount.setScale(2);
				membercard1.setAmount(amount);
				membercard1.setCardNo(request.getParameter("cardNo"));
				membercard1.setCusId(Integer.valueOf(request.getParameter("cusId")));
				membercard1.setCreateTime(request.getParameter("createTime"));
					
				MembercardDAO mdao = new MembercardDAO();
				try {
					if (operatorStr.equals("add")) {	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						membercard1.setCreateTime(df.format(new Date()));
						
						SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
						String prefix = df2.format(new Date());
						String cusIdStr = membercard1.getCusId() + "";
						String cardNo = prefix + cusIdStr;
						membercard1.setCardNo(cardNo);
						mdao.insert(membercard1);
						out.print("<script language='javascript'>alert('成功添加会员卡信息！');window.location='membercard_cus.jsp';</script>");
					}
					else {
						mdao.update(membercard1);
						out.print("<script language='javascript'>alert('成功修改会员卡信息！');window.location='membercard_cus.jsp';</script>");
					}
				}catch(Exception e)	{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加会员卡信息失败！');window.location='membercardEdit.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改会员卡信息失败！');window.location='membercardEdit.jsp';</script>");
				}
			} else if (operatorStr.equals("delete")) {
				MembercardDAO mdao = new MembercardDAO();
				String mId = request.getParameter("mId");
		    	try {
		    		mdao.deleteById(Integer.valueOf(mId.trim()));
		    		out.print("<script language='javascript'>alert('成功删除会员卡！');window.location='membercard_cus.jsp';</script>");
		    	}catch(Exception e)	{
		    		out.print("<script language='javascript'>alert('删除会员卡失败！');window.location='membercard_cus.jsp';</script>");
		    	}
			}
		}
	}
	
	public static void main(String[] args) {
		String a = "1000";
		BigDecimal amount = new BigDecimal(a);
		amount.setScale(2);
		System.out.println(amount.doubleValue());
	}
}
