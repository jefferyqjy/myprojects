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
import bean.Checkouts;
import bean.Customer;
import dao.CheckoutsDAO;
import dao.CustomerDAO;

/**
 * Servlet implementation class checkoutsServlet
 */
@WebServlet(name = "Checkouts", description = "Checkouts", urlPatterns = { "/Checkouts" })
public class CheckoutsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount = 7;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	CheckoutsDAO Checkouts = new CheckoutsDAO();

	public CheckoutsServlet() {
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
			if (operator.equals("1") || operator.equals("0")) // 查询全部或查询部分
			{
				String queryStr = "";
				String countStr = "";
				String queryName = "";
				String queryValue = "";
				int totalRows = 0;
				int totalPage = 0;
				int currentPage = Integer.parseInt(request
						.getParameter("currentPage"));
				int pagerMethod = Integer.parseInt(request
						.getParameter("pagerMethod"));
				try {
					if (operator.equals("1")) // 查询全部
					{
						queryStr = "select cID,checkouts.aID,repaircost,partID,partname,checknum,partprice,xiaofei,checkoutsdate,beizhu from arrange,checkouts where arrange.aID=checkouts.aID";
						countStr = "select count(cID) from  checkouts where checkouts.cID";
					} else // 查询部分
					{
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						if (!queryName.equals("cID")) // 纠正中文乱码
						{
							ChangeToGBK chGBK = new ChangeToGBK();
							queryValue = chGBK.change("queryValue", request);
						}

						queryStr = "select cID,checkouts.aID,repaircost,partID,partname,checknum,partprice,xiaofei,checkoutsdate,beizhu from arrange,checkouts where arrange.aID=checkouts.aID and "
								+ queryName + "='" + queryValue + "'";
						countStr = "select count(cID) from  checkouts where checkouts.cID and "
								+ queryName + "='" + queryValue + "'";
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
					checkoutss = dao.findByPage(queryStr, currentPage,pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；

					if(checkoutss.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='checkouts.jsp';</script>");
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
					out.print("<script language='javascript'>alert('查找客户结账信息失败！');window.location='checkouts.jsp';</script>");
					}
				getServletConfig().getServletContext().getRequestDispatcher("/checkouts.jsp").forward(request, response);
				return;
				
			} else if (operator.equals("2") || operator.equals("3")|| operator.equals("4")) // find by checkouts id.
			{
				List<Checkouts> checkouts1 = new ArrayList<Checkouts>();
				CheckoutsDAO cdao = new CheckoutsDAO();
				try {
					checkouts1 = cdao.findAll();
				} catch (Exception e) {
					out.print("<script language='javascript'>alert('查找客户结账信息失败！');window.location='checkouts.jsp';</script>");
				}

				if (operator.equals("3") || operator.equals("4")) {
					String cID = request.getParameter("cID");
					try {
						checkouts = dao.findById(Integer.parseInt(cID));

					} catch (Exception e2) {
						out.print("<script language='javascript'>alert('查找客户结账信息失败！');window.location='checkouts.jsp';</script>");
					}
				}

				HttpSession session = request.getSession();

				if (operator.equals("3")) // 找到商品后，把该商品的商品编码放入session，修改该商品信息再保存时可以从session中取出商品编码。
				{
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("cID", checkouts.getcID());
				} else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else
					session.setAttribute("operatorStr", "add");

				session.setAttribute("checkoutss", checkouts1);// 修改或添加商品时，若输入信息通不过检测，需要返回维护商品页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("checkouts", checkouts);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/checkoutsEdit.jsp").forward(request, response);
				return;
			}
		}

		String operatorStr = request.getParameter("operatorStr");

		if (operatorStr != null) // operatorStr!=null，说明请求从checkoutsEdit.jsp来，准备进行数据的插入、删除和修改
		{
			if (operatorStr.equals("add") || operatorStr.equals("modify")) {
				Checkouts checkouts1 = new Checkouts();
				if (operatorStr.equals("modify"))
					checkouts1.setcID((int) (request.getSession().getAttribute("cID")));
					checkouts1.setaID(Integer.parseInt(request.getParameter("aID")));
					checkouts1.setPartID(request.getParameter("partID"));
					checkouts1.setPartname(request.getParameter("partname"));
					checkouts1.setChecknum(Double.parseDouble(request.getParameter("checknum")));
					checkouts1.setPartprice(Double.parseDouble(request.getParameter("partprice")));
					checkouts1.setRepaircost(Double.parseDouble(request.getParameter("repaircost")));
					checkouts1.setXiaofei(Double.parseDouble(request.getParameter("xiaofei")));
					checkouts1.setCheckoutsdate(request.getParameter("checkoutsdate"));
					checkouts1.setBeizhu(request.getParameter("beizhu"));
					CheckoutsDAO cdao = new CheckoutsDAO();
					try {
						if (operatorStr.equals("add")) {
							
							Customer cust = new CustomerDAO().findByaID(checkouts1.getaID());
							cdao.insert(checkouts1);
							double curr = Double.valueOf(checkouts1.getXiaofei());
							cust.setZongxiaofei(cust.getZongxiaofei() + curr);
							if (cust.getZongxiaofei() >= 3000) {
								cust.setVip(1);
							}
							new CustomerDAO().Update(cust);
							out.print("<script language='javascript'>alert('成功添加客户结账信息！');window.location='checkouts.jsp';</script>");
						} else {
							checkouts1.setBeizhu(request.getParameter("beizhu"));
							cdao.update(checkouts1);
							out.print("<script language='javascript'>alert('成功修改客户结账信息！');window.location='checkouts.jsp';</script>");
						}
					} catch (Exception e) {
						if (operatorStr.equals("add"))
							out.print("<script language='javascript'>alert('添加客户结账信息！');window.location='checkouts.jsp';</script>");
						else
							out.print("<script language='javascript'>alert('修改客户结账信息！');window.location='checkouts.jsp';</script>");
					}

				} else if (operatorStr.equals("delete")) {
					CheckoutsDAO cdao = new CheckoutsDAO();
					int cID = Integer.parseInt(request.getParameter("cID"));

					try {
						
						cdao.deleteById(cID);

						out.print("<script language='javascript'>alert('成功删除客户结账信息！');window.location='checkouts.jsp';</script>");
					} catch (Exception e) {
						
						out.print("<script language='javascript'>alert('删除零件客户结账失败！');window.location='checkouts.jsp';</script>");
					}

				}

			}

		}
	}
