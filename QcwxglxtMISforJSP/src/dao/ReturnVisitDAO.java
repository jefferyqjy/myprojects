package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bean.ReturnVisit;
import util.ConnOfDatabase;

public class ReturnVisitDAO {
	private Connection conn;	
	public ReturnVisitDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();	
	}	
	public List<ReturnVisit> findAll() throws Exception
	{
		Statement stmt;
		String queryStr = "select * from returnvisit";
		ResultSet rs;
		List<ReturnVisit> returnvisits = new ArrayList<ReturnVisit>();
		ReturnVisit returnvisit;
		stmt = conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				returnvisit = new ReturnVisit();
				returnvisit.setrId(rs.getInt("rId"));
				returnvisit.setEmpId(rs.getInt("empId"));
				returnvisit.setcId(rs.getInt("cId"));
				returnvisit.setCreateTime(rs.getString("createTime"));
				returnvisit.setContent(rs.getString("content"));
				returnvisits.add(returnvisit);
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
		return returnvisits;
	}	
	
	public ReturnVisit findById(String id) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		ReturnVisit returnvisit = new ReturnVisit();
		try
		{
			queryStr = "select * from returnvisit where rId = " + id;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	returnvisit = new ReturnVisit();
	        	returnvisit.setrId(rs.getInt("rId"));
				returnvisit.setEmpId(rs.getInt("empId"));
				returnvisit.setcId(rs.getInt("cId"));
				returnvisit.setCreateTime(rs.getString("createTime"));
				returnvisit.setContent(rs.getString("content"));
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
		return returnvisit;
	}	
	
	public List<ReturnVisit> findByEmpIdAndCId(String empId, String cId) throws Exception
	{
		Statement stmt;
		StringBuffer queryStr = new StringBuffer("select * from message where 1=1");
		ResultSet rs;
		stmt = conn.createStatement();	
		List<ReturnVisit> returnvisits = new ArrayList<ReturnVisit>();
		ReturnVisit returnvisit = new ReturnVisit();
		try
		{
			if(StringUtils.isNotEmpty(empId)) {
				queryStr.append(" and empId = ").append(empId);
			}
			
			if(StringUtils.isNotEmpty(cId)) {
				queryStr.append(" and cId = ").append(cId);
			}
			
			rs=stmt.executeQuery(queryStr.toString());

	        if (rs.next())
	        {
	        	returnvisit = new ReturnVisit();
	        	returnvisit.setrId(rs.getInt("rId"));
				returnvisit.setEmpId(rs.getInt("empId"));
				returnvisit.setcId(rs.getInt("cId"));
				returnvisit.setCreateTime(rs.getString("createTime"));
				returnvisit.setContent(rs.getString("content"));
				returnvisits.add(returnvisit);
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
		return returnvisits;
	}
	
	public List<ReturnVisit> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		List<ReturnVisit> returnvisits = new ArrayList<ReturnVisit>();
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
					ReturnVisit returnvisit = new ReturnVisit();
					returnvisit.setrId(rs.getInt("rId"));
					returnvisit.setEmpId(rs.getInt("empId"));
					returnvisit.setcId(rs.getInt("cId"));
					returnvisit.setCreateTime(rs.getString("createTime"));
					returnvisit.setContent(rs.getString("content"));
					returnvisits.add(returnvisit);
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
		return returnvisits;
	}
	
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(ReturnVisit returnvisit) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into returnvisit (empId, cId, createTime, content) values(?, ?, ?, ?)";
		stmt = conn.prepareStatement(insertStr);
		try
		{
			stmt.setLong(1, returnvisit.getEmpId());
			stmt.setLong(2, returnvisit.getcId());
			stmt.setString(3, returnvisit.getCreateTime());
			stmt.setString(4, returnvisit.getContent());
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
	
	public boolean update(ReturnVisit returnvisit) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update returnvisit set content = ? where rId = ?";
		stmt = conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1, returnvisit.getContent());
			stmt.setInt(2, returnvisit.getrId());
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
		int mId = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try
		{
	    	String deleteStr = "delete from returnvisit where rId = ?";
			stmt = conn.prepareStatement(deleteStr);
			stmt.setInt(1, mId);
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
