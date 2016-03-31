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

import com.pro.conn.DaoManager;
import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.pro.db.InquireConvert;
import com.util.CommonUtil;
import com.util.PageControl;

public class InqHealthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("student_name");
		String bodycard = request.getParameter("bodycard");
		try {
			StringBuffer sql = new StringBuffer("(select * from pro_student");
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
			sql.append(") A right join student_health B on A.bodycard = B.bodycard order by A.bodycard");
			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			int totals = DaoManager.executeTotalsQuery(sql.toString());
			if (totals == 0) {
				request.setAttribute("Error", "û��ƥ��Ľ�����¼��");
			} else {
				DaoHelperOutput	output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from " +sql,
						new HealthConvert(), Integer.parseInt(curPage), 10);

				request.setAttribute("HEALTHLIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), totals, 10);
				request.setAttribute("control", control);

				request.setAttribute("ON_SELECT_STUDENT_NAME", name);
				request.setAttribute("ON_SELECT_BODYCARD", bodycard);
			}

		} catch (Exception e) {

		}
		String urlPara = "/Nursery/inqstudent?student_name=" + name+ "&bodycard=" + bodycard;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqhealth.jsp");
		rd.forward(request, response);
	}

	class HealthConvert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("name", rs.getString("name"));
				map.put("bodycard", rs.getString("bodycard"));
				map.put("sex", rs.getString("sex"));
				map.put("in_date", getDate(rs.getString("in_date")));
				map.put("height", rs.getString("height"));
				map.put("age", rs.getString("age"));
				map.put("weight", rs.getString("weight"));
				map.put("left_sight", rs.getString("left_sight"));
				map.put("right_sight", rs.getString("right_sight"));
				map.put("check_date", getDate(rs.getString("check_date")));
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
