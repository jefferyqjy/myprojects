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


	public class AdminDAO {
		private Connection conn;	
		public AdminDAO()
		{
			ConnOfDatabase sqlconn;
			sqlconn=new ConnOfDatabase();
			conn=sqlconn.getConn();	
		}	
		public List<Admin> findAll() throws Exception
		{
			Statement stmt;
			String queryStr="select * from admin";
			ResultSet rs;
			List<Admin> admins=new ArrayList<Admin>();
			Admin admin;
			stmt =conn.createStatement();	
			try
			{
				rs=stmt.executeQuery(queryStr);
				while (rs.next())
				{
					admin=new Admin();
					admin.setAdmID(rs.getInt("admID"));
					admin.setAdmName(rs.getString("admname"));
					admin.setAdmpwd(rs.getString("admpwd"));
					admin.setSex(rs.getString("sex"));
					admin.setAdmphone(rs.getString("admphone"));
					admin.setAdmadd(rs.getString("admadd"));
		        	admins.add(admin);
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
			return admins;
		}	
		public boolean CheckPassword(int admID, String password) throws SQLException{
			boolean insFlag = false;
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt=conn.prepareStatement(queryStr);
			try
			{
				queryStr="select * from admin where admID='"+admID+"' and admpwd='"+password+"'";
				rs=stmt.executeQuery(queryStr);

				while (rs.next()) {
					insFlag=true;
				}
				
			}catch (SQLException e)
			{
				e.printStackTrace();
			}
			return insFlag;
		}	
		
		public Admin findByadminName(String admName) throws Exception
		{
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt =conn.createStatement();	
			Admin admin=new Admin();
			try
			{
				queryStr="select * from admin where admName='"+admName+"'";
				rs=stmt.executeQuery(queryStr);

		        if (rs.next())
		        {
		        	admin.setAdmID(rs.getInt("admID"));
					admin.setAdmName(rs.getString("admname"));
					admin.setAdmpwd(rs.getString("admpwd"));
					admin.setSex(rs.getString("sex"));
					admin.setAdmphone(rs.getString("admphone"));
					admin.setAdmadd(rs.getString("admadd"));
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
			return admin;
		}	
		
		
		public  Admin findById(int id) throws Exception
		{
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt =conn.createStatement();	
			Admin admin=new Admin();
			try
			{
				queryStr="select * from admin where admID="+id;
				rs=stmt.executeQuery(queryStr);

		        if (rs.next())
		        {
		        	admin.setAdmID(rs.getInt("admID"));
					admin.setAdmName(rs.getString("admname"));
					admin.setAdmpwd(rs.getString("admpwd"));
					admin.setSex(rs.getString("sex"));
					admin.setAdmphone(rs.getString("admphone"));
					admin.setAdmadd(rs.getString("admadd"));
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
			return admin;
		}
		
		
		public  List<Admin> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
		{
			List<Admin> admins= new ArrayList<Admin>();
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
					Admin admin=new Admin();
					admin.setAdmID(rs.getInt("admID"));
					admin.setAdmName(rs.getString("admname"));
					admin.setAdmpwd(rs.getString("admpwd"));
					admin.setSex(rs.getString("sex"));
					admin.setAdmphone(rs.getString("admphone"));
					admin.setAdmadd(rs.getString("admadd"));
					admins.add(admin);
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
			return admins;
		}
		//public abstract <T> boolean insert(T t) throws Exception;
		public boolean insert(Admin admin) throws Exception
		{
			PreparedStatement stmt;
			int admID=0;
			boolean insFlag=false;
			String insertStr="insert into admin values(?,?,?,?,?,?)";
			stmt =conn.prepareStatement(insertStr);
			try
			{
				String queryStr="select max(admID) as madmId from admin";
				Statement qstmt=conn.createStatement();		
				ResultSet rs=qstmt.executeQuery(queryStr);
				if (rs.next())
				{	
					admID=1+rs.getInt("madmId");			
					admin.setAdmID(admID);
				}	
				stmt.setInt(1,admin.getAdmID());
				stmt.setString(2,admin.getAdmName());
				stmt.setString(3,admin.getAdmpwd());
				stmt.setString(4,admin.getSex());
				stmt.setString(5,admin.getAdmphone());
				stmt.setString(6,admin.getAdmadd());
				
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
		
		public  boolean update(Admin adm) throws Exception
		{
			PreparedStatement stmt;
			boolean updateFlag=false;
			String updateStr="update admin set admName=?,sex=?,admphone=?,admadd=? where admID=?";
			stmt =conn.prepareStatement(updateStr);	
			try
			{
				
				stmt.setString(1,adm.getAdmName());
				stmt.setString(2,adm.getSex());
				stmt.setString(3,adm.getAdmphone());
				stmt.setString(4,adm.getAdmadd());
				stmt.setInt(5,adm.getAdmID());
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
		public  boolean Update(int admID,String admpwd ) throws Exception
		{
			PreparedStatement stmt;
			boolean updateFlag=false;
			String updateStr="update admin set admpwd=? where admID=?";
			stmt =conn.prepareStatement(updateStr);	
			try
			{
				stmt.setString(1,admpwd);		
				stmt.setInt(2,admID);
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
			int admId=id;
			PreparedStatement stmt;
			boolean deleteFlag=false;
			try
			{
		    	String insertStr="delete from admin where admID=?";
				stmt =conn.prepareStatement(insertStr);
				stmt.setInt(1,admId);
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

