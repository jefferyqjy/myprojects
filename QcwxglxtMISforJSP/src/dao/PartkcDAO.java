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

public class PartkcDAO {
	private  Connection conn;

	public PartkcDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}
	
	public List<Partkc> findAll() throws Exception
	{
		Statement stmt;
		String queryStr="select partkcID,partID,partname,partstandard,partpackaging,partkcnum from partkc";
		ResultSet rs;
		List<Partkc> partkcs=new ArrayList<Partkc>();
		Partkc partkc;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
				partkc=new Partkc();
				partkc.setPartkcID(rs.getInt("partkcID"));
				partkc.setPartID(rs.getString("partID"));
				partkc.setPartname(rs.getString("partname"));
				partkc.setPartstandard(rs.getString("partstandard"));
				partkc.setPartpackaging(rs.getString("partpackaging"));
				partkc.setPartkcnum(rs.getDouble("partkcnum"));
				partkcs.add(partkc);
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
		return partkcs;
	}
	public int findCurrStoreNumById(String partID) throws SQLException{
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();	
		int StoreNum=0;
		try
		{
			queryStr="select * from partkc where partID='"+partID+"'";
			rs=stmt.executeQuery(queryStr);
	        if (rs.next())
	        {
	        	StoreNum=rs.getInt("partkcnum");
	    	}

		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return StoreNum;
	}
	public  Partkc findBypartId(String partID) throws Exception
	{
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;	
		Partkc partkc=new Partkc();
		try
		{
			queryStr="select partkcID,partID,partname,partstandard,partpackaging,partkcnum from partkc where partID=?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, partID);
			rs = stmt.executeQuery();
	        if (rs.next())
	        {
	        	partkc=new Partkc();
	        	partkc.setPartkcID(rs.getInt("partkcID"));
				partkc.setPartID(rs.getString("partID"));
				partkc.setPartname(rs.getString("partname"));
				partkc.setPartstandard(rs.getString("partstandard"));
				partkc.setPartpackaging(rs.getString("partpackaging"));
				partkc.setPartkcnum(rs.getDouble("partkcnum"));
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return partkc;
	}
	public  Partkc findById(int partkcID) throws Exception
	{
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;	
		Partkc partkc=new Partkc();
		try
		{
			queryStr="select partkcID,partID,partname,partstandard,partpackaging,partkcnum from partkc";
			stmt = conn.prepareStatement(queryStr);
			stmt.setInt(1, partkcID);
			rs = stmt.executeQuery();
	        if (rs.next())
	        {
	        	partkc=new Partkc();
	        	partkc.setPartkcID(rs.getInt("partkcID"));
				partkc.setPartID(rs.getString("partID"));
				partkc.setPartname(rs.getString("partname"));
				partkc.setPartstandard(rs.getString("partstandard"));
				partkc.setPartpackaging(rs.getString("partpackaging"));
				partkc.setPartkcnum(rs.getDouble("partkcnum"));
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
		return partkc;
	}
	private StringBuffer sbPartID=new StringBuffer();
	public String getNowPartIds(){
		return sbPartID.toString();
	}
	public  List<Partkc> findBypartByIds(String ids) throws SQLException{
		List<Partkc> resultList=null;
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();
		try
		{
			queryStr="select partkcID,partID,partname,partkcnum,partstandard,partpackaging " +
					"from partkc where partID in("+ids+")";
			rs=stmt.executeQuery(queryStr);
			resultList=new ArrayList<Partkc>();
	        while (rs.next())
	        {
	        	Partkc partkc=new Partkc();
	        	partkc.setPartkcID(rs.getInt("partkcID"));
				partkc.setPartID(rs.getString("partID"));
				partkc.setPartname(rs.getString("partname"));
				partkc.setPartstandard(rs.getString("partstandard"));
				partkc.setPartpackaging(rs.getString("partpackaging"));
				partkc.setPartkcnum(rs.getDouble("partkcnum"));
				sbPartID.append(partkc.getPartID()+",");
				resultList.add(partkc);
	    	}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public List<Partkc> findByPage(String queryStr, Integer currentPage,Integer pageCount) throws Exception {
		List<Partkc> partkcs = new ArrayList<Partkc>();
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_READ_ONLY);

		try {
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				rs.beforeFirst();
				if ((currentPage - 1) * pageCount > 0) {
					// 移动结果集数据到当前页
					rs.absolute((currentPage - 1) * pageCount);
				}
				if (currentPage == 1) {
					rs.beforeFirst();
				}
				int i = 0; // Readed pages
				while (rs.next() && i < pageCount) {
					i++;
					Partkc partkc = new Partkc();
					partkc.setPartkcID(rs.getInt("partkcID"));
					partkc.setPartID(rs.getString("partID"));
					partkc.setPartname(rs.getString("partname"));
					partkc.setPartstandard(rs.getString("partstandard"));
					partkc.setPartpackaging(rs.getString("partpackaging"));
					partkc.setPartkcnum(rs.getDouble("partkcnum"));
					partkcs.add(partkc);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		return partkcs;
	}
	public boolean insert(Partkc partkc) throws Exception
	{
		PreparedStatement stmt;
		int partkcID=0;
		boolean insFlag=false;
		String insertStr="insert into partkc values(?,?,?,?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{
			String queryStr="select max(partkcID) as mpartkcId from partkc";
			Statement qstmt=conn.createStatement();		
			ResultSet rs=qstmt.executeQuery(queryStr);
			if (rs.next())
			{	
				partkcID=1+rs.getInt("mpartkcId");			
				partkc.setPartkcID(partkcID);
			}	
			stmt.setInt(1,partkc.getPartkcID());
			stmt.setString(2,partkc.getPartID());
			stmt.setString(3,partkc.getPartname());
			stmt.setString(4, partkc.getPartstandard());
			stmt.setString(5, partkc.getPartpackaging());
			stmt.setDouble(6, partkc.getPartkcnum());	
			
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
		}
		return insFlag;
	}
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
		} 
	}
	
	public  boolean update(Partkc partkc) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update partkc set partID=?,partname=?,partstandard=?,partpackaging=?,partkcnum=? where partkcID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1,partkc.getPartID());
			stmt.setString(2,partkc.getPartname());
			stmt.setString(3, partkc.getPartstandard());
			stmt.setString(4, partkc.getPartpackaging());
			stmt.setDouble(5, partkc.getPartkcnum());	
			stmt.setInt(6,partkc.getPartkcID());
			
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
		}
		
		return updateFlag;
	}
	public boolean deleteById(int id) throws Exception
	{
		int partkcId=id;
		PreparedStatement stmt;
		boolean deleteFlag=false;
		try
		{
	    	String deleteStr="delete from partkc where partkcID=?";
			stmt =conn.prepareStatement(deleteStr);
			stmt.setInt(1,partkcId);
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
