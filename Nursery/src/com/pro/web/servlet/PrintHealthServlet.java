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
import com.util.PageControl;

public class PrintHealthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");

		try {
			int totals = DaoManager.executeTotalsQuery("student_health");
			if (totals == 0) {
				request.setAttribute("Error", "没有匹配的健康记录！");
			} else {
				String sql = "select A.id, A.bodycard, A.height, A.weight, A.age, A.check_date, A.left_sight, A.right_sight, A.description, B.name, B.sex ,B.in_date from student_health A, pro_student B where A.bodycard = B.bodycard order by A.check_date desc";
				String curPage = request.getParameter("curpage");
				if (null == curPage) {
					curPage = "1";
				}
				DaoHelperOutput	output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql,
						new HealthConvert(), Integer.parseInt(curPage), 100);

				request.setAttribute("HEALTHLIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), totals, 10);
				request.setAttribute("control", control);
			}

		} catch (Exception e) {

		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/printhealth.jsp");
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
				map.put("description", rs.getString("description"));
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
