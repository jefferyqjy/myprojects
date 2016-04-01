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
import com.util.IdcardUtil;

public class AddStudentGradeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public static final String [] subjects = {"", "语文", "数学", "英语", "美术", "音乐", "体育", "电脑",};
	
	public static final String [] terms = {"第一学期", "第二学期"};
	
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
			error += "身份证不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(grade)) {
			error += "等地不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(subject)) {
			error += "科目不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(year)) {
			error += "学年不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(term)) {
			error += "学期不能为空！<br>";
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
						error += "添加学生【" + bodycard + "】成绩信息失败，入库异常！<br>";
					} else {
						request.setAttribute("Succ", "添加学生【" + bodycard+"】成绩成功");
					}
				} else {
					int termInt = Integer.valueOf(term);
					int subjectInt = Integer.valueOf(subject);
					error += "学生【" + bodycard+ "】" + year+ terms[termInt]+ subjects[subjectInt]+ "成绩已录入！！<br>";
				}
			}catch (Exception e) {
				error += "添加学生信息失败，未知异常！<br>";
			}
		
		} 
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addstudentgrade.jsp");
		rd.forward(request, response);
	}

	
}
