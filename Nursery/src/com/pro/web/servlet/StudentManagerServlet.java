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

public class StudentManagerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("gbk");
		response.setCharacterEncoding("gbk");
		response.setContentType("text/html;charset=gbk");
		String type = request.getParameter("type");
		String bodycard = request.getParameter("bodycard");
		String target = "";
		if ("view".equals(type)) {
			target = "/console/viewstudent.jsp";
			view(request, bodycard);
		} else if ("delete".equals(type)) {
			target = "/console/inqstudent.jsp";
			delete(request, bodycard);
		} else if ("viewupdate".equals(type)) {
			target = "/console/updatestudent.jsp";
			view(request, bodycard);
		} else if ("update".equals(type)){
			target = "/console/inqstudent.jsp";
			update(request, bodycard);
		}
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	private void view(HttpServletRequest request, String bodycard) {
		try {
			String sql = "select * from pro_student where bodycard ='" + bodycard +"'";
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeQuery(sql,
					new InqStudentServlet() .new StudentConvert());
			request.setAttribute("STUDENTINFO", output.getResults().get(0));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void delete(HttpServletRequest request, String bodycard) {
		try {
			String sql = "delete from pro_student where bodycard ='" + bodycard +"'";
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql);
			if (output.isbSuccess()) {
				request.setAttribute("Succ", "删除学籍【" + bodycard +"】成功");
			} else {
				request.setAttribute("Error", "删除学籍【" + bodycard +"】失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("Error", e.getMessage());
		}
	}

	private void update(HttpServletRequest request, String bodycard) {
		try {
			String name = request.getParameter("student_name");
			String birth = request.getParameter("student_birthday");
			String newBodycard = request.getParameter("student_bodycard");
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
			if (!IdcardUtil.isIdcard(newBodycard)) {
				error += "身份证输入有误！<br>";
			}
			if (CommonUtil.checkEmpty(birth)) {
				error += "出生日期不能为空！<br>";
			} else {
				birth = birth.trim().replaceAll("-", "");
				try {
					Integer.parseInt(birth);
					if (birth.length() != 8) {
						throw new Exception();
					}
					birth = birth.substring(0,4) + "-" +
					birth.substring(4,6)+"-" + birth.substring(6,8);
				} catch (Exception e) {
					error += "出生日期格式不对！<br>";
				}
			}
			if (!CommonUtil.checkEmpty(outdate)) {
				outdate = outdate.trim().replaceAll("-", "");
				try {
					Integer.parseInt(outdate);
					if (outdate.length() != 8) {
						throw new Exception();
					}
					outdate = outdate.substring(0,4) + "-" +
					outdate.substring(4,6)+"-" + outdate.substring(6,8);
				} catch (Exception e) {
					error += "毕业日期格式不对！<br>";
				}
			} else {
				outdate = "";
			}

			if (!CommonUtil.checkEmpty(indate)) {
				indate = indate.trim().replaceAll("-", "");
				try {
					Integer.parseInt(indate);
					if (indate.length() != 8) {
						throw new Exception();
					}
					indate = indate.substring(0,4) + "-" +
					indate.substring(4,6)+"-" + indate.substring(6,8);
				} catch (Exception e) {
					error += "入学日期格式不对！<br>";
				}
			} else {
				indate ="";
			}

			if (CommonUtil.checkEmpty(name)) {
				error += "姓名不能为空！<br>";
			}
			if (error.equals("")) {
				try {
					StringBuffer sql = new StringBuffer();
					sql.append("update pro_student set bodycard ='").append(newBodycard).append("',");
					sql.append("name = '").append(name).append("',");
					sql.append("birthday = '").append(birth).append("',");
					sql.append("nationality = '").append(nationality).append("',");
					sql.append("nativeplace = '").append(nativeplace).append("',");
					sql.append("school = '").append(school).append("',");
					sql.append("in_date = '").append(indate).append("',");
					sql.append("out_date = '").append(outdate).append("',");
					sql.append("father_name = '").append(father).append("',");
					sql.append("father_work_address = '").append(fatherAddress).append("',");
					sql.append("mother_name = '").append(mother).append("',");
					sql.append("mother_work_address = '").append(motherAddress).append("',");
					sql.append("telephone = '").append(telephone).append("',");
					sql.append("description = '").append(description).append("' ");
					sql.append("where bodycard='").append(bodycard).append("'");
					DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler.executeUpdate(sql.toString());
					if (!output.isbSuccess()) {
						error += "修改学籍失败，入库异常！<br>";
					} else {
						request.setAttribute("Succ", "修改学籍【" + bodycard+"】成功");
					}
				}catch (Exception e) {
					error += "修改学籍失败，未知异常！<br>";
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
