package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;

/**
 * Servlet implementation class PasswordupdateServlet
 */
@WebServlet("/PasswordupdateServlet")
public class PasswordupdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public PasswordupdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String Password = request.getParameter("userPassword");//定义userPassword,通过get方法从Parameter中取得userPassword的值
		String Password2 = request.getParameter("userPassword2");
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。

		//创建session对象
		HttpSession  session=request.getSession();  
		//验证是否正常登录
		if(session.getAttribute("userID")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='login.jsp';</script>");
			out.flush();out.close();return;
		}

		String type=session.getAttribute("type").toString();//吧session取到的type的值转换成String格式再赋值给type
		if(type.equals("admin"))//系统用户
		{
			try
			{	  AdminDAO adminDAO=new AdminDAO();
				if(adminDAO.CheckPassword(Integer.parseInt(session.getAttribute("userID").toString()), Password))
				//调用CheckPassword方法验证session中的userID值和密码是否和数据库的一样//获取到的是一个对象obj.所以要转换.
				{
					adminDAO.Update(Integer.parseInt(session.getAttribute("userID").toString()),Password2);	
					//通过userID更新密码
					out.print("<script language='javascript'>alert('修改成功！');window.location='passwordupdate.jsp';</script>");
					out.flush();out.close();return;
				}else{
					out.print("<script language='javascript'>alert('原密码不正确！');window.location='passwordupdate.jsp';</script>");
				}
			} catch (Exception e) {
				out.print("<script language='javascript'>alert('修改不成功！');window.location='passwordupdate.jsp';</script>");
				e.printStackTrace();
			}
		}
		else if(type.equals("employee"))//员工身份
		{
			try
			{	  
				if(new EmployeeDAO().CheckPassword(Integer.parseInt(session.getAttribute("userID").toString()), Password)){
					new EmployeeDAO().Update(Integer.parseInt(session.getAttribute("userID").toString()),Password2);							
					out.print("<script language='javascript'>alert('修改成功！');window.location='passwordupdate.jsp';</script>");
					out.flush();out.close();return;
				}else{
					out.print("<script language='javascript'>alert('原密码不正确！');window.location='passwordupdate.jsp';</script>");
				}
			} catch (Exception e) {
				out.print("<script language='javascript'>alert('修改不成功！');window.location='passwordupdate.jsp';</script>");
				e.printStackTrace();
			}
		}
		
		if(type.equals("customer"))//客户
		{
			try
			{	  
				if(new CustomerDAO().CheckPassword(Integer.parseInt(session.getAttribute("userID").toString()), Password)){
					new CustomerDAO().UpdatePassword(Integer.parseInt(session.getAttribute("userID").toString()),Password2);							
					out.print("<script language='javascript'>alert('修改成功！');window.location='passwordupdate_cus.jsp';</script>");
					out.flush();out.close();return;
				}else{
					out.print("<script language='javascript'>alert('原密码不正确！');window.location='passwordupdate_cus.jsp';</script>");
				}
			} catch (Exception e) {
				out.print("<script language='javascript'>alert('修改不成功！');window.location='passwordupdate_cus.jsp';</script>");
				e.printStackTrace();
			}
		}
	}
}
