package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnOfDatabase;
import bean.Supplier;

public class SupplierDAO {

	
	private Connection conn;	
	public SupplierDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}	
	public List<Supplier> findAll() throws Exception
	{
		Statement stmt;
		String queryStr="select supID,supname,supadd,supphone from supplier";
		ResultSet rs;
		List<Supplier> suppliers=new ArrayList<Supplier>();
		Supplier supplier;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				supplier =new Supplier();
				supplier.setSupID(rs.getInt("supID"));
				supplier.setSupName(rs.getString("supname"));
				supplier.setSupadd(rs.getString("supadd"));
				supplier.setSupphone(rs.getString("supphone"));	
				suppliers.add(supplier);
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
		return suppliers;
	}
	public Supplier findById(int id) throws Exception
	{
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		Supplier supplier=new Supplier();
		
		try
		{
			queryStr="select supID,supname,supadd,supphone from supplier where supID="+id;
			rs=stmt.executeQuery(queryStr);

	        if (rs.next())
	        {
	        
	        	supplier.setSupID(rs.getInt("supID"));
				supplier.setSupName(rs.getString("supname"));
				supplier.setSupadd(rs.getString("supadd"));
				supplier.setSupphone(rs.getString("supphone"));
				
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
		return supplier;
	}
	public List<Supplier> findBySupname(String Supname) throws Exception
	{
		Statement stmt;
		String queryStr="select * from supplier where supname='"+Supname+"'";
		ResultSet rs;
		List<Supplier> suppliers=new ArrayList<Supplier>();
		Supplier supplier;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				supplier =new Supplier();
				supplier.setSupID(rs.getInt("supID"));
				supplier.setSupName(rs.getString("supname"));
				supplier.setSupadd(rs.getString("supadd"));
				supplier.setSupphone(rs.getString("supphone"));	
				suppliers.add(supplier);
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
		return suppliers;
	}		
	
	public List<Supplier> findBySupadd(String Supadd) throws Exception
	{
		Statement stmt;
		String queryStr="select * from supplier where supname='"+Supadd+"'";
		ResultSet rs;
		List<Supplier> suppliers=new ArrayList<Supplier>();
		Supplier supplier;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				supplier =new Supplier();
				supplier.setSupID(rs.getInt("supID"));
				supplier.setSupName(rs.getString("supname"));
				supplier.setSupadd(rs.getString("supadd"));
				supplier.setSupphone(rs.getString("supphone"));	
				suppliers.add(supplier);
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
		return suppliers;
	}		
	public  List<Supplier> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
	{
		List<Supplier> suppliers= new ArrayList<Supplier>();
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
				Supplier supplier=new Supplier();
				supplier.setSupID(rs.getInt("supID"));
				supplier.setSupName(rs.getString("supname"));
				supplier.setSupadd(rs.getString("supadd"));
				supplier.setSupphone(rs.getString("supphone"));	
				suppliers.add(supplier);
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
		return suppliers;
	}
	//public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Supplier supplier) throws Exception
	{
		PreparedStatement stmt;
		int supID=0;
		boolean insFlag=false;
		String insertStr="insert into supplier values(?,?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{
			String queryStr="select max(supID) as msupId from supplier";
			Statement qstmt=conn.createStatement();		
			ResultSet rs=qstmt.executeQuery(queryStr);
			if (rs.next())
			{	
				supID=1+rs.getInt("msupId");			
				supplier.setSupID(supID);
			}
			stmt.setInt(1,supplier.getSupID());
			stmt.setString(2,supplier.getSupName());
			stmt.setString(3, supplier.getSupadd());
			stmt.setString(4,supplier.getSupphone());
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
	
	public  boolean update(Supplier supplier) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update supplier set supname=?,supadd=?,supphone=? where supID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1,supplier.getSupName());
			stmt.setString(2, supplier.getSupadd());
			stmt.setString(3,supplier.getSupphone());
			stmt.setInt(4,supplier.getSupID());
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
		int supId=id;
		PreparedStatement stmt;
		boolean deleteFlag=false;
		try
		{
	    	String insertStr="delete from supplier where supID=?";
			stmt =conn.prepareStatement(insertStr);
			stmt.setInt(1,supId);
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

