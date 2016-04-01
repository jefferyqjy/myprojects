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

public class AddTeacherCommentServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6417243356003487497L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String teacher_user_name = request.getParameter("teacher_user_name");
		String content = request.getParameter("content");
		String grade = request.getParameter("grade");
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String create_time = df.format(date);

		String error = "";
		if (CommonUtil.checkEmpty(teacher_user_name)) {
			error += "老师用户名不能为空！<br>";
		}
		
		if(CommonUtil.checkEmpty(content)) {
			error += "评价内容不能为空！<br>";
		}
		
		if(CommonUtil.checkEmpty(grade)) {
			error += "等地不能为空！<br>";
		}

		if (error.equals("")) {
			try {
				StringBuffer sql = new StringBuffer();
				sql.append("insert into pro_teacher_comment(content, teacher_user_name, create_time, grade) values(")
				.append("'").append(content).append("',")
				.append("'").append(teacher_user_name).append("',")
				.append("'").append(create_time).append("',")
				.append("'").append(grade).append("')");

				DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
				if (!output.isbSuccess()) {
					error += "添加教师评价失败，入库异常！<br>";
				} else {
					request.setAttribute("Succ", "添加教师评价成功！");
				}

			}catch (Exception e) {
				error += "添加教师评价失败，未知异常！<br>";
			}

		}
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addteachercomment.jsp");
		rd.forward(request, response);
	}


}
