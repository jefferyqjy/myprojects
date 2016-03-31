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

public class HealthManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String target = "";
		if ("view".equals(type)) {
			target = "/console/viewhealth.jsp";
			view(request, id);
		} else if ("delete".equals(type)) {
			target = "/console/inqhealth.jsp";
			delete(request, id);
		} else if ("viewupdate".equals(type)) {
			target = "/console/updatehealth.jsp";
			view(request, id);
		} else if ("update".equals(type)){
			target = "/console/inqhealth.jsp";
			update(request, id);
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	private void view(HttpServletRequest request, String id) {
		try {
			String sql = "select A.id, A.bodycard, A.height, A.weight, A.age, A.check_date, A.left_sight, A.right_sight, A.description, B.name, B.sex from student_health A, pro_student B where id =" + id +" and A.bodycard = B.bodycard";
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql,
					new HealthConvert());
			request.setAttribute("HEALTHINFO", output.getResults().get(0));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void delete(HttpServletRequest request, String id) {
		try {
			String sql = "delete from student_health where id =" + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql);
			if (output.isbSuccess()) {
				request.setAttribute("Succ", "ɾ��������¼��" + id +"���ɹ�");
			} else {
				request.setAttribute("Error", "ɾ��������¼��" + id +"��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void update(HttpServletRequest request, String id) {
		try {
			String check_date = request.getParameter("check_date");
			String height = request.getParameter("height");
			String age = request.getParameter("age");
			String weight = request.getParameter("weight");
			String left_sight = request.getParameter("left_sight");
			String right_sight = request.getParameter("right_sight");
			String description = request.getParameter("description");

			String error = "";

			if (!CommonUtil.checkEmpty(check_date)) {
				check_date = check_date.trim().replaceAll("-", "");
				try {
					Integer.parseInt(check_date);
					if (check_date.length() != 8) {
						throw new Exception();
					}
					check_date = check_date.substring(0,4) + "-" +
					check_date.substring(4,6)+"-" + check_date.substring(6,8);
				} catch (Exception e) {
					error += "������ڸ�ʽ���ԣ�<br>";
				}
			} else {
				check_date = "";
			}

			if (CommonUtil.checkEmpty(age)) {
				error += "���䲻��Ϊ�գ�<br>";
			}

			if (CommonUtil.checkEmpty(height)) {
				error += "��߲���Ϊ�գ�<br>";
			}

			if (CommonUtil.checkEmpty(weight)) {
				error += "���ز���Ϊ�գ�<br>";
			}

			if (CommonUtil.checkEmpty(left_sight)) {
				error += "����������Ϊ�գ�<br>";
			}

			if (CommonUtil.checkEmpty(right_sight)) {
				error += "����������Ϊ�գ�<br>";
			}
			if (error.equals("")) {
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("update student_health set ");
					sql.append("check_date ='").append(check_date).append("',");
					sql.append("height = ").append(height).append(",");
					sql.append("age = ").append(age).append(",");
					sql.append("weight = ").append(weight).append(",");
					sql.append("left_sight = ").append(left_sight).append(",");
					sql.append("right_sight = ").append(right_sight).append(",");
					sql.append("description = ").append("'").append(description).append("'");
					sql.append(" where id =").append(id);
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error += "���½���״��ʧ�ܣ�����쳣��<br>";
					} else {
						request.setAttribute("Succ", "���½���״����" + id+"���ɹ�");
					}

				}catch (Exception e) {
					error += "���½���״��ʧ�ܣ�δ֪�쳣��<br>";
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
	class HealthConvert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("name", rs.getString("name"));
				map.put("bodycard", rs.getString("bodycard"));
				map.put("sex", rs.getString("sex"));
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
