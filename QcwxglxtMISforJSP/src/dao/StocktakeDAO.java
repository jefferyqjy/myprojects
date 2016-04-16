package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnOfDatabase;
import bean.Stocktake;

public class StocktakeDAO {
	private Connection conn;	
	public StocktakeDAO() {
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}	
	
	public List<Stocktake> findAll() throws Exception {
		Statement stmt;
		String queryStr="select * from stocktake";
		ResultSet rs;//遍历结果集
		List<Stocktake> stocktakes = new ArrayList<Stocktake>();//创建容器，存储对象为Category
		Stocktake stocktake;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);//执行sql语句
			//int len=rs.getRow();
			while (rs.next())//遍历结果集，当结果到达结尾，没有多余结果就退出
			{
				stocktake=new Stocktake();//初始化结果对象
				stocktake.setId(rs.getInt("id"));
				stocktake.setKindID(rs.getInt("kindid"));//这里是将数据库查询的数据集存储到对象中，然后放入结果集
				stocktake.setCreateTime(rs.getString("createtime"));
				stocktake.setNumber(rs.getInt("number"));
				stocktake.setValue(rs.getInt("value"));
				stocktakes.add(stocktake);
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
		return stocktakes;
	}
	
	public Stocktake findById(int id) throws Exception {
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Stocktake stocktake = new Stocktake();
		
		try
		{
			queryStr="select * from stocktake where id = " + id;
			rs=stmt.executeQuery(queryStr);
			//int len=rs.getRow();
			while (rs.next())
			{
				stocktake.setId(rs.getInt("id"));
				stocktake.setKindID(rs.getInt("kindid"));
				stocktake.setCreateTime(rs.getString("createtime"));
				stocktake.setNumber(rs.getInt("number"));
				stocktake.setValue(rs.getInt("value"));
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
		return stocktake;
	}
	
	public List<Stocktake> findByKindId(String kindId) throws Exception {
		Statement stmt;
		String queryStr="select * from chech where kindid='" + kindId + "'";
		ResultSet rs;
		List<Stocktake> stocktakes = new ArrayList<Stocktake>();
		Stocktake stocktake;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				stocktake = new Stocktake();
				stocktake.setId(rs.getInt("id"));
				stocktake.setKindID(rs.getInt("kindid"));
				stocktake.setCreateTime(rs.getString("createtime"));
				stocktake.setNumber(rs.getInt("number"));
				stocktake.setValue(rs.getInt("value"));
				stocktakes.add(stocktake);
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
		return stocktakes;
	}		
	
	
	public  List<Stocktake> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Stocktake> stocktakes = new ArrayList<Stocktake>();
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
				Stocktake stocktake = new Stocktake();
				stocktake.setId(rs.getInt("id"));
				stocktake.setKindID(rs.getInt("kindid"));
				stocktake.setCreateTime(rs.getString("createtime"));
				stocktake.setNumber(rs.getInt("number"));
				stocktake.setValue(rs.getInt("value"));
				stocktakes.add(stocktake);
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
		return stocktakes;
	}
	public boolean insert(Stocktake stocktake) throws Exception
	{
		PreparedStatement stmt;
		boolean insFlag=false;
		String insertStr="insert into stocktake (kindid, createtime, number, value) values(?,?,?,?)";
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
			stmt.setInt(1, stocktake.getKindID());
			stmt.setString(2, stocktake.getCreateTime());
			stmt.setInt(3, stocktake.getNumber());
			stmt.setInt(4, stocktake.getValue());
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
	public  boolean update(Stocktake stocktake) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update stocktake set number = ?, value = ? where id = ?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setInt(1, stocktake.getNumber());
			stmt.setInt(2, stocktake.getValue());
			stmt.setInt(3, stocktake.getId());
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
	    	String deleteStr="delete from stocktake where id = ?";
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