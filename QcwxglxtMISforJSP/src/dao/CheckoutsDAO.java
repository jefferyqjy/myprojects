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


public class CheckoutsDAO {
	private Connection conn;	
	public CheckoutsDAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}	
		public List<Checkouts> findAll() throws Exception
		{
			Statement stmt;
			String queryStr="select cID,checkouts.aID,repaircost,partID,partname,checknum,partprice,partcost,xiaofei,checkoutsdate,beizhu from arrange,checkouts where arrange.aID=checkouts.aID";
			ResultSet rs;
			List<Checkouts> checkoutss=new ArrayList<Checkouts>();
			Checkouts checkouts;
			stmt =conn.createStatement();	
			try
			{
				rs=stmt.executeQuery(queryStr);
				while (rs.next())
				{
					checkouts=new Checkouts();
					checkouts.setcID(rs.getInt("cID"));
					checkouts.setaID(rs.getInt("checkouts.aID"));
					checkouts.setRepaircost(rs.getDouble("repaircost"));
					checkouts.setPartID(rs.getString("partID"));
					checkouts.setPartname(rs.getString("partname"));
					checkouts.setChecknum(rs.getDouble("checknum"));
					checkouts.setPartprice(rs.getDouble("partprice"));
					checkouts.setPartcost(rs.getDouble("partcost"));
					checkouts.setXiaofei(rs.getDouble("xiaofei"));
					checkouts.setCheckoutsdate(rs.getString("checkoutsdate"));
					checkouts.setBeizhu(rs.getString("beizhu"));
					checkoutss.add(checkouts);
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
			return checkoutss;
		}	
		
		public  Checkouts findById(int id) throws Exception
		{
			PreparedStatement stmt = null;
			String queryStr = "";
			ResultSet rs;
			Checkouts checkouts=null;
			try
			{
				queryStr="select cID,checkouts.aID,repaircost,partID,partname,checknum,partprice,partcost,xiaofei,checkoutsdate,beizhu from arrange,checkouts where arrange.aID=checkouts.aID and  cID=?";
				stmt = conn.prepareStatement(queryStr);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();

		        if (rs.next())
		        {
		        	checkouts=new Checkouts();
		        	checkouts.setcID(rs.getInt("cID"));
					checkouts.setaID(rs.getInt("checkouts.aID"));
					checkouts.setRepaircost(rs.getDouble("repaircost"));
					checkouts.setPartID(rs.getString("partID"));
					checkouts.setPartname(rs.getString("partname"));
					checkouts.setChecknum(rs.getDouble("checknum"));
					checkouts.setPartprice(rs.getDouble("partprice"));
					checkouts.setPartcost(rs.getDouble("partcost"));
					checkouts.setXiaofei(rs.getDouble("xiaofei"));
					checkouts.setCheckoutsdate(rs.getString("checkoutsdate"));
					checkouts.setBeizhu(rs.getString("beizhu"));
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
			return checkouts;
		}
		public  Checkouts findByaId(int id) throws Exception{
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt =conn.createStatement();
		   Checkouts checkouts = new Checkouts();

		try {
			queryStr = "select cID,checkouts.aID,repaircost,partID,partname,checknum,partprice,xiaofei,checkoutsdate,beizhu from arrange,checkouts where arrange.aID=checkouts.aID and checkouts.aID=" +id;
			rs=stmt.executeQuery(queryStr);
			if (rs.next()) {
	        	checkouts.setaID(rs.getInt("checkouts.aID"));
	        	checkouts.setPartID(rs.getString("partID"));
				checkouts.setPartname(rs.getString("partname"));
				checkouts.setChecknum(rs.getDouble("checknum"));
				checkouts.setPartprice(rs.getDouble("partprice"));
				checkouts.setPartcost(rs.getDouble("partcost"));
				checkouts.setXiaofei(rs.getDouble("xiaofei"));
				checkouts.setCheckoutsdate(rs.getString("checkoutsdate"));
				checkouts.setBeizhu(rs.getString("beizhu"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return checkouts;
	}
		
		public  List<Checkouts> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
		{
			List<Checkouts> checkoutss= new ArrayList<Checkouts>();
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
					Checkouts checkouts=new Checkouts();
					checkouts=new Checkouts();
					checkouts.setcID(rs.getInt("cID"));
					checkouts.setaID(rs.getInt("checkouts.aID"));
					checkouts.setRepaircost(rs.getDouble("repaircost"));
					checkouts.setPartID(rs.getString("partID"));
					checkouts.setPartname(rs.getString("partname"));
					checkouts.setChecknum(rs.getDouble("checknum"));
					checkouts.setPartprice(rs.getDouble("partprice"));
					checkouts.setPartcost(rs.getDouble("partcost"));
					checkouts.setXiaofei(rs.getDouble("xiaofei"));
					checkouts.setCheckoutsdate(rs.getString("checkoutsdate"));
					checkouts.setBeizhu(rs.getString("beizhu"));
					checkoutss.add(checkouts);
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
			return checkoutss;
		}
		
		public  List<Checkouts> findByPagecus(String queryStr, Integer currentPage, Integer pageCount) throws Exception
		{
			List<Checkouts> checkoutss= new ArrayList<Checkouts>();
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
					Checkouts checkouts=new Checkouts();
					checkouts=new Checkouts();
					checkouts.setcID(rs.getInt("cID"));
					checkouts.setaID(rs.getInt("checkouts.aID"));
					checkouts.setRepaircost(rs.getDouble("repaircost"));
					checkouts.setPartID(rs.getString("partID"));
					checkouts.setPartname(rs.getString("partname"));
					checkouts.setChecknum(rs.getDouble("checknum"));
					checkouts.setPartprice(rs.getDouble("partprice"));
					checkouts.setPartcost(rs.getDouble("partcost"));
					checkouts.setXiaofei(rs.getDouble("xiaofei"));
					checkouts.setCheckoutsdate(rs.getString("checkoutsdate"));
					checkouts.setBeizhu(rs.getString("beizhu"));
					checkoutss.add(checkouts);
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
			return checkoutss;
		}
		//public abstract <T> boolean insert(T t) throws Exception;
		public boolean insert(Checkouts checkouts) throws Exception
		{
			PreparedStatement stmt;
			int cID=0;
			boolean insFlag=false;
			String insertStr="insert into checkouts values(?,?,?,?,?,?,?,?)";
			stmt =conn.prepareStatement(insertStr);
			try
			{
				String queryStr="select max(cID) as mcId from checkouts";
				Statement qstmt=conn.createStatement();		
				ResultSet rs=qstmt.executeQuery(queryStr);
				if (rs.next())
				{	
					cID=1+rs.getInt("mcId");			
					checkouts.setcID(cID);
				}	
				stmt.setInt(1,checkouts.getcID());
				stmt.setInt(2,checkouts.getaID());
				stmt.setDouble(3,checkouts.getPartprice());
				stmt.setDouble(4,checkouts.getPartcost());
				stmt.setDouble(5,checkouts.getRepaircost());
				stmt.setDouble(6,checkouts.getXiaofei());
				stmt.setString(7,checkouts.getCheckoutsdate());
				stmt.setString(8,checkouts.getBeizhu());
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

		public  boolean update(Checkouts checkouts) throws Exception
		{
			PreparedStatement stmt;
			boolean updateFlag=false;
			String updateStr="update checkouts set aID=?,partprice=?,partcost=?,repaircost=?,xiaofei=?,checkoutsdate=?,beizhu=? where cID=?";
			stmt =conn.prepareStatement(updateStr);	
			try
			{
				stmt.setInt(1,checkouts.getaID());
				stmt.setDouble(2,checkouts.getPartprice());
				stmt.setDouble(3,checkouts.getPartcost());
				stmt.setDouble(4,checkouts.getRepaircost());
				stmt.setDouble(5,checkouts.getXiaofei());
				stmt.setString(6,checkouts.getCheckoutsdate());
				stmt.setString(7,checkouts.getBeizhu());
				stmt.setInt(8,checkouts.getcID());
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
			int cId=id;
			PreparedStatement stmt;
			boolean deleteFlag=false;
			try
			{
		    	String insertStr="delete from checkouts where cID=?";
				stmt =conn.prepareStatement(insertStr);
				stmt.setInt(1,cId);
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

