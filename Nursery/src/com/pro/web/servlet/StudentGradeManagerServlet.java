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

public class StudentGradeManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String target = "";
		if ("view".equals(type)) {
			target = "/console/viewstudentgrade.jsp";
			view(request, id);
		} else if ("delete".equals(type)) {
			target = "/console/inqstudentgrade.jsp";
			delete(request, id);
		} else if ("viewupdate".equals(type)) {
			target = "/console/updatestudentgrade.jsp";
			view(request, id);
		} else if ("update".equals(type)){
			target = "/console/inqstudentgrade.jsp";
			update(request, id);
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	private void view(HttpServletRequest request, String id) {
		try {
			String sql = "select * from pro_student_grade where id = " + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql,
					new InqStudentGradeServlet() .new StudentGradeConvert());
			request.setAttribute("STUDENTGRADEINFO", output.getResults().get(0));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void delete(HttpServletRequest request, String id) {
		try {
			String sql = "delete from pro_student_grade where id = " + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql);
			if (output.isbSuccess()) {
				request.setAttribute("Succ", "ɾ��ѧ���ɼ��ɹ���");
			} else {
				request.setAttribute("Error", "ɾ��ѧ���ɼ�ʧ�ܣ�");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void update(HttpServletRequest request, String id) {
		try {
			String grade = request.getParameter("grade");
			String error = "";
			
			if (CommonUtil.checkEmpty(grade)) {
				error += "�ȵز���Ϊ�գ�<br>";
			}
			
			if (error.equals("")) {
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("update pro_student_grade set ")
						.append("grade = '").append(grade).append("' ")
						.append("where id = ").append(id);
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error += "�޸�ѧ���ɼ�ʧ�ܣ�����쳣��<br>";
					} else {
						request.setAttribute("Succ", "�޸�ѧ���ɼ��ɹ���");
					}
				}catch (Exception e) {
					error += "�޸�ѧ���ɼ�ʧ�ܣ�δ֪�쳣��<br>";
				}

			}
			if (!error.equals("")){
				request.setAttribute("Error", error);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	public static void main(String args[]) {
		String a = "2011-02-01";
		a =a.replaceAll("-", "");
		System.out.println(a);
	}
}
