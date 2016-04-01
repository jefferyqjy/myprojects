package com.pro.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.util.CommonUtil;

public class AddStudentCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2045031191244881602L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String teacher_user_name = request.getParameter("teacher_user_name");
		String student_bodycard = request.getParameter("student_bodycard");
		String content = request.getParameter("content");
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_time = df.format(date);
		String grade = request.getParameter("grade");

		String error = "";
		if (CommonUtil.checkEmpty(teacher_user_name)) {
			error += "������ʦ����Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(student_bodycard)) {
			error += "ѧ�����֤����Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(grade)) {
			error += "���۵ȵز���Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(content)) {
			error += "�������ݲ���Ϊ�գ�<br>";
		}

		if (error.equals("")) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("insert into pro_student_comment(student_bodycard, teacher_user_name, grade, content, create_time) values(")
				.append("'").append(student_bodycard).append("',")
				.append("'").append(teacher_user_name).append("',")
				.append("'").append(grade).append("',")
				.append("'").append(content).append("',")
				.append("'").append(create_time).append("')");

				DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
				if (!output.isbSuccess()) {
					error += "���ѧ������ʧ�ܣ�����쳣��<br>";
				} else {
					request.setAttribute("Succ", "���ѧ�����۳ɹ���");
				}

			}catch (Exception e) {
				error += "���ѧ������ʧ�ܣ�δ֪�쳣��<br>";
			}

		}
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addstudentcomment.jsp");
		rd.forward(request, response);
	}


}
