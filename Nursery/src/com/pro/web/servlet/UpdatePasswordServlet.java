package com.pro.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.util.CommonUtil;

public class UpdatePasswordServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String old_password = request.getParameter("old_password");
		String new_password = request.getParameter("new_password");
		String confim_password = request.getParameter("confim_password");
		String userName = (String)request.getSession().getAttribute("PRO_USER_NAME");
		String error = null;
		try {
			if (CommonUtil.checkEmpty(old_password)
					||
				CommonUtil.checkEmpty(new_password)
					||
				CommonUtil.checkEmpty(confim_password) 
					||
				!confim_password.equals(new_password)	) {
				error = "输入有误！请重新输入！";
			} else {
				
				StringBuffer sql = new StringBuffer("update pro_user set ");
				sql.append("user_password ='").append(new_password).append("' where ");
				sql.append(" user_name ='").append(userName).append("' and user_password ='");
				sql.append(old_password).append("'");
				DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
				if (!output.isbSuccess()) {
					error = "修改密码失败，入库异常！";
				} else {
					request.setAttribute("Succ", "修改【" + userName+"】密码成功");
				}
			}
		}catch (Exception e) {
			error = "修改密码失败，未知异常！";
		}
		if (error != null){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/updatepassword.jsp");
		rd.forward(request, response);	
	}

}
