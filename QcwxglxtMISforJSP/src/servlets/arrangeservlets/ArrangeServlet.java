package servlets.arrangeservlets;

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

import dao.*;
import bean.*;

/**
 * Servlet implementation class ArrangeServlet
 */
@WebServlet(name = "Arrange", description = "Arrange", urlPatterns = { "/Arrange" })
public class ArrangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount = 7;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	ArrangeDAO arrange = new ArrangeDAO();

	public ArrangeServlet() {
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
		List<Arrange> arranges = new ArrayList<Arrange>();
		ArrangeDAO dao = new ArrangeDAO();
		Arrange arrange = new Arrange();
		PartkcDAO partkcdao = new PartkcDAO();
		response.setContentType("text/html;charset=UTF-8"); // 通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");// response的响应的编码方式为utf-8
		PrintWriter out = response.getWriter();// 通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。

		if (operator != null) {
			if (operator.equals("1") || operator.equals("0")) // 查询全部或查询部分
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
					if (operator.equals("1")) // 查询全部
					{
						queryStr = "select  arrange.aID,arrange.empID,employee.empName,empphone,arrange.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyue.yuyuetime,anpaitime,wangongtime,arrange.partID,arrange.partname,checknum,!isnull(checkouts.aID) as modifyflag from arrange left join checkouts on checkouts.aID=arrange.aID join employee on  arrange.empID=employee.empID join yuyue on arrange.yID=yuyue.yID join customer on yuyue.cusID=customer.cusID order by arrange.aID";
						countStr = "select count(aID) from  arrange,yuyue,employee,customer where arrange.empID=employee.empID and arrange.yID=yuyue.yID and yuyue.cusID=customer.cusID order by aID";
					} else // 查询部分
					{
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						if (!queryName.equals("aID")) // 纠正中文乱码
						{
							ChangeToGBK chGBK = new ChangeToGBK();
							queryValue = chGBK.change("queryValue", request);
						}

						queryStr = "select  arrange.aID,arrange.empID,employee.empName,empphone,arrange.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyue.yuyuetime,anpaitime,wangongtime,arrange.partID,arrange.partname,checknum,!isnull(checkouts.aID) as modifyflag from arrange left join checkouts on checkouts.aID=arrange.aID join employee on  arrange.empID=employee.empID join yuyue on arrange.yID=yuyue.yID join customer on yuyue.cusID=customer.cusID and "
								+ queryName
								+ "='"
								+ queryValue
								+ "' order by aID";
						countStr = "select count(aID) from  arrange,yuyue,employee,customer where arrange.empID=employee.empID and arrange.yID=yuyue.yID and yuyue.cusID=customer.cusID and "
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
					arranges = dao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；

					if (arranges.size() == 0) {
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='arrange.jsp';</script>");
						return;
					}

					request.setAttribute("arranges", arranges);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找维修安排信息失败！');window.location='arrange.jsp';</script>");
				}

				getServletConfig().getServletContext().getRequestDispatcher("/arrange.jsp").forward(request, response);
				return;
			} else if (operator.equals("2") || operator.equals("3")|| operator.equals("4") || operator.equals("5")) // find by arrange id.
			{
				List<Arrange> arrange1 = new ArrayList<Arrange>();
				ArrangeDAO adao = new ArrangeDAO();
				List<Customer> customer = new ArrayList<Customer>();
				CustomerDAO cdao = new CustomerDAO();
				List<Employee> employees = new ArrayList<Employee>();
				EmployeeDAO edao = new EmployeeDAO();
				try {
					arrange1 = adao.findAll();
					customer = cdao.findAll();
					employees = edao.findAll();

				} catch (Exception e) {
					out.print("<script language='javascript'>alert('查找维修安排信息失败！');window.location='arrange.jsp';</script>");
				}

				if (operator.equals("3") || operator.equals("4") || operator.equals("5")) {
					String aID = request.getParameter("aID");
					try {
						arrange = dao.findById(Integer.parseInt(aID));

					} catch (Exception e2) {
						out.print("<script language='javascript'>alert('查找维修安排信息失败！');window.location='arrange.jsp';</script>");
					}
				}

				HttpSession session = request.getSession();

				if (operator.equals("3")) // 找到维修安排后，把该维修安排的维修安排编码放入session，修改该维修安排信息再保存时可以从session中取出维修安排编码。
				{
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("aID", arrange.getaID());
				} else if (operator.equals("4"))
					session.setAttribute("operatorStr", "delete");
				else if (operator.equals("5"))
					session.setAttribute("operatorStr", "queryarrange");
				else
					session.setAttribute("operatorStr", "add");

				session.setAttribute("arranges", arrange1);// 修改或添加维修安排时，若输入信息通不过检测，需要返回维护维修安排页面，必须把信息放到session，否则会丢失
				session.setAttribute("employees", employees);
				session.setAttribute("customer", customer);
				session.setAttribute("arrange", arrange);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/arrangeEdit.jsp").forward(request, response);
				return;
			}
		}

		String operatorStr = request.getParameter("operatorStr");

		if (operatorStr != null) // operatorStr!=null，说明请求从arrangeEdit.jsp来，准备进行数据的插入、删除和修改
		{
			if (operatorStr.equals("add") || operatorStr.equals("modify")) {
				Arrange arrange1 = new Arrange();
				Arrange arrangeold = new Arrange();
				if (operatorStr.equals("modify"))
				arrange1.setaID((int) (request.getSession().getAttribute("aID")));
				String checknum = request.getParameter("checknum");
				String partID = request.getParameter("partID");
				arrange1.setEmpID(Integer.parseInt(request.getParameter("empID")));
				arrange1.setEmpName(request.getParameter("empName"));
				arrange1.setEmpphone(request.getParameter("empphone"));
				arrange1.setyID(Integer.parseInt(request.getParameter("yID")));
				arrange1.setCusID(Integer.parseInt(request.getParameter("cusID")));
				arrange1.setCusName(request.getParameter("cusName"));
				arrange1.setCusphone(request.getParameter("cusphone"));
				arrange1.setCarnum(request.getParameter("carnum"));
				arrange1.setCarmoder(request.getParameter("carmoder"));
				arrange1.setRepairitem(request.getParameter("repairitem"));
				arrange1.setYuyuetime(request.getParameter("yuyuetime"));
				arrange1.setAnpaitime(request.getParameter("anpaitime"));
				arrange1.setWangongtime(request.getParameter("wangongtime"));
				arrange1.setPartID(partID);
				arrange1.setPartname(request.getParameter("partname"));
				arrange1.setChecknum(Double.parseDouble(checknum));
				ArrangeDAO adao = new ArrangeDAO();
				try {
					if (operatorStr.equals("add")) {
						int currStoreNum = partkcdao.findCurrStoreNumById(request.getParameter("partID"));
						if (currStoreNum < Double.parseDouble(checknum)) {
							out.print("<script language='javascript'>alert('当前库存不足，最大可用库存量为【"
									+ currStoreNum
									+ "】！');window.location='arrangeEdit.jsp';</script>");
						} else {
							adao.insert(arrange1);// 添加到arrange
							Partkc partkc = new Partkc();
							partkc = partkcdao.findBypartId(partID);// 找到库存表中零件编号
							partkc.setPartkcnum(partkc.getPartkcnum()
									- arrange1.getChecknum());
							partkcdao.update(partkc);
						}
						out.print("<script language='javascript'>alert('成功添加维修安排信息！');window.location='arrange.jsp';</script>");
					} else {
						try {
							arrangeold = dao.findById((int) (request.getSession().getAttribute("aID")));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						adao.update(arrange1);
						Partkc partkc = new Partkc();
						partkc = partkcdao.findBypartId(partID);// 找到库存表中零件编号
						partkc.setPartkcnum(partkc.getPartkcnum()
								+ arrangeold.getChecknum()
								- arrange1.getChecknum());
						partkcdao.update(partkc);

						out.print("<script language='javascript'>alert('成功修改维修安排信息！');window.location='arrange.jsp';</script>");

					}
				} catch (Exception e) {
					if (operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加维修安排信息！');window.location='arrange.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改维修安排信息！');window.location='arrange.jsp';</script>");
				}

			} else if (operatorStr.equals("delete")) {
				ArrangeDAO adao = new ArrangeDAO();
				Arrange arrangeold =new Arrange();
				int aID = Integer.parseInt(request.getParameter("aID"));

				try {
					
					arrangeold = adao.findById(aID);
					Partkc partkc = new Partkc();
					partkc = partkcdao.findBypartId(arrangeold.getPartID());// 找到库存表中零件编号
					partkc.setPartkcnum(partkc.getPartkcnum()
							+ arrangeold.getChecknum());
					partkcdao.update(partkc);
					adao.deleteById(aID);
					
					out.print("<script language='javascript'>alert('成功删除维修安排信息！');window.location='arrange.jsp';</script>");
				} catch (Exception e) {
					out.print("<script language='javascript'>alert('成功删除维修安排信息！');window.location='arrange.jsp';</script>");
				}

			}
		}

	}
}
