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

public class ProfitDAO {
	private Connection conn;	
	public ProfitDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();	
	}	
	public List<Profit> findAll() throws Exception
	{
		Statement stmt;
		String queryStr = "select * from profit";
		ResultSet rs;
		List<Profit> ps = new ArrayList<Profit>();
		Profit profit;
		stmt = conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				profit = new Profit();
				profit.setpId(rs.getInt("pId"));
				profit.setcId(rs.getInt("cId"));
				profit.setProfit(rs.getBigDecimal("profit"));
				profit.setCost(rs.getBigDecimal("cost"));
				profit.setCreateTime(rs.getString("createTime"));
				ps.add(profit);
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
		return ps;
	}	
	
	public Profit findByProfitId(String mId) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		Profit profit = new Profit();
		try
		{
			queryStr = "select * from profit where mId = " + mId;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	profit = new Profit();
	        	profit.setpId(rs.getInt("pId"));
				profit.setcId(rs.getInt("cId"));
				profit.setProfit(rs.getBigDecimal("profit"));
				profit.setCost(rs.getBigDecimal("cost"));
				profit.setCreateTime(rs.getString("createTime"));
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
		return profit;
	}	
	
	
	public List<Profit> findByCheckoutId(String cId) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		List<Profit> ps = new ArrayList<Profit>();
		Profit profit = new Profit();
		try
		{
			queryStr="select * from profit where cId = " + cId;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	profit = new Profit();
	        	profit.setpId(rs.getInt("pId"));
				profit.setcId(rs.getInt("cId"));
				profit.setProfit(rs.getBigDecimal("profit"));
				profit.setCost(rs.getBigDecimal("cost"));
				profit.setCreateTime(rs.getString("createTime"));
				ps.add(profit);
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
		return ps;
	}
	
	
	public List<Profit> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Profit> ps = new ArrayList<Profit>();
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

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
				Profit profit = new Profit();
				profit.setpId(rs.getInt("pId"));
				profit.setcId(rs.getInt("cId"));
				profit.setProfit(rs.getBigDecimal("profit"));
				profit.setCost(rs.getBigDecimal("cost"));
				profit.setCreateTime(rs.getString("createTime"));
				ps.add(profit);
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
		return ps;
	}
	
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Profit profit) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into profit (cId, profit, cost, createTime) values(?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try
		{
			stmt.setLong(1, profit.getcId());
			stmt.setBigDecimal(2, profit.getProfit());
			stmt.setBigDecimal(3, profit.getCost());
			stmt.setString(4, profit.getCreateTime());
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
	
	public boolean deleteById(int id) throws Exception
	{
		int pId = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try
		{
	    	String deleteStr = "delete from profit where pId = ?";
			stmt = conn.prepareStatement(deleteStr);
			stmt.setInt(1, pId);
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
