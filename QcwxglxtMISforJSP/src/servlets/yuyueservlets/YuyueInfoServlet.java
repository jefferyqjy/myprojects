package servlets.yuyueservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.JSonHelper;
import bean.Yuyue;
import dao.YuyueDAO;

/**
 * Servlet implementation class YuyueInfoServlet
 */
@WebServlet(name = "YuyueInfo", description = "YuyueInfo", urlPatterns = { "/YuyueInfo" })
public class YuyueInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public YuyueInfoServlet() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			YuyueDAO yuyueDao = new YuyueDAO();
			int yID = Integer.valueOf(request.getParameter("yID"));

			response.setCharacterEncoding("UTF-8");

			Yuyue yuyue = yuyueDao.findById(yID);
			if (yuyue != null) {
				String jsonStr = JSonHelper.toJSonString(yuyue);//静态方法,通过一个JSONString创建一个JSONObject对象
				response.getWriter().append(jsonStr);//append() 方法在被选元素的结尾（仍然在内部）插入指定内容。
				response.getWriter().flush();
			} else {
				throw new Exception("找不到预约号");
			}
		} catch (Exception e) {
			response.getWriter().append(
					"{\"error\":\"" + e.getMessage() + "\"}");
			response.getWriter().flush();
		}
	}

}
