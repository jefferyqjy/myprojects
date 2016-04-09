package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Partinner;
import util.ConnOfDatabase;

public class PartinnerDAO {
	private Connection conn;	
	public PartinnerDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();	
	}	
	public List<Partinner> findAll() throws Exception
	{
		Statement stmt;
		String queryStr = "select * from partinner";
		ResultSet rs;
		List<Partinner> partinners = new ArrayList<Partinner>();
		Partinner partinner;
		stmt = conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				partinner = new Partinner();
				partinner.setCompany(rs.getString("company"));
				partinner.setDate(rs.getString("date"));
				partinner.setEmpId(rs.getInt("empId"));
				partinner.setiId(rs.getInt("iId"));
				partinner.setpCost(rs.getBigDecimal("pCost"));
				partinner.setpId(rs.getString("pId"));
				partinner.setpNum(rs.getInt("pNum"));
				partinner.setpPrice(rs.getBigDecimal("pPrice"));
				partinners.add(partinner);
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
		return partinners;
	}	
	
	public Partinner findById(String id) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		Partinner partinner = new Partinner();
		try
		{
			queryStr = "select * from partinner where iId = " + id;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	partinner = new Partinner();
	        	partinner.setCompany(rs.getString("company"));
				partinner.setDate(rs.getString("date"));
				partinner.setEmpId(rs.getInt("empId"));
				partinner.setiId(rs.getInt("iId"));
				partinner.setpCost(rs.getBigDecimal("pCost"));
				partinner.setpId(rs.getString("pId"));
				partinner.setpNum(rs.getInt("pNum"));
				partinner.setpPrice(rs.getBigDecimal("pPrice"));
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
		return partinner;
	}	
	
	public List<Partinner> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		List<Partinner> partinners = new ArrayList<Partinner>();
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
					Partinner partinner = new Partinner();
					partinner.setCompany(rs.getString("company"));
					partinner.setDate(rs.getString("date"));
					partinner.setEmpId(rs.getInt("empId"));
					partinner.setiId(rs.getInt("iId"));
					partinner.setpCost(rs.getBigDecimal("pCost"));
					partinner.setpId(rs.getString("pId"));
					partinner.setpNum(rs.getInt("pNum"));
					partinner.setpPrice(rs.getBigDecimal("pPrice"));
					partinners.add(partinner);
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
		return partinners;
	}
	
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Partinner partinner) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into partinner (empId, company, pCost, pPrice, pId, pNum, date) values(?, ?, ?, ?, ?, ?, ?)";
		stmt = conn.prepareStatement(insertStr);
		try
		{
			stmt.setLong(1, partinner.getEmpId());
			stmt.setString(2, partinner.getCompany());
			stmt.setBigDecimal(3, partinner.getpCost());
			stmt.setBigDecimal(4, partinner.getpPrice());
			stmt.setString(5, partinner.getpId());
			stmt.setLong(6, partinner.getpNum());
			stmt.setString(7, partinner.getDate());
			stmt.executeUpdate();
			insFlag = true;
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
	
	public boolean update(Partinner partinner) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update partinner set company = ?, pCost = ?, pPrice = ?, pNum = ?, pId = ? where iId = ?";
		stmt = conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1, partinner.getCompany());
			stmt.setBigDecimal(2, partinner.getpCost());
			stmt.setBigDecimal(3, partinner.getpPrice());
			stmt.setLong(4, partinner.getpNum());
			stmt.setString(5, partinner.getpId());
			stmt.setLong(6, partinner.getiId());
			stmt.executeUpdate();
			updateFlag = true;
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
		int iId = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try
		{
	    	String deleteStr = "delete from partinner where iId = ?";
			stmt = conn.prepareStatement(deleteStr);
			stmt.setInt(1, iId);
			stmt.executeUpdate();
			deleteFlag = true;
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
