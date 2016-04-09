package servlets.partinnerservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bean.Employee;
import bean.Part;
import bean.Partinner;
import bean.Partkc;
import dao.EmployeeDAO;
import dao.PartDAO;
import dao.PartinnerDAO;
import dao.PartkcDAO;
import util.RowCount;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "Partinner", description = "Partinner", urlPatterns = { "/Partinner" })
public class PartinnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PartinnerServlet() {
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
		List<Partinner> pis = new ArrayList<Partinner>();
		Partinner pi = new Partinner();
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
						queryStr="select p.iId, p.empId, p.company, p.pCost, p.pPrice, p.pNum, p.pId, p.date from partinner p ";
						countStr = "select count(*) from partinner p ";
					}
					if (operator.equals("0")) {
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						queryStr="select p.iId, p.empId, p.company, p.pCost, p.pPrice, p.pNum, p.pId, p.date from partinner p where 1=1 ";
						countStr = "select count(*) from partinner p where 1=1";
						if(StringUtils.isNotEmpty(queryValue)) {
							queryStr += (" and " + queryName + " = '" + queryValue + "'");
							countStr += (" and " + queryName + " = '" + queryValue + "'");
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
					PartinnerDAO pdao = new PartinnerDAO();
					pis = pdao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；
					if(pis.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='partinner.jsp';</script>");
						return;
					} else {
						for(Partinner m : pis){
							// 设置非数据库字段的值
							EmployeeDAO edao = new EmployeeDAO();
							Employee emp = edao.findByempID(m.getEmpId().toString());
							m.setEmpName(emp.getEmpName());
							
							PartDAO pdao2 = new PartDAO();
							Part part = pdao2.findById(m.getpId().toString());
							m.setpName(part.getPartname());
						}
					}
					
					request.setAttribute("partinners", pis);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找内配单失败！');window.location='partinner.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/partinner.jsp").forward(request, response);
				return;
			} else if(operator.equals("2") || operator.equals("3") || operator.equals("4")) {
				String cId = request.getParameter("cId");
				List<Partinner> pis1 = new ArrayList<Partinner>();
				
				try {
					PartinnerDAO rdao = new PartinnerDAO();
					pis1 = rdao.findAll();
					if(pis1 != null && pis1.size() > 0) {
						for(Partinner m : pis1) {
							EmployeeDAO edao = new EmployeeDAO();
							Employee emp = edao.findByempID(m.getEmpId().toString());
							m.setEmpName(emp.getEmpName());
							
							PartDAO pdao2 = new PartDAO();
							Part part = pdao2.findById(m.getpId().toString());
							m.setpName(part.getPartname());
						}
					}
				} catch(Exception e) {
					out.print("<script language='javascript'>alert('查找内配单失败！');window.location='partinner.jsp';</script>");
				}
				
				if(operator.equals("3")||operator.equals("4")) {	
					String iId = request.getParameter("iId");
					try {
						PartinnerDAO rdao = new PartinnerDAO();
						pi = rdao.findById(iId);
						EmployeeDAO edao = new EmployeeDAO();
						Employee emp = edao.findByempID(pi.getEmpId().toString());
						pi.setEmpName(emp.getEmpName());
						
						PartDAO pdao2 = new PartDAO();
						Part part = pdao2.findById(pi.getpId().toString());
						pi.setpName(part.getPartname());
					} catch(Exception e2) {
						out.print("<script language='javascript'>alert('查找内配单失败！');window.location='partinner.jsp';</script>");
					}
				}
				HttpSession  session=request.getSession();  
				if(operator.equals("3")) {	
					//找到预约单后，把该预约单的预约单编码放入session，修改该预约单信息再保存时可以从session中取出预约单编码。
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("iId", pi.getiId());
				} else if (operator.equals("4")) {
					session.setAttribute("operatorStr", "delete");
				} else {
					session.setAttribute("operatorStr", "add");
				}

				session.setAttribute("cId", cId);
				session.setAttribute("partinners", pis);//修改或添加预约单时，若输入信息通不过检测，需要返回维护预约单页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("partinner", pi);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/partinnerEdit.jsp").forward(request, response);
				return;
			}
		} 
		
		String operatorStr=request.getParameter("operatorStr");
		//operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		if(StringUtils.isNotEmpty(operatorStr)) {	
			if(operatorStr.equals("add") || operatorStr.equals("modify")) {
				Integer pNum = Integer.parseInt(request.getParameter("pNum"));
				String pId = request.getParameter("pId");
				PartkcDAO kcdao = new PartkcDAO();
				Partkc kc = null;
				try {
					kc = kcdao.findBypartId(pId);
					if(kc.getPartkcnum() < pNum) {
						out.print("<script language='javascript'>alert('零件【"+pNum+"】库存不足！无法销售！');window.location='partinner.jsp';</script>");
						return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
					out.print("<script language='javascript'>alert('添加/修改失败！');window.location='partinner.jsp';</script>");
					return;
				}
				pi = new Partinner();
				if(operatorStr.equals("modify")){
					pi.setiId((int)(request.getSession().getAttribute("iId")));
				}
				pi.setCompany(request.getParameter("company"));
				pi.setDate(request.getParameter("createTime"));
				pi.setEmpId(Integer.parseInt(request.getParameter("empId")));
				
				String cost = request.getParameter("pCost");
				BigDecimal pCost = new BigDecimal(cost);
				pCost.setScale(2);
				pi.setpCost(pCost);
				pi.setpId(request.getParameter("pId"));
				pi.setpNum(Integer.parseInt(request.getParameter("pNum")));
				
				String price = request.getParameter("pPrice");
				BigDecimal pPrice = new BigDecimal(price);
				pPrice.setScale(2);
				pi.setpPrice(pPrice);
				
				String date = request.getParameter("date");
				pi.setDate(date);
				PartinnerDAO rdao = new PartinnerDAO();
				try {
					if(operatorStr.equals("add")) {	
						rdao.insert(pi);
						out.print("<script language='javascript'>alert('成功添加内配单信息！');window.location='partinner.jsp';</script>");
					} else {
						rdao.update(pi);
						out.print("<script language='javascript'>alert('成功修改内配单信息！');window.location='partinner.jsp';</script>");
					}
					Double num = kc.getPartkcnum();
					Integer newNum = num.intValue() - pi.getpNum();
					kc.setPartkcnum(Double.valueOf(newNum));
					kcdao = new PartkcDAO();
					kcdao.update(kc);
				} catch(Exception e) {
					if(operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加内配单信息失败！');window.location='partinner.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改内配单信息失败！');window.location='partinner.jsp';</script>");
				}
			} else if (operatorStr.equals("delete")) {
				PartinnerDAO rdao = new PartinnerDAO();
				int iId = Integer.parseInt(request.getParameter("iId"));
		    	try {
		    		rdao.deleteById(iId);
	    			out.print("<script language='javascript'>alert('成功删除内配单信息！');window.location='partinner.jsp';</script>");
				} catch(Exception e) {
		    		out.print("<script language='javascript'>alert('删除内配单信息失败！');window.location='partinner.jsp';</script>");
				}
			}
		}
		
	}
	
}
