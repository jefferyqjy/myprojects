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

public class AddStudentGradeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String [] subjects = {"", "����", "��ѧ", "Ӣ��", "����", "����", "����", "����",};
	
	public static final String [] terms = {"��һѧ��", "�ڶ�ѧ��"};
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String bodycard = request.getParameter("student_bodycard");
		String grade = request.getParameter("grade");
		String subject = request.getParameter("subject");
		String year = request.getParameter("year");
		String term = request.getParameter("term");
		String error = "";
		if (CommonUtil.checkEmpty(bodycard)) {
			error += "���֤����Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(grade)) {
			error += "�ȵز���Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(subject)) {
			error += "��Ŀ����Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(year)) {
			error += "ѧ�겻��Ϊ�գ�<br>";
		}
		
		if (CommonUtil.checkEmpty(term)) {
			error += "ѧ�ڲ���Ϊ�գ�<br>";
		}
		
		if (error.equals("")) {
			try {
				StringBuffer validateSql = new StringBuffer("pro_student_grade where 1=1 ");
					validateSql.append("student_bodycard = '").append(bodycard.trim()).append("'")
					.append(" and year = ").append(year.trim())
					.append(" and term = ").append(term.trim())
					.append(" and subject = ").append(subject.trim());
				int totals = DaoManager.executeTotalsQuery(validateSql.toString());
				if (totals == 0) {
					StringBuffer sql = new StringBuffer();
					sql.append("insert into pro_student_grade (student_bodycard, subject, grade, year, term) values(");
					sql.append("'").append(bodycard).append("',");
					sql.append("'").append(subject).append("',");
					sql.append("'").append(grade).append("',");
					sql.append("'").append(year).append("',");
					sql.append("'").append(term).append("')");
					
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error += "���ѧ��" + bodycard + "���ɼ���Ϣʧ�ܣ�����쳣��<br>";
					} else {
						request.setAttribute("Succ", "���ѧ��" + bodycard+"���ɼ��ɹ�");
					}
				} else {
					int termInt = Integer.valueOf(term);
					int subjectInt = Integer.valueOf(subject);
					error += "ѧ��" + bodycard+ "��" + year+ terms[termInt]+ subjects[subjectInt]+ "�ɼ���¼�룡��<br>";
				}
			}catch (Exception e) {
				error += "���ѧ����Ϣʧ�ܣ�δ֪�쳣��<br>";
			}
		
		} 
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addstudentgrade.jsp");
		rd.forward(request, response);
	}

	
}
