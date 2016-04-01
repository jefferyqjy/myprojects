package com.pro.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.util.CommonUtil;

public class AddAnnounceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2045031191244881602L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String teacher_user_name = request.getParameter("teacher_user_name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String subject = request.getParameter("subject");

		String error = "";
		if (CommonUtil.checkEmpty(teacher_user_name)) {
			error += "评价老师不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(title)) {
			error += "标题不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(content)) {
			error += "内容不能为空！<br>";
		}
		
		if (CommonUtil.checkEmpty(subject)) {
			error += "科目不能为空！<br>";
		}

		if (error.equals("")) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("insert into pro_announce (teacher_user_name, subject, content, title) values(")
				.append("'").append(teacher_user_name).append("',")
				.append("'").append(subject).append("',")
				.append("'").append(content).append("',")
				.append("'").append(title).append("')");

				DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
				if (!output.isbSuccess()) {
					error += "添加公告失败，入库异常！<br>";
				} else {
					request.setAttribute("Succ", "添加公告成功！");
				}

			}catch (Exception e) {
				error += "添加公告失败，未知异常！<br>";
			}

		}
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addannounce.jsp");
		rd.forward(request, response);
	}


}
