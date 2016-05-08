
package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.*;
import abstractClasses.DAO;

public class WageDAO extends DAO<Wage>{
	public List<Wage> findAll() throws Exception
	{
		Statement stmt;
		String queryStr="select wID,wage.empID,empName,month,basepay,commission,salary from wage,employee  where wage.empID=employee.empID";
		//String queryStr="select * from product";
		ResultSet rs;
		List<Wage> wages=new ArrayList<Wage>();
		Wage wage;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			//int len=rs.getRow();
			while (rs.next())
			{
				wage=new Wage();
				wage.setwID(rs.getInt("wID"));
	        	wage.setEmpID(rs.getInt("wage.empID"));
	        	wage.setEmpName(rs.getString("empName"));
				wage.setMonth(rs.getString("month"));
				wage.setBasepay(rs.getFloat("basepay"));
				wage.setCommission(rs.getFloat("commission"));
				wage.setSalary(rs.getFloat("salary"));
				wages.add(wage);
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
		return wages;
	}
	public  Wage findById(int id) throws Exception
	{
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Wage wage=new Wage();
		try
		{
			queryStr="select wID,wage.empID,empName,month,basepay,commission,salary from wage,employee where wID="+id;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	wage.setwID(rs.getInt("wID"));
	        	wage.setEmpID(rs.getInt("wage.empID"));
	        	wage.setEmpName(rs.getString("empName"));
				wage.setMonth(rs.getString("month"));
				wage.setBasepay(rs.getFloat("basepay"));
				wage.setCommission(rs.getFloat("commission"));
				wage.setSalary(rs.getFloat("salary"));
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
		return wage;
	}
	
	
	public  List<Wage> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Wage> wages= new ArrayList<Wage>();
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
				Wage wage=new Wage();
				wage.setwID(rs.getInt("wID"));
	        	wage.setEmpID(rs.getInt("wage.empID"));
	        	//wage.setEmpName(rs.getString("empName"));
				wage.setMonth(rs.getString("month"));
				wage.setBasepay(rs.getFloat("basepay"));
				wage.setCommission(rs.getFloat("commission"));
				wage.setSalary(rs.getFloat("salary"));
				wages.add(wage);
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
		return wages;
	}
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Wage wage) throws Exception
	{
		PreparedStatement stmt;
		int wID=0;
		boolean insFlag=false;
		String insertStr="insert into wage values(?,?,?,?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{
			String queryStr="select max(wID) as mwId from wage";
			Statement qstmt=conn.createStatement();		
			ResultSet rs=qstmt.executeQuery(queryStr);
			if (rs.next())
			{	
				wID=1+rs.getInt("mwId");			
				wage.setwID(wID);
			}		
			stmt.setInt(1,wage.getwID());
			stmt.setInt(2,wage.getEmpID());
			stmt.setString(3,wage.getMonth());
			stmt.setFloat(4, wage.getBasepay());
			stmt.setFloat(5,wage.getCommission());
			stmt.setFloat(6,wage.getSalary());
			
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
	
	public  boolean update(Wage wage) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update wage set empID=?,month=?,basepay=?,commission=?,salary=? where wID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			System.out.println(wage);

			stmt.setInt(1,wage.getEmpID());
			stmt.setString(2,wage.getMonth());
			stmt.setFloat(3, wage.getBasepay());
			stmt.setFloat(4,wage.getCommission());
			stmt.setFloat(5,wage.getSalary());
			stmt.setInt(6,wage.getwID());
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
		int wId=id;
		PreparedStatement stmt;
		boolean deleteFlag=false;
		try
		{
	    	String insertStr="delete from wage where wID=?";
			stmt =conn.prepareStatement(insertStr);
			stmt.setInt(1,wId);
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
