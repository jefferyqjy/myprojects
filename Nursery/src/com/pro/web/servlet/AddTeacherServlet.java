package com.pro.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManager;
import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.util.CommonUtil;

public class AddTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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
				error = "�����������������룡";
			} else {
				teacherUserName = teacherUserName.trim();
				teacherName = teacherName.trim();
				subject = subject.trim();
				sex = sex.trim();
				age = age.trim();
				
				int totals = DaoManager.executeTotalsQuery(" pro_teacher where user_name = '" + teacherUserName +"'");
				if (totals > 0) {
					error = "��ʦ��¼���ظ������������룡";
				} else {
					StringBuffer sql = new StringBuffer("insert into pro_user (user_name, user_password) values ('");
					sql.append(teacherUserName).append("','admin')");
					DaoManagerHandler.executeUpdate(sql.toString());
					
					sql = new StringBuffer("insert into pro_user_role (user_name, role_name) values ('");
					sql.append(teacherUserName).append("','teacher')");
					DaoManagerHandler.executeUpdate(sql.toString());
					
					sql = new StringBuffer("insert into pro_teacher (user_name, name, age, subject, sex) values (");
					sql.append("'").append(teacherUserName).append("',");
					sql.append("'").append(teacherName).append("',");
					sql.append(age).append(",");
					sql.append(subject).append(",");
					sql.append("'").append(sex).append("')");
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error = "��ӽ�ʦʧ�ܣ�����쳣��";
					} else {
						request.setAttribute("Succ", "��ӽ�ʦ��" + teacherUserName+"���ɹ�, ��ʼ���� admin");
					}
				}
			}
		} catch (Exception e) {
			error = "��ӽ�ʦʧ�ܣ�δ֪�쳣��";
		}
		if (error != null){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addteacher.jsp");
		rd.forward(request, response);
	}
}
