package servlets.evaluationservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JSonHelper;
import bean.*;
import dao.*;

/**
 * Servlet implementation class EvaluationInfocIDServlet
 */
@WebServlet(name = "EvaluationInfocIDaID", description = "EvaluationInfocID", urlPatterns = { "/EvaluationInfocID" })
public class EvaluationInfocIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public EvaluationInfocIDServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* Ser 销毁的时候调用此方法 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			
			EvaluationDAO evaluationDAO=new EvaluationDAO();
			int cID = Integer.valueOf(request.getParameter("cID"));

			response.setCharacterEncoding("UTF-8");
			Evaluation evaluation= evaluationDAO.findBycId(cID);
			if (evaluation.getcID() != cID)
				 {
				String jsonStr = JSonHelper.toJSonString(evaluation);//静态方法,通过一个JSONString创建一个JSONObject对象
				response.getWriter().append(jsonStr);
				response.getWriter().flush();
			} else {
				throw new Exception("该维修单已评价");
			}
		} catch (Exception e) {
			response.getWriter().append(
					"{\"error\":\"" + e.getMessage() + "\"}");
			response.getWriter().flush();
		}
	
	}
}
