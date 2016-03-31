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

public class UpdateTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String teacherUserName = request.getParameter("teacher_user_name");
		String teacherName = request.getParameter("teacher_name");
		String subject = request.getParameter("teacher_subject");
		String sex = request.getParameter("teacher_sex");
		String age = request.getParameter("teacher_age");
		
		String error = null;
		try {
			if (CommonUtil.checkEmpty(teacherUserName)
					||
				CommonUtil.checkEmpty(teacherName)
					||
				CommonUtil.checkEmpty(age) 
					||
				!CommonUtil.isNum(age)	) {
				error = "输入有误！请重新输入！";
			} else {
				teacherUserName = teacherUserName.trim();
				teacherName = teacherName.trim();
				subject = subject.trim();
				sex = sex.trim();
				age = age.trim();
				StringBuffer sql = new StringBuffer("update pro_teacher set ");
				sql.append("name ='").append(teacherName).append("', ");
				sql.append("age =").append(age).append(", ");
				sql.append("sex ='").append(sex).append("',");
				sql.append("subject =").append(subject).append(" where user_name ='");
				sql.append(teacherUserName).append("'");
				DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
				if (!output.isbSuccess()) {
					error = "修改教师失败，入库异常！";
				} else {
					request.setAttribute("Succ", "修改教师【" + teacherUserName+"】成功");
				}
			}
		}catch (Exception e) {
			error = "修改教师失败，未知异常！";
		}
		if (error != null){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/updateteacher.jsp");
		rd.forward(request, response);	
	}

}
