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

public class AnnounceManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String target = "";
		if ("view".equals(type)) {
			target = "/console/viewannounce.jsp";
			view(request, id);
		} else if ("delete".equals(type)) {
			target = "/console/inqannounce.jsp";
			delete(request, id);
		} else if ("viewupdate".equals(type)) {
			target = "/console/updateannounce.jsp";
			view(request, id);
		} else if ("update".equals(type)){
			target = "/console/inqannounce.jsp";
			update(request, id);
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	private void view(HttpServletRequest request, String id) {
		try {
			String sql = "select * from pro_announce where id = " + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql,
					new InqAnnounceServlet() .new AnnounceConvert());
			request.setAttribute("ANNOUNCEINFO", output.getResults().get(0));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void delete(HttpServletRequest request, String id) {
		try {
			String sql = "delete from pro_announce where id = " + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql);
			if (output.isbSuccess()) {
				request.setAttribute("Succ", "删除公告成功！");
			} else {
				request.setAttribute("Error", "删除公告失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void update(HttpServletRequest request, String id) {
		try {
			String content = request.getParameter("content");
			String title = request.getParameter("title");
			String error = "";
			
			if (CommonUtil.checkEmpty(content)) {
				error += "内容不能为空！<br>";
			}
			if (CommonUtil.checkEmpty(content)) {
				error += "标题不能为空！<br>";
			}
			
			if (error.equals("")) {
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("update pro_announce set ")
						.append("content = '").append(content).append("', ")
						.append("title = '").append(title).append("' ")
						.append("where id = ").append(id);
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error += "修改公告失败，入库异常！<br>";
					} else {
						request.setAttribute("Succ", "修改公告成功！");
					}
				}catch (Exception e) {
					error += "修改公告失败，未知异常！<br>";
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

	public static void main(String args[]) {
		String a = "2011-02-01";
		a =a.replaceAll("-", "");
		System.out.println(a);
	}
}
