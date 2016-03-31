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

public class InqTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static final String [] subjects = {"", "语文", "数学", "英语", "美术", "音乐", "体育", "电脑",};
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String teacherUserName = request.getParameter("teacher_user_name");
		String teacherName = request.getParameter("teacher_name");
		String subject = request.getParameter("teacher_subject");
		try {
			StringBuffer sql = new StringBuffer("");
			boolean flag = false;
			if (!CommonUtil.checkEmpty(teacherUserName)) {
				sql.append(" where ");
				teacherUserName = teacherUserName.trim();
				flag = true;
				sql.append(" user_name like '%").append(teacherUserName).append("%'");
			}

			if (!CommonUtil.checkEmpty(teacherName)) {
				if (!flag) {
					teacherName = teacherName.trim();
					flag = true;
					sql.append(" where name like '%").append(teacherName).append("%'");
				} else {
					sql.append(" and name like '%").append(teacherName).append("%'");
				}
			}

			if (!"0".equals(subject)) {
				if (!flag) {
					sql.append(" where subject = '").append(subject).append("'");
				} else {
					sql.append(" and subject = '").append(subject).append("'");
				}
			}

			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select count(*) totals from pro_teacher" + sql.toString(),
					new TotalsConvert());
			String result = (String) output.getResults().get(0);
			if ("0".equals(result)) {
				request.setAttribute("Error", "没有匹配的教师！");
			} else {
				int total = Integer.parseInt(result);
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from pro_teacher" +sql,
						new _convert(), Integer.parseInt(curPage), 10);

				request.setAttribute("TEACHERLIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), total, 10);
				request.setAttribute("control", control);

				request.setAttribute("ON_SELECT_TEACHER_USER_NAME", teacherUserName);
				request.setAttribute("ON_SELECT_TEACHER_NAME", teacherName);
				request.setAttribute("ON_SELECT_TEACHER_SUBJECT", subject);
			}

		} catch (Exception e) {

		}
		String urlPara = "/Nursery/inqteacher?teacher_user_name=" + teacherUserName+ "&teacher_name=" + teacherName+"&teacher_subject="  + subject;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqteacher.jsp");
		rd.forward(request, response);
	}

	class _convert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("user_name", rs.getString("user_name"));
				map.put("name", rs.getString("name"));
				map.put("age", rs.getString("age"));
				String subject = (String)rs.getString("subject");
				int sv = Integer.parseInt(subject);
				map.put("subject", subjects[sv]);
				map.put("subjectv", subject);
				map.put("sex", rs.getString("sex"));
				return map;
			} catch (Exception e) {
				return null;
			}
		}
	}
}
