package com.pro.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManager;
import com.util.CommonUtil;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("user_name");
		String userPassword = request.getParameter("user_password");
		String userType = request.getParameter("user_type");
		String error = null;
		if (CommonUtil.checkEmpty(userName)
				|| CommonUtil.checkEmpty(userPassword)) {
			error = "输入有误！请确认！";
		} else {
			userName = userName.trim();
			userPassword = userPassword.trim();
			try {
				int totals = DaoManager
						.executeTotalsQuery("pro_user where user_name='"
								+ userName + "' and user_password ='"
								+ userPassword + "'");
				if (totals > 0) {
					if ("1".equals(userType)) {
						userType = "admin";
					} else if ("2".equals(userType)) {
						userType = "teacher";
					} else {
						userType = "family";
					}
					totals = DaoManager
							.executeTotalsQuery("pro_user_role where user_name='"
									+ userName
									+ "' and role_name ='"
									+ userType + "'");
					if (totals > 0) {

					} else {
						error = "帐号身份不匹配！";
					}
				} else {
					error = "帐号密码不匹配！";
				}
			} catch (Exception e) {
				error = "输入有误！请确认！";
			}

		}

		String target = "/console/main.jsp";
		if (error != null) {
			request.setAttribute("Error", error);
			target = "index.jsp";
		} else {
			request.getSession().setAttribute("PRO_USER_NAME", userName);
			request.getSession().setAttribute("PRO_USER_ROLE", userType);
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}
}
