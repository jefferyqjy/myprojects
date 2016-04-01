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

public class DeleteStudentCommentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("TUN");
		try {
			List<String> list = new ArrayList<String>();
			list.add("delete from pro_teacher_comment where id = " + id.trim());
			DaoManagerHandler.executeUpdate(list);
			request.setAttribute("Succ", "删除学生评价成功！");
		} catch (Exception e) {
			request.setAttribute("Error", "删除学生评价失败！");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqstudentcomment.jsp");
		rd.forward(request, response);
	}

}
