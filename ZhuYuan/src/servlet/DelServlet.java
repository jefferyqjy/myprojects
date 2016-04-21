package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.InsertUpdateDelBean;

public class DelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DelServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=gb2312");
		request.setCharacterEncoding("gb2312");
		
		String sql = "";
		String str = "";
		InsertUpdateDelBean ib = new InsertUpdateDelBean();
		
		String admin = request.getParameter("admin");
		String users = request.getParameter("users");
		String ward = request.getParameter("ward");
		String sickbed = request.getParameter("sickbed");
		String patient = request.getParameter("patient");
		String symptom = request.getParameter("symptom");
		String prescribe = request.getParameter("prescribe");
		String inspect = request.getParameter("inspect");
		String drug = request.getParameter("drug");
		String stock = request.getParameter("stock");
		
		if(admin != null && !admin.equals("")){
			sql = "delete from admin where id="+admin;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/systemuser.jsp";			
		}
		if(users != null && !users.equals("")){
			sql = "delete from users where id="+users;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/users.jsp";			
		}
		if(ward != null && !ward.equals("")){
			sql = "delete from sickbed where ward="+ward;
			ib.insertANDupdateANDdel(sql);
			sql = "delete from ward where id="+ward;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/ward.jsp";			
		}
		if(sickbed != null && !sickbed.equals("")){
			sql = "delete from sickbed where id="+sickbed;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/ward.jsp";			
		}
		if(patient != null && !patient.equals("")){
			sql = "delete from inspect where patient="+patient;
			ib.insertANDupdateANDdel(sql);
			sql = "delete from prescribe where patient="+patient;
			ib.insertANDupdateANDdel(sql);
			sql = "delete from symptom where patient="+patient;
			ib.insertANDupdateANDdel(sql);
			sql = "delete from patient where id="+patient;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/patient.jsp";			
		}
		if(symptom != null && !symptom.equals("")){
			sql = "delete from symptom where id="+symptom;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/symptom.jsp";			
		}
		if(prescribe != null && !prescribe.equals("")){
			sql = "delete from prescribe where id="+prescribe;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/prescribe.jsp";			
		}
		if(inspect != null && !inspect.equals("")){
			sql = "delete from inspect where id="+inspect;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/inspect.jsp";			
		}
		if(drug != null && !drug.equals("")){
			sql = "delete from prescribe where drug="+drug;
			ib.insertANDupdateANDdel(sql);
			sql = "delete from stock where drug="+drug;
			ib.insertANDupdateANDdel(sql);
			sql = "delete from drug where id="+drug;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/drug.jsp";			
		}
		if(stock != null && !stock.equals("")){
			sql = "delete from stock where id="+stock;
			ib.insertANDupdateANDdel(sql);
			str = "/admin/stock.jsp";			
		}
		
		request.getRequestDispatcher(str).forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
