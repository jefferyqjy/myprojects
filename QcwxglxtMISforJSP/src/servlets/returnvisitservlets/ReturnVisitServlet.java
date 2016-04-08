package servlets.returnvisitservlets;

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

import org.apache.commons.lang.StringUtils;

import bean.Employee;
import bean.ReturnVisit;
import dao.EmployeeDAO;
import dao.ReturnVisitDAO;
import util.RowCount;

/**
 * Servlet implementation class YuyueQueryServlet
 */
@WebServlet(name = "ReturnVisit", description = "ReturnVisit", urlPatterns = { "/ReturnVisit" })
public class ReturnVisitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int pageCount=7; 

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnVisitServlet() {
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
		List<ReturnVisit> returnvisits = new ArrayList<ReturnVisit>();
		ReturnVisitDAO dao = new ReturnVisitDAO();
		ReturnVisit rv = new ReturnVisit();
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
						queryStr="select r.rId, r.empId, r.cId, r.content, r.createTime from returnvisit r ";
						countStr = "select count(*) from returnvisit";
					}
					if(operator.equals("0")) {
						queryName = request.getParameter("queryName");
						queryValue = request.getParameter("queryValue");
						String cId = request.getParameter("cId");
						String empId = request.getParameter("empId");
						queryStr="select r.rId, r.empId, r.cId, r.content, r.createTime from returnvisit r where 1=1 ";
						countStr = "select count(*) from returnvisit r where 1=1 ";
						if(StringUtils.isNotEmpty(cId)) {
							queryStr += (" and r.cId = " + cId);
							countStr += (" and r.cId = " + cId);
						}
						if(StringUtils.isNotEmpty(empId)) {
							queryStr += (" and r.empId = " + empId);
							countStr += (" and r.empId = " + empId);
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
					returnvisits = dao.findByPage(queryStr, currentPage, pageCount); // 通过查询字符串、当前页和每页纪录数查找产品纪录；
					if(returnvisits.size()==0){
						out.print("<script language='javascript'>alert('未检索到符合条件的记录');window.location='returnvisit.jsp';</script>");
					} else {
						EmployeeDAO edao = new EmployeeDAO();
						for(ReturnVisit m : returnvisits){
							Integer empId = m.getEmpId();
							Employee e = edao.findById(empId);
							m.setEmpName(e.getEmpName());
						}
					}
					
					request.setAttribute("returnvisits", returnvisits);
					request.setAttribute("totalRows", totalRows);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("operator", operator);
					if (operator.equals("0")) {
						request.setAttribute("queryName", queryName);
						request.setAttribute("queryValue", queryValue);
					}

				} catch (Exception e2) {
					out.print("<script language='javascript'>alert('查找回访信息失败！');window.location='returnvisit.jsp';</script>");
				}
				getServletConfig().getServletContext().getRequestDispatcher("/returnvisit.jsp").forward(request, response);
				return;
			} else if(operator.equals("2") || operator.equals("3") || operator.equals("4")) {
				String cId = request.getParameter("cId");
				List<ReturnVisit> rv1 = new ArrayList<ReturnVisit>();
				
				try {
					ReturnVisitDAO rdao = new ReturnVisitDAO();
					rv1 = rdao.findAll();
					if(rv1 != null && rv1.size() > 0) {
						for(ReturnVisit m : rv1) {
							EmployeeDAO edao = new EmployeeDAO();
							Employee emp = edao.findByempID(m.getEmpId().toString());
							m.setEmpName(emp.getEmpName());
						}
					}
				} catch(Exception e) {
					out.print("<script language='javascript'>alert('查找回访信息失败！');window.location='returnvisit.jsp';</script>");
				}
				if(operator.equals("3")||operator.equals("4")) {	
					String rId = request.getParameter("rId");
					try {
						ReturnVisitDAO rdao = new ReturnVisitDAO();
						rv = rdao.findById(rId);
						EmployeeDAO edao = new EmployeeDAO();
						Employee emp = edao.findByempID(rv.getEmpId().toString());
						rv.setEmpName(emp.getEmpName());
					} catch(Exception e2) {
						out.print("<script language='javascript'>alert('查找回访信息失败！');window.location='returnvisit.jsp';</script>");
					}
				}
				HttpSession  session=request.getSession();  
				if(operator.equals("3")) {	
					//找到预约单后，把该预约单的预约单编码放入session，修改该预约单信息再保存时可以从session中取出预约单编码。
					session.setAttribute("operatorStr", "modify");
					session.setAttribute("rId", rv.getrId());
				} else if (operator.equals("4")) {
					session.setAttribute("operatorStr", "delete");
				} else {
					session.setAttribute("operatorStr", "add");
				}

				session.setAttribute("cId", cId);
				session.setAttribute("returnvisits", rv1);//修改或添加预约单时，若输入信息通不过检测，需要返回维护预约单页面，必须把类别信息放到session，否则会丢失
				session.setAttribute("returnvisit", rv);
				request.setAttribute("operator", operator);
				getServletConfig().getServletContext().getRequestDispatcher("/returnvisitEdit.jsp").forward(request, response);
				return;
			}
		}
		
		String operatorStr=request.getParameter("operatorStr");
		//operatorStr!=null，说明请求从productEdit.jsp来，准备进行数据的插入、删除和修改
		if(StringUtils.isNotEmpty(operatorStr)) {	
			if(operatorStr.equals("add") || operatorStr.equals("modify")) {
				ReturnVisit rv1 = new ReturnVisit();
				if(operatorStr.equals("modify")){
					rv1.setrId((int)(request.getSession().getAttribute("rId")));
				}
				rv1.setcId(Integer.parseInt(request.getParameter("cId")));
				rv1.setEmpId(Integer.parseInt(request.getParameter("empId")));
				rv1.setCreateTime(request.getParameter("createTime"));
				rv1.setContent(request.getParameter("content"));
				
				ReturnVisitDAO rdao = new ReturnVisitDAO();
				try {
					if(operatorStr.equals("add")) {	
						rdao.insert(rv1);
						out.print("<script language='javascript'>alert('成功添加回访信息！');window.location='returnvisit.jsp';</script>");
					} else {
						rdao.update(rv1);
						out.print("<script language='javascript'>alert('成功修改预约单信息！');window.location='returnvisit.jsp';</script>");
					}
				} catch(Exception e) {
					if(operatorStr.equals("add"))
						out.print("<script language='javascript'>alert('添加预约单信息失败！');window.location='returnvisit.jsp';</script>");
					else
						out.print("<script language='javascript'>alert('修改预约单信息失败！');window.location='returnvisit.jsp';</script>");
				}
			} else if (operatorStr.equals("delete")) {
				ReturnVisitDAO rdao = new ReturnVisitDAO();
				int rId = Integer.parseInt(request.getParameter("rId"));
		    	try {
		    		rdao.deleteById(rId);
	    			out.print("<script language='javascript'>alert('成功删除回访信息！');window.location='returnvisit.jsp';</script>");
				} catch(Exception e) {
		    		out.print("<script language='javascript'>alert('删除回访信息失败！');window.location='returnvisit.jsp';</script>");
				}
			}
		}
	}
}
