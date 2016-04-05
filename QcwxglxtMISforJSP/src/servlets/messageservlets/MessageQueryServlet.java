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

import bean.Message;
import dao.MessageDAO;
import util.RowCount;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "MessageQuery", description = "MessageQuery", urlPatterns = { "/MessageQuery" })
public class MessageQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageQueryServlet() {
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
			if (StringUtils.equals(operator, "0")) {
				String queryStr = "";
				String countStr = "";
				//String queryName = "";
				//String queryValue = "";
				int totalRows = 0;
				int totalPage = 0;
				int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				int pagerMethod = Integer.parseInt(request.getParameter("pagerMethod"));
				try {
					// 查询部分
					if (operator.equals("0")) {
						String userId = request.getParameter("cusId");
						queryStr="select m.mId, m.cusId, m.createTime, m.content from message m where m.cusId = " + userId;
						countStr = "select count(*) from message m where m.cusId = " + userId;
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
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='message_cus.jsp';</script>");
						return;
					}
					request.setAttribute("messages", messages);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找预约单信息失败！');window.location='message_cus.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/message_cus.jsp").forward(request, response);
				return;
			} else if(StringUtils.equals(operator, "2")) {
				// add a new message
				
			}
		}
	}
}
