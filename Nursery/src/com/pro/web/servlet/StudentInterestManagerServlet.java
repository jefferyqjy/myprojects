package com.pro.web.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
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
import com.util.InterestEnum;
import com.util.PageControl;
import com.util.TotalsConvert;

public class StudentInterestManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String target = "";
		if("add".equals(type)) {
			target = "/console/addstudentinterest.jsp";
			add(request, id);
		} else if("inq".equals(type)) {
			target = "/console/inqstudentinterest.jsp";
			inq(request, response);
			return;
		} else if("view".equals(type)) {
			target = "/console/viewstudentinterest.jsp";
			view(request, id);
		} else if("delete".equals(type)) {
			target = "/console/inqstudentinterest.jsp";
			delete(request, id);
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	private void delete(HttpServletRequest request, String id) {
		try {
			String sql = "delete from pro_student_interest where id = " + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql);
			if (output.isbSuccess()) {
				request.setAttribute("Succ", "删除学生兴趣班信息成功！");
			} else {
				request.setAttribute("Error", "删除学生兴趣班信息失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}
	
	private void view(HttpServletRequest request, String id) {
		try {
			String sql = "select * from pro_student_interest where id = " + id;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql, new StudentInterestManagerServlet() .new StudentInterestConvert());
			request.setAttribute("STUDENTINTEREST", output.getResults().get(0));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}
	
	private void inq(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sql = new StringBuffer("");
		String bodycard = request.getParameter("student_bodycard");
		String interest = request.getParameter("interest");
		try {
			if(!CommonUtil.checkEmpty(bodycard)) {
				sql.append(" where student_bodycard like '%").append(bodycard).append("%'");
			}	
			if(!CommonUtil.checkEmpty(interest)) {
				sql.append(" and interest = '").append(interest).append("'");
			}
			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			DaoHelperOutput output;
			
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select count(*) totals from pro_student_interest " + sql.toString(), new TotalsConvert());
			String result = (String) output.getResults().get(0);
			if ("0".equals(result)) {
				request.setAttribute("Error", "没有匹配的学生兴趣班信息！");
			} else {
				int total = Integer.parseInt(result);
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from pro_student_interest " + sql, new StudentInterestConvert(), Integer.parseInt(curPage), 10);
	
				request.setAttribute("STUDENTINTERESTLIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), total, 10);
				request.setAttribute("control", control);
	
				request.setAttribute("ON_SELECT_STUDENT_BODYCARD", bodycard);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String urlPara = "/Nursery/inqstudentinterest?student_bodycard=" + bodycard;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqstudentinterest.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void add(HttpServletRequest request, String id) {
		try {
			String bodycard = request.getParameter("student_bodycard");
			String interest= request.getParameter("interest");
			String error = "";
			
			if(CommonUtil.checkEmpty(bodycard)) {
				error += "学生学号不能为空！<br>";
			}
			if(CommonUtil.checkEmpty(interest)) {
				error += "兴趣不能为空！<br>";
			}
			
			if (error.equals("")) {
				try {
					// duplicate validation
					StringBuffer sql1 = new StringBuffer();
					sql1.append("select * from pro_student_interest where student_bodycard = '").append(bodycard).append("'");;
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql1.toString(), new StudentInterestConvert());
					List<Object> list = output.getResults();
					if(list != null && list.size() > 0) {
						error += "该学号已选择过兴趣班！";
					} else {
						StringBuffer sql = new StringBuffer();
						sql.append("insert into pro_student_interest (student_bodycard, interest) values (")
							.append("'").append(bodycard).append("', ")
							.append("'").append(interest).append("')");
						output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
						
						if (!output.isbSuccess()) {
							error += "新增学生兴趣班信息失败，入库异常！<br>";
						} else {
							request.setAttribute("Succ", "修改学生兴趣班信息成功！");
						}
					}
				}catch (Exception e) {
					error += "修改学生兴趣班失败，未知异常！<br>";
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
	
	public class StudentInterestConvert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("student_bodycard", rs.getString("student_bodycard"));
				map.put("interest", rs.getString("interest"));
				map.put("interestInfo", InterestEnum.valueOf(rs.getString("interest")).getInfo());
				System.out.println("map :" + map);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
}
