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
import com.util.IdcardUtil;

public class AddHealthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String bodycard = request.getParameter("bodycard");
		String check_date = request.getParameter("check_date");
		String height = request.getParameter("height");
		String age = request.getParameter("age");
		String weight = request.getParameter("weight");
		String left_sight = request.getParameter("left_sight");
		String right_sight = request.getParameter("right_sight");
		String description = request.getParameter("description");

		String error = "";
		if (!IdcardUtil.isIdcard(bodycard)) {
			error += "���֤��������<br>";
		}

		if (!CommonUtil.checkEmpty(check_date)) {
			check_date = check_date.trim();
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
				sql.append("insert into student_health(bodycard,check_date,height,age,weight,left_sight,right_sight,description) values('").append(bodycard).append("',");
				sql.append("'").append(check_date).append("',");
				sql.append(height).append(",");
				sql.append(age).append(",");
				sql.append(weight).append(",");
				sql.append(left_sight).append(",");
				sql.append(right_sight).append(",");
				sql.append("'").append(description).append("')");

				DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
				if (!output.isbSuccess()) {
					error += "��ӽ���״��ʧ�ܣ�����쳣��<br>";
				} else {
					request.setAttribute("Succ", "��ӽ���״����" + bodycard+"���ɹ�");
				}

			}catch (Exception e) {
				error += "��ӽ���״��ʧ�ܣ�δ֪�쳣��<br>";
			}

		}
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addhealth.jsp");
		rd.forward(request, response);
	}


}
