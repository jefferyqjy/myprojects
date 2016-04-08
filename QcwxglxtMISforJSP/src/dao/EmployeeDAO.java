package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnOfDatabase;
import bean.*;

public class EmployeeDAO {
	private Connection conn;	
	public EmployeeDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}	
	public List<Employee> findAll() throws Exception
	{
		Statement stmt;
		String queryStr="select * from employee";
		ResultSet rs;
		List<Employee> employees=new ArrayList<Employee>();
		Employee employee;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				employee=new Employee();
				employee.setEmpID(rs.getInt("empID"));
	        	employee.setEmpName(rs.getString("empName"));
	        	employee.setEmppwd(rs.getString("emppwd"));
	        	employee.setEmpadd(rs.getString("empadd"));
	        	employee.setWorktime(rs.getString("worktime"));
	        	employee.setEmpphone(rs.getString("empphone"));
				employees.add(employee);
			}		
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}  		
		}
		return employees;
	}	
	public boolean CheckPassword(int empID, String password) throws SQLException{
		boolean insFlag = false;
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt=conn.prepareStatement(queryStr);
		try
		{
			queryStr="select * from employee where empID='"+empID+"' and emppwd='"+password+"'";
			rs=stmt.executeQuery(queryStr);

			while (rs.next()) {
				insFlag=true;
			}
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return insFlag;
	}	
	
	public Employee findByempID(String userID) throws Exception
	{
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Employee employee=new Employee();
		try
		{
			queryStr="select * from employee where empID="+userID;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	employee.setEmpID(rs.getInt("empID"));
	        	employee.setEmpName(rs.getString("empName"));
	        	employee.setEmppwd(rs.getString("emppwd"));
	        	employee.setEmpadd(rs.getString("empadd"));
	        	employee.setWorktime(rs.getString("worktime"));
	        	employee.setEmpphone(rs.getString("empphone"));
	        }
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}    				
		}
		return employee;
	}	
	
	
	public  Employee findById(int id) throws Exception
	{
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Employee employee=new Employee();
		try
		{
			queryStr="select * from employee where empID="+id;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	employee.setEmpID(rs.getInt("empID"));
	        	employee.setEmpName(rs.getString("empName"));
	        	employee.setEmppwd(rs.getString("emppwd"));
	        	employee.setEmpadd(rs.getString("empadd"));
	        	employee.setWorktime(rs.getString("worktime"));
	        	employee.setEmpphone(rs.getString("empphone"));
	        }
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}    				
		}
		return employee;
	}
	
	
	public  List<Employee> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Employee> employees= new ArrayList<Employee>();
		Statement stmt;
		ResultSet rs;
		stmt =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

		try
		{
		rs=stmt.executeQuery(queryStr);
		if (rs.next())
		{
			rs.beforeFirst();
			if ((currentPage - 1) * pageCount > 0) {
			// 移动结果集数据到当前页
			rs.absolute((currentPage - 1) * pageCount);
			}   
			if (currentPage==1)
			{
				rs.beforeFirst();				
			}
				int i = 0; // Readed pages
				while (rs.next() && i < pageCount) 
				{
				i++;
				Employee employee=new Employee();
				employee.setEmpID(rs.getInt("empID"));
				employee.setEmpName(rs.getString("empName"));
				employee.setEmppwd(rs.getString("emppwd"));
				employee.setEmpadd(rs.getString("empadd"));
				employee.setWorktime(rs.getString("worktime"));
				employee.setEmpphone(rs.getString("empphone"));
				employees.add(employee);
				}  
		}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}    		
		}
		return employees;
	}
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Employee employee) throws Exception
	{
		PreparedStatement stmt;
		int empID=0;
		boolean insFlag=false;
		String insertStr="insert into employee values(?,?,?,?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{
			String queryStr="select max(empID) as mempId from employee";
			Statement qstmt=conn.createStatement();		
			ResultSet rs=qstmt.executeQuery(queryStr);
			if (rs.next())
			{	
				empID=1+rs.getInt("mempId");			
				employee.setEmpID(empID);
			}	
			stmt.setInt(1,employee.getEmpID());
			stmt.setString(2,employee.getEmpName());
			stmt.setString(3,employee.getEmppwd());
			stmt.setString(4,employee.getEmpadd());
			stmt.setString(5, employee.getWorktime());
			stmt.setString(6, employee.getEmpphone());
			stmt.executeUpdate();
			insFlag=true;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}    				
		}
		return insFlag;
	}
	
	public  boolean update(Employee emp) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update employee set empName=?,empadd=?,worktime=?,empphone=? where empID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1,emp.getEmpName());
			stmt.setString(2,emp.getEmpadd());
			stmt.setString(3,emp.getWorktime());
			stmt.setString(4,emp.getEmpphone());
			stmt.setInt(5,emp.getEmpID());
			stmt.executeUpdate();
			updateFlag=true;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}    				
		}
		
		return updateFlag;
	}
	public  boolean Update(int empID,String emppwd ) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update employee set emppwd=? where empID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1,emppwd);		
			stmt.setInt(2,empID);
			stmt.executeUpdate();
			updateFlag=true;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}    				
		}
		
		return updateFlag;
	}
	public boolean deleteById(int id) throws Exception
	{
		int empId=id;
		PreparedStatement stmt;
		boolean deleteFlag=false;
		try
		{
	    	String insertStr="delete from employee where empID=?";
			stmt =conn.prepareStatement(insertStr);
			stmt.setInt(1,empId);
			stmt.executeUpdate();
			deleteFlag=true;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
			}    					
		}
		
		return deleteFlag;	
	}

}
