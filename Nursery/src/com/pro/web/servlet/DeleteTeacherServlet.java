package com.pro.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManagerHandler;

public class DeleteTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String teacherUserName = request.getParameter("TUN");
		try {
			List<String> list = new ArrayList<String>();
			list.add("delete from pro_teacher where user_name ='" + teacherUserName.trim() +"'");
			list.add("delete from pro_user where user_name ='" + teacherUserName.trim() +"'");
			list.add("delete from pro_user_role where user_name ='" + teacherUserName.trim() +"'");
			DaoManagerHandler.executeUpdate(list);
			request.setAttribute("Succ", "删除教师【" + teacherUserName +"】成功");
		} catch (Exception e) {
			request.setAttribute("Error", "删除教师【" + teacherUserName +"】失败");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqteacher.jsp");
		rd.forward(request, response);
	}

}
