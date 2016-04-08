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

public class MessageDAO {
	private Connection conn;	
	public MessageDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();	
	}	
	public List<Message> findAll() throws Exception
	{
		Statement stmt;
		String queryStr = "select * from message";
		ResultSet rs;
		List<Message> messages = new ArrayList<Message>();
		Message message;
		stmt = conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				message = new Message();
				message.setmId(rs.getInt("mId"));
				message.setCusId(rs.getInt("cusId"));
				message.setCreateTime(rs.getString("createTime"));
				message.setContent(rs.getString("content"));
				messages.add(message);
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
		return messages;
	}	
	
	public Message findByMessageId(String mId) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		Message message = new Message();
		try
		{
			queryStr = "select * from message where mId = " + mId;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	message = new Message();
				message.setmId(rs.getInt("mId"));
				message.setCusId(rs.getInt("cusId"));
				message.setCreateTime(rs.getString("createTime"));
				message.setContent(rs.getString("content"));
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
		return message;
	}	
	
	
	public List<Message> findByCusId(String cusId) throws Exception
	{
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();	
		List<Message> messages = new ArrayList<Message>();
		Message message = new Message();
		try
		{
			queryStr="select * from message where cusId = " + cusId;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        	message = new Message();
				message.setmId(rs.getInt("mId"));
				message.setCusId(rs.getInt("cusId"));
				message.setCreateTime(rs.getString("createTime"));
				message.setContent(rs.getString("content"));
				messages.add(message);
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
		return messages;
	}
	
	
	public  List<Message> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Message> messages = new ArrayList<Message>();
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
				Message message = new Message();
				message.setmId(rs.getInt("mId"));
				message.setCusId(rs.getInt("cusId"));
				message.setCreateTime(rs.getString("createTime"));
				message.setContent(rs.getString("content"));
				messages.add(message);
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
		return messages;
	}
	
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Message message) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into message (cusId, createTime, content) values(?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try
		{
			stmt.setLong(1, message.getCusId());
			stmt.setString(2, message.getCreateTime());
			stmt.setString(3, message.getContent());
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
	
	public boolean update(Message message) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update message set content = ? where mId = ?";
		stmt = conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1, message.getContent());
			stmt.setInt(2, message.getmId());
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
	    	String deleteStr = "delete from message where mId = ?";
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
