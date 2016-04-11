package com.pro.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pro.conn.DaoManager;
import com.pro.conn.DaoManagerHandler;
import com.pro.db.DaoHelperOutput;
import com.util.CommonUtil;

public class AddStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("student_name");
		String sex = request.getParameter("student_sex");
		String birth = request.getParameter("student_birthday");
		String bodycard = request.getParameter("student_bodycard");
		String nationality = request.getParameter("student_nationality");
		String nativeplace = request.getParameter("student_nativeplace");
		String indate = request.getParameter("student_indate");
		String outdate = request.getParameter("student_outdate");
		String school = request.getParameter("student_school");
		String father = request.getParameter("student_father");
		String fatherAddress = request.getParameter("father_address");
		String mother = request.getParameter("student_mother");
		String motherAddress = request.getParameter("mother_address");
		String description = request.getParameter("student_description");
		String telephone = request.getParameter("telephone");
		String error = "";
		/*if (!IdcardUtil.isIdcard(bodycard)) {
			error += "���֤��������<br>";
		}*/
		if (CommonUtil.checkEmpty(bodycard)) {
			error += "���֤����Ϊ�գ�<br>";
		}
		if (CommonUtil.checkEmpty(birth)) {
			error += "�������ڲ���Ϊ�գ�<br>";
		} else {
			birth = birth.trim();
			try {
				Integer.parseInt(birth);
				if (birth.length() != 8) {
					throw new Exception();
				}
				birth = birth.substring(0,4) + "-" +
				birth.substring(4,6)+"-" + birth.substring(6,8);
			} catch (Exception e) {
				error += "�������ڸ�ʽ���ԣ�����ʽ��yyyyMMdd��<br>";
			}
		}
		if (!CommonUtil.checkEmpty(outdate)) {
			outdate = outdate.trim();
			try {
				Integer.parseInt(outdate);
				if (outdate.length() != 8) {
					throw new Exception();
				}
				outdate = outdate.substring(0,4) + "-" +
				outdate.substring(4,6)+"-" + outdate.substring(6,8);
			} catch (Exception e) {
				error += "��ҵ���ڸ�ʽ���ԣ�����ʽ��yyyyMMdd��<br>";
			}
		} else {
			outdate = "";
		}
		
		if (!CommonUtil.checkEmpty(indate)) {
			indate = indate.trim();
			try {
				Integer.parseInt(indate);
				if (indate.length() != 8) {
					throw new Exception();
				}
				indate = indate.substring(0,4) + "-" +
				indate.substring(4,6)+"-" + indate.substring(6,8);
			} catch (Exception e) {
				error += "��ѧ���ڸ�ʽ���ԣ�����ʽ��yyyyMMdd��<br>";
			}
		} else {
			indate ="";
		}
		
		if (CommonUtil.checkEmpty(name)) {
			error += "������Ϊ�գ�<br>";
		}
		if (error.equals("")) {
			try {
				int totals = DaoManager.executeTotalsQuery("pro_student where bodycard = '" + bodycard.trim()+"'");
				if (totals == 0) {
					StringBuffer sql = new StringBuffer();
					sql.append("insert into pro_student values('").append(bodycard).append("',");
					sql.append("'").append(name).append("',");
					sql.append("'").append(birth).append("',");
					sql.append("'").append(sex).append("',");
					sql.append("'").append(nationality).append("',");
					sql.append("'").append(nativeplace).append("',");
					sql.append("'").append(school).append("',");
					sql.append("'").append(indate).append("',");
					sql.append("'").append(outdate).append("',");
					sql.append("'").append(father).append("',");
					sql.append("'").append(fatherAddress).append("',");
					sql.append("'").append(mother).append("',");
					sql.append("'").append(motherAddress).append("',");
					sql.append("'").append(telephone).append("',");
					sql.append("'").append(description).append("')");
					
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error += "���ѧ����Ϣʧ�ܣ�����쳣��<br>";
					} else {
						request.setAttribute("Succ", "���ѧ��" + bodycard+"���ɹ�");
					}
				} else {
					error += "�����֤�ѵǼǣ���<br>";
				}
			}catch (Exception e) {
				error += "���ѧ����Ϣʧ�ܣ�δ֪�쳣��<br>";
			}
		
		} 
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addstudent.jsp");
		rd.forward(request, response);
	}

	
}
