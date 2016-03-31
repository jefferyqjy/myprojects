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
import com.util.IdcardUtil;

public class AddStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
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
			error += "身份证输入有误！<br>";
		}*/
		if (CommonUtil.checkEmpty(bodycard)) {
			error += "身份证不能为空！<br>";
		}
		if (CommonUtil.checkEmpty(birth)) {
			error += "出生日期不能为空！<br>";
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
				error += "出生日期格式不对！（格式：yyyyMMdd）<br>";
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
				error += "毕业日期格式不对！（格式：yyyyMMdd）<br>";
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
				error += "入学日期格式不对！（格式：yyyyMMdd）<br>";
			}
		} else {
			indate ="";
		}
		
		if (CommonUtil.checkEmpty(name)) {
			error += "姓名不能为空！<br>";
		}
		if (error.equals("")) {
			try {
				int totals = DaoManager.executeTotalsQuery("pro_student where bodycared = '" + bodycard.trim()+"'");
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
						error += "添加学生信息失败，入库异常！<br>";
					} else {
						request.setAttribute("Succ", "添加学生【" + bodycard+"】成功");
					}
				} else {
					error += "该身份证已登记！！<br>";
				}
			}catch (Exception e) {
				error += "添加学生信息失败，未知异常！<br>";
			}
		
		} 
		if (!error.equals("")){
			request.setAttribute("Error", error);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/console/addstudent.jsp");
		rd.forward(request, response);
	}

	
}
