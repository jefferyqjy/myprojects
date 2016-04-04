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


	public class PartDAO {
		private Connection conn;	
		public PartDAO()
		{
			ConnOfDatabase sqlconn;
			sqlconn=new ConnOfDatabase();
			conn=sqlconn.getConn();	
		}	
		public List<Part> findAll() throws Exception
		{
			Statement stmt;
			String queryStr="select partID,partname,partstandard,partpackaging,part.kindID,kindName from part,category where part.kindID=category.kindID";
			ResultSet rs;
			List<Part> parts=new ArrayList<Part>();
			Part part;
			stmt =conn.createStatement();	
			try
			{
				rs=stmt.executeQuery(queryStr);
				while (rs.next())
				{
					part=new Part();
					part.setPartID(rs.getString("partID"));
					part.setPartname(rs.getString("partname"));
					part.setPartstandard(rs.getString("partstandard"));
					part.setPartpackaging(rs.getString("partpackaging"));
					part.setKindID(rs.getInt("part.kindID"));
					part.setKindName(rs.getString("kindName"));
					parts.add(part);
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
			return parts;
		}
		/**
		 * 根据partID获取当前库存量
		 * @param partID
		 * @return
		 * @throws SQLException 
		 */
		public int findCurrStoreNumById(String partID) throws SQLException{
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt =conn.createStatement();	
			int StoreNum=0;
			try
			{
				queryStr="select totalpartrknum-ifnull(totalpartssnum,0)-ifnull(totalchecknum,0) as partkcnum from (select partID,sum(partrknum) as totalpartrknum from partrkmingxi group by partID) pmx " +
						"left join (select partID,sum(partssnum) as totalpartssnum from partssmingxi group by partID) psmx on pmx.partID=psmx.partID " +
						"left join (select partID,sum(checknum) as totalchecknum from arrange group by partID) a on psmx.partID=a.partID " +
						"where pmx.partID='"+partID+"'";
				rs=stmt.executeQuery(queryStr);
		        if (rs.next())
		        {
		        	StoreNum=rs.getInt("partkcnum");
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
			return StoreNum;
		}
		public  Part findById(String partID) throws Exception
		{
			PreparedStatement stmt = null;
			String queryStr = "";
			ResultSet rs;	
			Part part=new Part();
			try
			{
				queryStr="select partID,partname,partstandard,partpackaging,part.kindID,kindName from part,category where part.kindID=category.kindID and partID=?";
				stmt = conn.prepareStatement(queryStr);
				stmt.setString(1, partID);
				rs = stmt.executeQuery();
		        if (rs.next())
		        {
		        	part=new Part();
					part.setPartID(rs.getString("partID"));
					part.setPartname(rs.getString("partname"));
					part.setPartstandard(rs.getString("partstandard"));
					part.setPartpackaging(rs.getString("partpackaging"));
					part.setKindID(rs.getInt("part.kindID"));
					part.setKindName(rs.getString("kindName"));
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
			return part;
		}
		
		public  Part findByPartname(String partname) throws Exception
		{
			PreparedStatement stmt = null;
			String queryStr = "";
			ResultSet rs;	
			Part part=null;
			try
			{
				queryStr="select partID,partname from part where partname=?";
				stmt = conn.prepareStatement(queryStr);
				stmt.setString(1, partname);
				rs = stmt.executeQuery();
		        if (rs.next())
		        {
		        	part=new Part();
					part.setPartID(rs.getString("partID"));
					part.setPartname(rs.getString("partname"));
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
			return part;
		}
		
		
		public List<Part> findByKindID(int id) throws Exception
		{
			Statement stmt;
			String queryStr="select partID,partname,partstandard,partpackaging,part.kindID,kindName from part,category where part.kindID=category.kindID and part.kindID="+id;
			ResultSet rs;
			List<Part> parts=new ArrayList<Part>();
			Part part;
			stmt =conn.createStatement();	
			try
			{
				rs=stmt.executeQuery(queryStr);
				//int len=rs.getRow();
				while (rs.next())
				{
					part=new Part();
					part.setPartID(rs.getString("partID"));
					part.setPartname(rs.getString("partname"));
					part.setPartstandard(rs.getString("partstandard"));
					part.setPartpackaging(rs.getString("partpackaging"));
					part.setKindID(rs.getInt("part.kindID"));
					part.setKindName(rs.getString("kindName"));
					parts.add(part);
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
			return parts;
		}
		public  List<Part> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
		{
			List<Part> parts= new ArrayList<Part>();
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
					Part part=new Part();
					part.setPartID(rs.getString("partID"));
					part.setPartname(rs.getString("partname"));
					part.setPartstandard(rs.getString("partstandard"));
					part.setPartpackaging(rs.getString("partpackaging"));
					part.setKindID(rs.getInt("part.kindID"));
					part.setKindName(rs.getString("category.kindName"));
					parts.add(part);
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
			return parts;
		}
		//public abstract <T> boolean insert(T t) throws Exception;
		public boolean insert(Part part) throws Exception
		{
			PreparedStatement stmt;
			boolean insFlag = false;
			String insertStr = "insert into part values(?,?,?,?,?)";

			stmt = conn.prepareStatement(insertStr);
			try {
				String queryStr = "select max(partID) as mpartId from part";
				Statement qstmt = conn.createStatement();
				ResultSet rs = qstmt.executeQuery(queryStr);
				if (rs.next()) {
					String partID = rs.getString("mpartId");// maxId="A12334"；
					if (partID != null && partID != "") {
						String idValue = partID.substring(1);// 得到12334 zheliwenyi
																// K10000002 多了一个K
																// 少截取了个K
						int val = Integer.parseInt(idValue) + 1;
						String id = "P" + val;
						part.setPartID(id);
					} else {
						String id = "P" + 1;
						part.setPartID(id);
					}
					
					stmt.setString(1, part.getPartID());
					stmt.setString(2, part.getPartname());
					stmt.setString(3, part.getPartstandard());
					stmt.setString(4, part.getPartpackaging());				
					stmt.setInt(5, part.getKindID());
					
					stmt.executeUpdate();
					insFlag = true;
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
			return insFlag;
		}
		
		public  boolean update(Part part) throws Exception
		{
			PreparedStatement stmt;
			boolean updateFlag=false;
			String updateStr="update part set partname=?,partstandard=?,partpackaging=?,kindID=? where partID=?";
			stmt =conn.prepareStatement(updateStr);	
			try
			{
				stmt.setString(1,part.getPartname());
				stmt.setString(2,part.getPartstandard());
				stmt.setString(3,part.getPartpackaging());
				stmt.setInt(4,part.getKindID());
				stmt.setString(5,part.getPartID());
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
		public boolean deleteById(String id) throws Exception
		{
			String partId=id;
			PreparedStatement stmt;
			boolean deleteFlag=false;
			try
			{
		    	String deleteStr="delete from part where partID=?";
				stmt =conn.prepareStatement(deleteStr);
				stmt.setString(1,partId);
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

