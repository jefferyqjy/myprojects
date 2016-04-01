package com.pro.web.servlet;

import java.io.IOException;
import java.sql.ResultSet;
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

public class InqStudentGradeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1068702977545577062L;
	
	public static final String [] subjects = {"", "语文", "数学", "英语", "美术", "音乐", "体育", "电脑",};
	public static final String [] grades = {"优", "良", "差"};
	public static final String [] terms = {"第一学期", "第二学期"};
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String bodycard = request.getParameter("student_bodycard");
		String subject = request.getParameter("subject");
		try {
			StringBuffer sql = new StringBuffer(" where 1=1 ");

			if (!CommonUtil.checkEmpty(bodycard)) {
				sql.append(" and student_bodycard like '%").append(bodycard.trim()).append("%'");
			}
			
			if(!CommonUtil.checkEmpty(subject)) {
				sql.append(" and subject = ").append(subject.trim());
			}

			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select count(*) totals from pro_student_grade" + sql.toString(),
					new TotalsConvert());
			String result = (String) output.getResults().get(0);
			if ("0".equals(result)) {
				request.setAttribute("Error", "没有匹配的学生成绩信息！");
			} else {
				int total = Integer.parseInt(result);
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from pro_student_grade" + sql,
						new StudentGradeConvert(), Integer.parseInt(curPage), 10);

				request.setAttribute("STUDENTGRADELIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), total, 10);
				request.setAttribute("control", control);

				request.setAttribute("ON_SELECT_STUDENT_BODYCARD", bodycard);
			}

		} catch (Exception e) {

		}
		String urlPara = "/Nursery/inqstudentgrade?student_bodycard=" + bodycard + "&subject=" + subject;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqstudentgrade.jsp");
		rd.forward(request, response);
	}

	public class StudentGradeConvert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("student_bodycard", rs.getString("student_bodycard"));
				map.put("year", rs.getString("year"));
				
				Integer subject = rs.getInt("subject");
				map.put("subject", subject.toString());
				String subjectInfo = subjects[subject];
				map.put("subjectInfo", subjectInfo);

				Integer grade = rs.getInt("grade");
				map.put("grade", grade.toString());
				String gradeInfo = grades[grade];
				map.put("gradeInfo", gradeInfo);
				
				Integer term = rs.getInt("term");
				map.put("term", term.toString());
				String termInfo = terms[term];
				map.put("termInfo", termInfo);
				
				System.out.println("map :" + map);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/*private String getDate(String src) {
			try {
				src = src.substring(0, 10);
				return src;
			} catch (Exception e) {
				return "";
			}
		}*/
	}
}
