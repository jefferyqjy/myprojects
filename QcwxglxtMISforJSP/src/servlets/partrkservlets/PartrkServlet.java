package servlets.partrkservlets;

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
 * Servlet implementation class PartrkServlet
 */
@WebServlet(name = "Partrk", description = "Partrk", urlPatterns = { "/Partrk" })
public class PartrkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartrkServlet() {
		super();
		// TODO Auto-generated constructor stub
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
		String p_operator = request.getParameter("p_operator");
		List<Partrk> partrks = new ArrayList<Partrk>();
		PartrkDAO dao = new PartrkDAO();
		Partrk partrk = new Partrk();
		List<Partrkmingxi> partrkmingxis;
		String message="";
		HttpSession session=request.getSession();
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
	
		if (p_operator != null) {
			if (p_operator.equals("1") || p_operator.equals("0")) // 查询全部或查询部分
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
					if (p_operator.equals("1")) // 查询全部
					{
						queryStr = "select partrkID,partrktotal,partrk.supID,supName,partrk.empID,empName,rkdate from partrk,employee,supplier where partrk.empID=employee.empID and supplier.supID=partrk.supID ";
						countStr = "select count(partrkID) from partrk";
					} else // 查询部分
					{
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						if (!queryName.equals("partrkID")) // 纠正中文乱码
						{
							ChangeToGBK chGBK = new ChangeToGBK();
							queryValue = chGBK.change("queryValue", request);
						}

						queryStr = "select partrkID,partrktotal,partrk.supID,supplier.supName,partrk.empID,employee.empName,rkdate from partrk,employee,supplier where partrk.empID=employee.empID and supplier.supID=partrk.supID and  "
								+ queryName + "='" + queryValue + "'";
						countStr = "select count(partrkID) from partrk,employee,supplier where partrk.empID=employee.empID and supplier.supID=partrk.supID and  "
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
					
					partrks = dao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；
					
					if(partrks.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='partrk.jsp';</script>");
						return;
					}
					
					request.setAttribute("partrks", partrks);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("p_operator", p_operator);
					if (p_operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					message="查找信息失败";
					}
				request.setAttribute("message", message);
				getServletConfig().getServletContext().getRequestDispatcher("/partrk.jsp").forward(request, response);
				return;
			} else if (p_operator.equals("3") || p_operator.equals("4")) // 修改或删除某个订单之前，先找到该订单，然后转到编辑页面。
			{
				session.removeAttribute("partrkmingxis");// 编辑某个订单之前，先移除session中原有订单
				session.removeAttribute("partrk");// 编辑某个订单之前，先移除session中原有订单明细
				EmployeeDAO edao = new EmployeeDAO();
				SupplierDAO sdao = new SupplierDAO();
				CategoryDAO cdao = new CategoryDAO();
				PartrkDAO pdao = new PartrkDAO();
				List<Employee> employees = new ArrayList<Employee>();
				List<Supplier> suppliers = new ArrayList<Supplier>();
				List<Category> categorys = new ArrayList<Category>();
				partrkmingxis = new ArrayList<Partrkmingxi>();
				try {
					employees = edao.findAll();
					suppliers = sdao.findAll();
					categorys = cdao.findAll();
					String partrkID = request.getParameter("partrkID");
					partrk = pdao.findById(partrkID);
					pdao = new PartrkDAO();
					partrkmingxis = pdao.findPartrkmingxi(partrkID);
				} catch (Exception e) {
					e.printStackTrace();
					message = "获取客户信息列表、职员信息列表、类别信息列表及订单信息失败！";
				}

				session.setAttribute("employees", employees);
				session.setAttribute("suppliers", suppliers);
				session.setAttribute("categorys", categorys);
				session.setAttribute("partrk", partrk);
				session.setAttribute("partrkmingxis", partrkmingxis);

				request.setAttribute("message", message);
				if (p_operator.equals("3"))
					session.setAttribute("p_operatorStr", "modify");

				else
					session.setAttribute("p_operatorStr", "delete");
				getServletConfig().getServletContext().getRequestDispatcher("/partrkmingxi.jsp").forward(request, response);
			}
		}
	}
}