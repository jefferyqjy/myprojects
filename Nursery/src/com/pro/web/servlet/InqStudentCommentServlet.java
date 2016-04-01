package com.pro.web.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.pro.db.InquireConvert;
import com.util.CommonUtil;
import com.util.PageControl;
import com.util.TotalsConvert;

public class InqStudentCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6579638071051837369L;
	
	public static final String [] grades = {"��", "��", "��"};
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String teacherUserName = request.getParameter("teacher_user_name");
		String studentBodycard = request.getParameter("student_bodycard");
		String grade = request.getParameter("grade");
		try {
			StringBuffer sql = new StringBuffer("");
			sql.append(" where 1=1 ");
			if (!CommonUtil.checkEmpty(teacherUserName)) {
				teacherUserName = teacherUserName.trim();
				sql.append(" and teacher_user_name like '%").append(teacherUserName).append("%'");
			}
			
			if (!CommonUtil.checkEmpty(studentBodycard)) {
				studentBodycard = studentBodycard.trim();
				sql.append(" and student_bodycard like '%").append(studentBodycard).append("%'");
			}
			
			if(!CommonUtil.checkEmpty(grade)) {
				sql.append(" and grade = ").append(grade);
			}

			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select count(*) totals from pro_student_comment " + sql.toString(),
					new TotalsConvert());
			String result = (String) output.getResults().get(0);
			if ("0".equals(result)) {
				request.setAttribute("Error", "û��ƥ���ѧ�����ۣ�");
			} else {
				int total = Integer.parseInt(result);
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from pro_student_comment" +sql,
						new _convert(), Integer.parseInt(curPage), 10);

				request.setAttribute("STUDENTCOMMENTLIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), total, 10);
				request.setAttribute("control", control);

				request.setAttribute("ON_SELECT_TEACHER_USER_NAME", teacherUserName);
				request.setAttribute("ON_SELECT_STUDENT_BODYCARD", studentBodycard);
			}

		} catch (Exception e) {

		}
		String urlPara = "/Nursery/inqstudentcomment?teacher_user_name=" + teacherUserName + "&grade=" + grade + "&student_bodycard=" + studentBodycard;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqstudentcomment.jsp");
		rd.forward(request, response);
	}

	class _convert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("teacher_user_name", rs.getString("teacher_user_name"));
				map.put("student_bodycard", rs.getString("student_bodycard"));
				map.put("content", rs.getString("content"));
				Date create_time = (Date)rs.getDate("create_time");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String create_time_str = df.format(create_time);
				map.put("create_time", create_time_str);
				String grade = (String)rs.getString("grade");
				int sv = Integer.parseInt(grade);
				map.put("grade", grades[sv]);
				return map;
			} catch (Exception e) {
				return null;
			}
		}
	}
}