package servlets.messageservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import bean.Customer;
import bean.Message;
import dao.CustomerDAO;
import dao.MessageDAO;
import util.RowCount;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "Message", description = "Message", urlPatterns = { "/Message" })
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageServlet() {
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
		List<Message> messages = new ArrayList<Message>();
		MessageDAO dao = new MessageDAO();
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
					// 查询部分
					if(StringUtils.equals(operator, "1")) {
						queryStr="select m.mId, m.cusId, c.cusName, m.createTime, m.content from message m, customer c where m.cusId = c.cusID ";
						countStr = "select count(*) from message m ";
					}
					if (operator.equals("0")) {
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						String userId = request.getParameter("cusId");
						queryStr="select m.mId, m.cusId, c.cusName, m.createTime, m.content from message m, customer c where m.cusId = c.cusID ";
						countStr = "select count(*) from message m where 1=1 ";
						if(StringUtils.isNotEmpty(userId)) {
							queryStr += (" and m.cusId = " + userId);
							countStr += (" and m.cusId = " + userId);
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
					messages = dao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；
					if(messages.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='message.jsp';</script>");
						return;
					} else {
						CustomerDAO cdao = new CustomerDAO();
						for(Message m : messages){
							Integer cusId = m.getCusId();
							Customer c = cdao.findById(cusId);
							m.setCusName(c.getCusName());
						}
					}
					
					request.setAttribute("messages", messages);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找留言信息失败！');window.location='message.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
				return;
			} 
		}
	}
}
