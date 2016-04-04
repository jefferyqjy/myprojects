package servlets.checkoutsservlets;

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
 * Servlet implementation class checkoutsQueryServlet
 */
@WebServlet(name = "CheckoutsQuery", description = "CheckoutsQuery", urlPatterns = { "/CheckoutsQuery" })
public class CheckoutsQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount = 7;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	CheckoutsDAO Checkouts = new CheckoutsDAO();

	public CheckoutsQueryServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operator = request.getParameter("operator");
		List<Checkouts> checkoutss = new ArrayList<Checkouts>();
		CheckoutsDAO dao = new CheckoutsDAO();
		Checkouts checkouts = new Checkouts();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。

		if (operator != null) {
			if (operator.equals("0")) // 查询全部或查询部分
			{
				String queryStr = "";
				String countStr = "";
				String queryName = "";
				String queryValue = "";
				int totalRows = 0;
				int totalPage = 0;
				int currentPage = Integer.parseInt(request.getParameter("currentPage"));
				int pagerMethod = Integer.parseInt(request.getParameter("pagerMethod"));
				try {
					if (operator.equals("0")) // 查询部分
					{
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						if (!queryName.equals("cID")) // 纠正中文乱码
						{
							ChangeToGBK chGBK = new ChangeToGBK();
							queryValue = chGBK.change("queryValue", request);
						}
						int userId = ((Customer)request.getSession().getAttribute("customer")).getCusID();
						if(queryValue.equals(Integer.toString(userId))){
							queryStr="select cID,checkouts.aID,repaircost,partID,partname,checknum,partprice,xiaofei,checkoutsdate,beizhu,yuyue.cusID,arrange.yID from yuyue,arrange,checkouts where arrange.aID=checkouts.aID and yuyue.yID=arrange.yID and yuyue.cusID ='"+userId+"'";
							countStr = "select count(cID) from  arrange,yuyue,checkouts where checkouts.cID and yuyue.cusID ='"+userId+"'";
						}else{
							out.print("<script language='javascript'>alert('请输入您的编号！');window.location='checkouts_cus.jsp';</script>");
							return ;	
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
					checkoutss = dao.findByPagecus(queryStr, currentPage,pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；

					if(checkoutss.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='checkouts_cus.jsp';</script>");
						return;
					}
					
					request.setAttribute("checkoutss", checkoutss);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找客户结账信息失败！');window.location='checkouts_cus.jsp';</script>");
					}
				getServletConfig().getServletContext().getRequestDispatcher("/checkouts_cus.jsp").forward(request, response);
				return;
				
			}else if (operator.equals("3")) 
			{
					String cID = request.getParameter("cID");
					try {
						checkouts = dao.findById(Integer.parseInt(cID));

					} catch (Exception e2) {
						out.print("<script language='javascript'>alert('查找客户结账信息失败！');window.location='checkouts.jsp';</script>");
					}

				HttpSession session = request.getSession();

				session.setAttribute("operatorStr", "modify");
				session.setAttribute("cID", checkouts.getcID());

				session.setAttribute("checkouts", checkouts);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/evaluationAdd.jsp").forward(request, response);
				return;
			}
		}

		String operatorStr = request.getParameter("operatorStr");

		if (operatorStr != null) // operatorStr!=null，说明请求从evaluationAdd.jsp来，准备进行数据的插入、删除和修改
		{
			if (operatorStr.equals("modify")) {
					Evaluation evaluation =new Evaluation();
					Evaluation evaluation1 =new Evaluation();
					int cID = (int) (request.getSession().getAttribute("cID"));
					evaluation.setcID(cID);
					evaluation.setEvaluatime(request.getParameter("evaluatime"));
					evaluation.setEvaluation(request.getParameter("evaluation"));
					EvaluationDAO evaluationdao = new EvaluationDAO();
					try {
						evaluation1 = evaluationdao.findBycId(cID);
							if(evaluation1.getcID()==cID){
								out.print("<script language='javascript'>alert('该结账单已评价！不可再评价');window.location='checkouts_cus.jsp';</script>");
							}else{
								evaluationdao.insert(evaluation);
								out.print("<script language='javascript'>alert('评价成功！');window.location='checkouts_cus.jsp';</script>");
							}
					} catch (Exception e) {
						
							out.print("<script language='javascript'>alert('修改客户结账信息！');window.location='checkouts_cus.jsp';</script>");
					}

				} 

			}

		}
	}
