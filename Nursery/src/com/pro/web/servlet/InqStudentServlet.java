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

public class InqStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String name = request.getParameter("student_name");
		String bodycard = request.getParameter("bodycard");
		try {
			StringBuffer sql = new StringBuffer("");
			boolean flag = false;
			if (!CommonUtil.checkEmpty(name)) {
				sql.append(" where ");
				name = name.trim();
				flag = true;
				sql.append(" name like '%").append(name).append("%'");
			}

			if (!CommonUtil.checkEmpty(bodycard)) {
				if (!flag) {
					bodycard = bodycard.trim();
					flag = true;
					sql.append(" where bodycard = '").append(bodycard).append("'");
				} else {
					sql.append(" and bodycard = '").append(bodycard).append("'");
				}
			}

			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select count(*) totals from pro_student" + sql.toString(),
					new TotalsConvert());
			String result = (String) output.getResults().get(0);
			if ("0".equals(result)) {
				request.setAttribute("Error", "没有匹配的学籍！");
			} else {
				int total = Integer.parseInt(result);
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from pro_student" +sql,
						new StudentConvert(), Integer.parseInt(curPage), 10);

				request.setAttribute("STUDENTLIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), total, 10);
				request.setAttribute("control", control);

				request.setAttribute("ON_SELECT_STUDENT_NAME", name);
				request.setAttribute("ON_SELECT_BODYCARD", bodycard);
			}

		} catch (Exception e) {

		}
		String urlPara = "/Nursery/inqstudent?student_name=" + name+ "&bodycard=" + bodycard;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqstudent.jsp");
		rd.forward(request, response);
	}

	public class StudentConvert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", rs.getString("name"));
				map.put("bodycard", rs.getString("bodycard"));
				map.put("birthday", getDate(rs.getString("birthday")));
				map.put("sex", rs.getString("sex"));
				map.put("nationality", rs.getString("nationality"));
				map.put("school", rs.getString("school"));
				map.put("nativeplace", rs.getString("nativeplace"));
				map.put("in_date", getDate(rs.getString("in_date")));
				map.put("out_date", getDate(rs.getString("out_date")));
				map.put("father_name", rs.getString("father_name"));
				map.put("father_work_address", rs.getString("father_work_address"));
				map.put("mother_name", rs.getString("mother_name"));
				map.put("mother_work_address", rs.getString("mother_work_address"));
				map.put("telephone", rs.getString("telephone"));
				map.put("description", rs.getString("description"));
				System.out.println("map :" + map);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		private String getDate(String src) {
			try {
				src = src.substring(0, 10);
				return src;
			} catch (Exception e) {
				return "";
			}
		}
	}
}
