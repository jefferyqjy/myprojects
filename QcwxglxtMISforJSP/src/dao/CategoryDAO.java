package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnOfDatabase;
import bean.Category;

public class CategoryDAO {
	private Connection conn;	
	public CategoryDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}	
	public List<Category> findAll() throws Exception
	{
		Statement stmt;
		String queryStr="select * from category";
		ResultSet rs;//遍历结果集
		List<Category> categorys=new ArrayList<Category>();//创建容器，存储对象为Category
		Category category;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);//执行sql语句
			//int len=rs.getRow();
			while (rs.next())//遍历结果集，当结果到达结尾，没有多余结果就退出
			{
				category=new Category();//初始化结果对象
				category.setKindID(rs.getInt("kindId"));//这里是将数据库查询的数据集存储到对象中，然后放入结果集
				category.setKindName(rs.getString("kindName"));
				category.setDescription(rs.getString("description"));
				categorys.add(category);
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
		return categorys;
	}
	
	public Category findById(int id) throws Exception
	{
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Category category=new Category();
		
		try
		{
			queryStr="select * from category where kindID="+id;
			rs=stmt.executeQuery(queryStr);
			//int len=rs.getRow();
			while (rs.next())
			{
				
				category.setKindID(rs.getInt("kindID"));
				category.setKindName(rs.getString("kindName"));
				category.setDescription(rs.getString("description"));	
			
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
		return category;
	}
	public List<Category> findByKindName(String KindName) throws Exception
	{
		Statement stmt;
		String queryStr="select * from category where kindName='"+KindName+"'";
		ResultSet rs;
		List<Category> categorys=new ArrayList<Category>();
		Category category;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				category=new Category();
				category.setKindID(rs.getInt("kindID"));
				category.setKindName(rs.getString("kindName"));
				category.setDescription(rs.getString("description"));
				categorys.add(category);
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
		return categorys;
	}		
	
	
	public  List<Category> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Category> categorys= new ArrayList<Category>();
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
				Category category=new Category();
				category.setKindID(rs.getInt("kindID"));
				category.setKindName(rs.getString("kindName"));
				category.setDescription(rs.getString("description"));
				categorys.add(category);
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
		return categorys;
	}
	public boolean insert(Category category) throws Exception
	{
		PreparedStatement stmt;
		int kindID=0;
		boolean insFlag=false;
		String insertStr="insert into category values(?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{
			String queryStr="select max(kindID) as mkindID from category";
			Statement qstmt=conn.createStatement();		
			ResultSet rs=qstmt.executeQuery(queryStr);
			if (rs.next())
			{	
				kindID=1+rs.getInt("mkindID");			
				category.setKindID(kindID);
			}
			stmt.setInt(1,category.getKindID());
			stmt.setString(2,category.getKindName());
			stmt.setString(3,category.getDescription());
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
	public  boolean update(Category category) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update category set kindName=?,description=? where kindID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1,category.getKindName());
			stmt.setString(2,category.getDescription());
			stmt.setInt(3,category.getKindID());
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
		int kindId=id;
		PreparedStatement stmt;
		boolean deleteFlag=false;
		try
		{
	    	String insertStr="delete from category where kindID=?";
			stmt =conn.prepareStatement(insertStr);
			stmt.setInt(1,kindId);
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