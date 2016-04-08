package servlets.messageservlets;

import java.io.IOException;
import java.io.PrintWriter;
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
		Customer cus = (Customer) request.getSession().getAttribute("customer");
		List<Message> messages = new ArrayList<Message>();
		MessageDAO dao = new MessageDAO();
		Message message = new Message();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		if (StringUtils.isNotEmpty(operator)) {
			// 查询全部或查询部分
			if (StringUtils.equals(operator, "0")) {
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
					if (operator.equals("0")) {
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
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
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找留言信息失败！');window.location='message_cus.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/message_cus.jsp").forward(request, response);
				return;
			} else if(StringUtils.equals(operator, "2") || StringUtils.equals(operator, "3") || StringUtils.equals(operator, "4")) {
				List<Message> message1 = new ArrayList<Message>();
				MessageDAO mdao=new MessageDAO();
				try {
					message1 = mdao.findAll();
				}catch(Exception e) {
					out.print("<script language='javascript'>alert('查找留言信息失败！');window.location='messageEdit_cus.jsp';</script>");
				}
			
				if(operator.equals("3")||operator.equals("4")) {	
					String mId = request.getParameter("mId");
					try {
						message = dao.findByMessageId(mId);
					}catch(Exception e2) {
						out.print("<script language='javascript'>alert('查找留言信息失败！');window.location='messageEdit_cus.jsp';</script>");
					}
				}
			
				HttpSession  session=request.getSession();  
				//找到留言后，把该留言的Id放入session，修改该留言信息再保存时可以从session中取出id。
				if (operator.equals("3")) {	
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("mId",message.getmId());
				} else if (operator.equals("4")) {
					session.setAttribute("operatorStr", "delete");
				} else {
					session.setAttribute("operatorStr", "add");
				}

				session.setAttribute("messages", message1);//修改或添加留言时，若输入信息通不过检测，需要返回维护留言页面，必须把类别信息放到session，否则会丢失
				message.setCusId(cus.getCusID());
				session.setAttribute("msg", message);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/messageEdit_cus.jsp").forward(request, response);
				return;
			}
		}
		
		String operatorStr=request.getParameter("operatorStr");
		//operatorStr!=null，说明请求从messageEdit.jsp来，准备进行数据的插入、删除和修改
		if(operatorStr != null) {	
			if(operatorStr.equals("add") ||operatorStr.equals("modify")) {
				Message message1 = new Message();
				if (operatorStr.equals("modify")) {
					message1.setmId((Integer) request.getSession().getAttribute("mId"));
				}
				message1.setContent(request.getParameter("content"));
				message1.setCreateTime(request.getParameter("createTime"));
				if(cus != null) {
					message1.setCusId(cus.getCusID());
				}
					
				MessageDAO mdao = new MessageDAO();
				try {
					if (operatorStr.equals("add")) {	
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						message1.setCreateTime(df.format(new Date()));
						mdao.insert(message1);
						out.print("<script language='javascript'>alert('成功添加留言信息！');window.location='message_cus.jsp';</script>");
					}
					else {
						mdao.update(message1);
						out.print("<script language='javascript'>alert('成功修改留言信息！');window.location='messageEdit_cus.jsp';</script>");
					}
				}catch(Exception e)	{
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加留言信息失败！');window.location='messageEdit_cus.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改留言信息失败！');window.location='messageEdit_cus.jsp';</script>");
				}
			} else if (operatorStr.equals("delete")) {
				MessageDAO mdao = new MessageDAO();
				String mId = request.getParameter("mId");
		    	try {
		    		mdao.deleteById(Integer.valueOf(mId.trim()));
		    		out.print("<script language='javascript'>alert('成功删除留言！');window.location='message_cus.jsp';</script>");
		    	}catch(Exception e)	{
		    		out.print("<script language='javascript'>alert('删除留言失败！');window.location='message_cus.jsp';</script>");
		    	}
			}
		}
	}
}
