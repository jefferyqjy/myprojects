package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnOfDatabase;
import bean.Check;

public class CheckDAO {
	private Connection conn;	
	public CheckDAO() {
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}	
	
	public List<Check> findAll() throws Exception {
		Statement stmt;
		String queryStr="select * from check";
		ResultSet rs;//遍历结果集
		List<Check> checks = new ArrayList<Check>();//创建容器，存储对象为Category
		Check check;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);//执行sql语句
			//int len=rs.getRow();
			while (rs.next())//遍历结果集，当结果到达结尾，没有多余结果就退出
			{
				check=new Check();//初始化结果对象
				check.setId(rs.getInt("id"));
				check.setKindID(rs.getInt("kindid"));//这里是将数据库查询的数据集存储到对象中，然后放入结果集
				check.setCreateTime(rs.getString("createtime"));
				check.setNumber(rs.getInt("number"));
				check.setValue(rs.getInt("value"));
				checks.add(check);
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
		return checks;
	}
	
	public Check findById(int id) throws Exception {
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Check check = new Check();
		
		try
		{
			queryStr="select * from check where id = " + id;
			rs=stmt.executeQuery(queryStr);
			//int len=rs.getRow();
			while (rs.next())
			{
				check.setId(rs.getInt("id"));
				check.setKindID(rs.getInt("kindid"));
				check.setCreateTime(rs.getString("createtime"));
				check.setNumber(rs.getInt("number"));
				check.setValue(rs.getInt("value"));
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
		return check;
	}
	
	public List<Check> findByKindId(String kindId) throws Exception {
		Statement stmt;
		String queryStr="select * from chech where kindid='" + kindId + "'";
		ResultSet rs;
		List<Check> checks = new ArrayList<Check>();
		Check check;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				check = new Check();
				check.setId(rs.getInt("id"));
				check.setKindID(rs.getInt("kindid"));
				check.setCreateTime(rs.getString("createtime"));
				check.setNumber(rs.getInt("number"));
				check.setValue(rs.getInt("value"));
				checks.add(check);
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
		return checks;
	}		
	
	
	public  List<Check> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Check> checks = new ArrayList<Check>();
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
				Check check = new Check();
				check.setId(rs.getInt("id"));
				check.setKindID(rs.getInt("kindid"));
				check.setCreateTime(rs.getString("createtime"));
				check.setNumber(rs.getInt("number"));
				check.setValue(rs.getInt("value"));
				checks.add(check);
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
		return checks;
	}
	public boolean insert(Check check) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag=false;
		String insertStr="insert into check (kindid, createtime, number, value) values(?,?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{
			//String queryStr="select max(kindID) as mkindID from category";
			//Statement qstmt=conn.createStatement();		
			//ResultSet rs=qstmt.executeQuery(queryStr);
			/*if (rs.next())
			{	
				kindID=1+rs.getInt("mkindID");			
				category.setKindID(kindID);
			}*/
			stmt.setInt(1, check.getKindID());
			stmt.setString(2, check.getCreateTime());
			stmt.setInt(3, check.getNumber());
			stmt.setInt(4, check.getValue());
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
	public  boolean update(Check check) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update check set number = ?, value = ? where id = ?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setInt(1, check.getNumber());
			stmt.setInt(2, check.getValue());
			stmt.setInt(3, check.getId());
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
		PreparedStatement stmt;
		boolean deleteFlag=false;
		try
		{
	    	String deleteStr="delete from check where id = ?";
			stmt =conn.prepareStatement(deleteStr);
			stmt.setInt(1, id);
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