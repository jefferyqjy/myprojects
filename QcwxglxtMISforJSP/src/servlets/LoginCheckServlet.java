package servlets;

import java.io.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.*;
import bean.*;

/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet(name = "LoginCheck", description = "LoginCheck", urlPatterns = { "/LoginCheck" })
public class LoginCheckServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID=request.getParameter("userID");//定义useID,通过get方法从Parameter中取得useID的值
		String userPassword=request.getParameter("userPassword");
		String userType=request.getParameter("userType");
		String imageCode = request.getParameter("imageCode"); 
		HttpSession  session=request.getSession();//request 是一个实例化的对象,它有一个方法,叫做getSession();执行这个方法之后这个方法会返回一个实例化好的 HttpSession 对象,这个时候session 就可以经由它来赋值了.
		String rand = (String)session.getAttribute("rand"); //获取session中名为rand的值并转为字符串赋给rand
		response.setContentType("text/html;charset=UTF-8"); //通知浏览器服务器发送的数据格式
		response.setCharacterEncoding("UTF-8");//response的响应的编码方式为utf-8 
		PrintWriter out = response.getWriter();//通过PrintWrite，以流方式输出html，返回给客户端，显示在IE上。
		
		if (userType.equals("employee"))
		{	
			EmployeeDAO empDAO=new EmployeeDAO();//实例化一个父类的对象EmployeeDAO
			Employee employee=new Employee();
			boolean findFl=false;
			try
	    	  { 
	    		  employee=empDAO.findById(Integer.parseInt(userID));
	    	  }catch(Exception e)
	    	  {
	    		  out.print("<script language='javascript'>alert('获取人员信息失败，请输入正确的用户名编号！')</script>");   
	    		  getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").include(request, response);
	    		  return;
	    	  }
	    	  if (employee.getEmpID() == Integer.parseInt(userID))
	    	  {
	    		  if (employee.getEmppwd().equals(userPassword))
	    		 {	  
	    			  if(imageCode.equals(rand))
	    			  {
	    				  
	    			  findFl=true;
	    		      }
	    			  else
	    			  {
	    		           out.print("<script language='javascript'>alert('验证码不正确，请重新输入!')</script>");
	    		      }
	    	      }
	    		  else
	    		  {
	    		       out.print("<script language='javascript'>alert('密码不正确，请重新输入!')</script>");
	              }
	    		  
	    	  }
	    	  else
	    	  {
	    		   out.print("<script language='javascript'>alert('用户名不正确，请重新输入!')</script>");
	    	  }  
			if (findFl)
			{
				session.setAttribute("userID",userID);
				session.setAttribute("type", "employee");
				session.setAttribute("employee", employee);
				getServletConfig().getServletContext().getRequestDispatcher("/mainforemployee.jsp").forward(request, response);	
			}
			else
			{	
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").include(request, response);	
			}	
		}
		else if (userType.equals("admin"))
		{	
			AdminDAO adminDAO=new AdminDAO();
			Admin admin=new Admin();
			boolean findFl=false;
			
			try
	    	  { 
				admin=adminDAO.findById(Integer.parseInt(userID));
	    	  }catch(Exception e)
	    	  {
	    		  out.print("<script language='javascript'>alert('获取人员信息失败！')</script>");
	    	  }
			
	    	  if (admin.getAdmID() == Integer.parseInt(userID))
	    	  {
	    		  if (admin.getAdmpwd().equals(userPassword))
		    		 {
	    			  if(imageCode.equals(rand))   			  
    			       {
    			           findFl=true;
    			
    		          }
	    			  else
	    			  {
    		           out.print("<script language='javascript'>alert('验证码不正确，请重新输入!')</script>");
		           }
	    	     }
	    		  else
	    		  {
	    			  out.print("<script language='javascript'>alert('密码不正确，请重新输入!')</script>");
	    	      }
		    }
	    	  else
	    	  {
	    		  out.print("<script language='javascript'>alert('用户名不正确，请重新输入!')</script>"); 
		}
			if (findFl)
			{
				
				session.setAttribute("userID",userID);
				session.setAttribute("type", "admin");
				session.setAttribute("admin", admin);
				getServletConfig().getServletContext().getRequestDispatcher("/mainforadmin.jsp").forward(request, response);	
			}
			else
			{	
				getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").include(request, response);	
			}	
			
		}
		else if (userType.equals("customer"))
		{	
			CustomerDAO customerDAO=new CustomerDAO();
			Customer customer=new Customer();
			boolean findFl=false;
			
			try
	    	  { 
				customer=customerDAO.findById(Integer.parseInt(userID));
	    	  }catch(Exception e)
	    	  {
	    		  out.print("<script language='javascript'>alert('获取人员信息失败！')</script>");
	    	  }
			
	    	  if (customer.getCusID() == Integer.parseInt(userID))
	    	  {
	    		  if (customer.getCuspwd().equals(userPassword))
		    		 {
	    			  if(imageCode.equals(rand))   			  
    			       {
    			           findFl=true;
    			
    		          }
	    			  else
	    			  {
    		           out.print("<script language='javascript'>alert('验证码不正确，请重新输入!')</script>");
		           }
	    	     }
	    		  else
	    		  {
	    			  out.print("<script language='javascript'>alert('密码不正确，请重新输入!')</script>");
	    	      }
		    }
	    	  else
	    	  {
	    		  out.print("<script language='javascript'>alert('用户名不正确，请重新输入!')</script>"); 
		}
			if (findFl)
			{
				
				session.setAttribute("userID",userID);
				session.setAttribute("type", "customer");
				session.setAttribute("customer", customer);
				getServletConfig().getServletContext().getRequestDispatcher("/mainforcustomer.jsp").forward(request, response);	
			}
			else
			{	
				getServletConfig().getServletContext().getRequestDispatcher("/login_cus.jsp").include(request, response);	
			}	
			
		}
	}

}
