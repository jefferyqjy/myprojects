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

public class InqAnnounceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1068702977545577062L;
	
	public static final String [] subjects = {"", "����", "��ѧ", "Ӣ��", "����", "����", "����", "����",};
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String title = request.getParameter("title");
		String subject = request.getParameter("subject");
		try {
			StringBuffer sql = new StringBuffer(" where 1=1 ");

			if (!CommonUtil.checkEmpty(title)) {
				sql.append(" and title like '%").append(title.trim()).append("%'");
			}
			
			if(!CommonUtil.checkEmpty(subject)) {
				sql.append(" and subject = ").append(subject.trim());
			}

			String curPage = request.getParameter("curpage");
			if (null == curPage) {
				curPage = "1";
			}
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select count(*) totals from pro_announce" + sql.toString(),
					new TotalsConvert());
			String result = (String) output.getResults().get(0);
			if ("0".equals(result)) {
				request.setAttribute("Error", "û��ƥ��Ĺ�����Ϣ��");
			} else {
				int total = Integer.parseInt(result);
				output = (DaoHelperOutput) DaoManagerHandler.executeQuery("select * from pro_announce" + sql,
						new AnnounceConvert(), Integer.parseInt(curPage), 10);

				request.setAttribute("ANNOUNCELIST", output.getResults());
				PageControl control = new PageControl();
				control.init(Integer.parseInt(curPage), total, 10);
				request.setAttribute("control", control);

				request.setAttribute("ON_SELECT_TITLE", title);
			}

		} catch (Exception e) {

		}
		String urlPara = "/Nursery/inqsannounce?title=" + title + "&subject=" + subject;
		request.setAttribute("URL_PARA", urlPara);
		RequestDispatcher rd = request.getRequestDispatcher("/console/inqannounce.jsp");
		rd.forward(request, response);
	}

	public class AnnounceConvert implements InquireConvert {

		public Object convert(ResultSet rs) throws Exception {
			try {
				Map<String, String> map = new HashMap<String, String>();
				map.put("id", rs.getString("id"));
				map.put("teacher_user_name", rs.getString("teacher_user_name"));
				map.put("title", rs.getString("title"));
				map.put("content", rs.getString("content"));
				
				Integer subject = rs.getInt("subject");
				map.put("subject", subject.toString());
				String subjectInfo = subjects[subject];
				map.put("subjectInfo", subjectInfo);

				System.out.println("map :" + map);
				return map;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
		/*private String getDate(String src) {
			try {
				src = src.substring(0, 10);
				return src;
			} catch (Exception e) {
				return "";
			}
		}*/
	}
}
