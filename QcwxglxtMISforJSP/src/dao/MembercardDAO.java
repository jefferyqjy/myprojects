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

public class MembercardDAO {
	private Connection conn;	
	public MembercardDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();	
	}	
	public List<Membercard> findAll() throws Exception
	{
		Statement stmt;
		String queryStr = "select * from membercard";
		ResultSet rs;
		List<Membercard> mcs = new ArrayList<Membercard>();
		Membercard membercard;
		stmt = conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				membercard = new Membercard();
				membercard.setmId(rs.getInt("mId"));
				membercard.setCusId(rs.getInt("cusId"));
				membercard.setCardNo(rs.getString("cardNo"));
				membercard.setAmount(rs.getBigDecimal("amount"));
				membercard.setCreateTime(rs.getString("createTime"));
				mcs.add(membercard);
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
		return mcs;
	}	
	
	public Membercard findByMembercardId(String mId) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		Membercard membercard = new Membercard();
		try
		{
			queryStr = "select * from membercard where mId = " + mId;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	membercard = new Membercard();
				membercard.setmId(rs.getInt("mId"));
				membercard.setCusId(rs.getInt("cusId"));
				membercard.setCardNo(rs.getString("cardNo"));
				membercard.setAmount(rs.getBigDecimal("amount"));
				membercard.setCreateTime(rs.getString("createTime"));
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
		return membercard;
	}	
	
	
	public List<Membercard> findByCusId(String cusId) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		List<Membercard> mcs = new ArrayList<Membercard>();
		Membercard membercard = new Membercard();
		try
		{
			queryStr="select * from membercard where cusId = " + cusId;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	membercard = new Membercard();
				membercard.setmId(rs.getInt("mId"));
				membercard.setCusId(rs.getInt("cusId"));
				membercard.setCardNo(rs.getString("cardNo"));
				membercard.setAmount(rs.getBigDecimal("amount"));
				membercard.setCreateTime(rs.getString("createTime"));
				mcs.add(membercard);
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
		return mcs;
	}
	
	
	public List<Membercard> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Membercard> mcs = new ArrayList<Membercard>();
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
				Membercard membercard = new Membercard();
				membercard.setmId(rs.getInt("mId"));
				membercard.setCusId(rs.getInt("cusId"));
				membercard.setCardNo(rs.getString("cardNo"));
				membercard.setAmount(rs.getBigDecimal("amount"));
				membercard.setCreateTime(rs.getString("createTime"));
				mcs.add(membercard);
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
		return mcs;
	}
	
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Membercard membercard) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into membercard (cusId, amount, cardNo, createTime) values(?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try
		{
			stmt.setLong(1, membercard.getCusId());
			stmt.setBigDecimal(2, membercard.getAmount());
			stmt.setString(3, membercard.getCardNo());
			stmt.setString(4, membercard.getCreateTime());
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
	
	public boolean update(Membercard membercard) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update membercard set amount = ? where mId = ?";
		stmt = conn.prepareStatement(updateStr);	
		try
		{
			stmt.setBigDecimal(1, membercard.getAmount());
			stmt.setInt(2, membercard.getmId());
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
	    	String deleteStr = "delete from membercard where mId = ?";
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
