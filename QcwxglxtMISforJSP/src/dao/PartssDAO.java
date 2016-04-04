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

public class PartssDAO {
	private Connection conn;

	public PartssDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	public List<Partss> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select partssID,partsstotal,partss.cusID,customer.cusName,ssdate,partss.empID,employee.empName from partss,employee,customer where partss.empID=employee.empID and customer.cusID=partss.cusID order by partssID";
		ResultSet rs;
		List<Partss> partsss = new ArrayList<Partss>();
		Partss partss;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				partss = new Partss();
				partss.setPartssID(rs.getString("partssID"));
				partss.setEmpID(rs.getInt("partss.empID"));
				partss.setEmpName(rs.getString("employee.empName"));
				partss.setCusID(rs.getInt("partss.cusID"));
				partss.setCusName(rs.getString("customer.cusName"));
				partss.setSsdate(rs.getString("ssdate"));
				partss.setPartsstotal(rs.getDouble("partsstotal"));
				partsss.add(partss);
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
		return partsss;
	}

	public Partss findById(String partssID) throws Exception {
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;
		Partss partss = null;
		try {
			queryStr = "select partssID,partsstotal,partss.cusID,customer.cusName,partss.empID,employee.empName,ssdate from partss,employee,customer where partss.empID=employee.empID and customer.cusID=partss.cusID and partssID=?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, partssID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				partss = new Partss();
				partss.setPartssID(rs.getString("partssID"));
				partss.setEmpID(rs.getInt("partss.empID"));
				partss.setEmpName(rs.getString("employee.empName"));
				partss.setCusID(rs.getInt("partss.cusID"));
				partss.setCusName(rs.getString("customer.cusName"));
				partss.setSsdate(rs.getString("ssdate"));
				partss.setPartsstotal(rs.getDouble("partsstotal"));
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
		return partss;
	}
	

	public List<Partssmingxi> findPartssmingxi(String id)throws Exception {
		Statement stmt;
		String queryStr="select partssmingxi.partssID,partssmingxi.partID,partname,partstandard,partpackaging,kindName,partssnum,partssprice,partssnum*partssprice partsstotal from part,partss,partssmingxi,category where partssmingxi.partID=part.partID and partssmingxi.partssID=partss.partssID and category.kindID=part.kindID and partssmingxi.partssID='"+id+"'";
		ResultSet rs;
		List<Partssmingxi> partssmingxis = new ArrayList<Partssmingxi>();
		Partssmingxi partssmingxi;
		stmt =conn.createStatement();	
		try
		{
			rs=stmt.executeQuery(queryStr);
			while (rs.next())
			{
		
				partssmingxi=new Partssmingxi();
				partssmingxi.setPartssID(rs.getString("partssmingxi.partssID"));
				partssmingxi.setPartID(rs.getString("partssmingxi.partID"));
				partssmingxi.setPartname(rs.getString("partname"));
				partssmingxi.setPartstandard(rs.getString("partstandard"));
				partssmingxi.setPartpackaging(rs.getString("partpackaging"));
				partssmingxi.setKindName(rs.getString("kindName"));
				partssmingxi.setPartssnum(rs.getDouble("partssnum"));
				partssmingxi.setPartssprice(rs.getDouble("partssprice"));
				partssmingxi.setPartsstotalmoney(rs.getDouble("partsstotal"));
				partssmingxis.add(partssmingxi);
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
		return partssmingxis;
	}
	
	
	
	public List<Partss> findByPage(String queryStr, Integer currentPage,
			Integer pageCount) throws Exception {
		List<Partss> partsss = new ArrayList<Partss>();
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
					Partss partss = new Partss();
					partss.setPartssID(rs.getString("partss.partssID"));
					partss.setEmpID(rs.getInt("partss.empID"));
					partss.setEmpName(rs.getString("employee.empName"));
					partss.setCusID(rs.getInt("partss.cusID"));
					partss.setCusName(rs.getString("customer.cusName"));
					partss.setSsdate(rs.getString("ssdate"));
					partss.setPartsstotal(rs.getDouble("partsstotal"));
					partsss.add(partss);
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
		return partsss;
	}

	// public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Partss partss) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into partss values(?,?,?,?,?)";

		stmt = conn.prepareStatement(insertStr);
		try {
			String queryStr = "select partssID as mpartssId from partss order by cast(substring(partssID,3) as signed integer) desc limit 1";
			Statement qstmt = conn.createStatement();
			ResultSet rs = qstmt.executeQuery(queryStr);
			if (rs.next()) {
				String partssID = rs.getString("mpartssId");// maxId="A12334"；
				if (partssID != null && partssID != "") {
					String idValue = partssID.substring(2);// 得到12334 zheliwenyi
															// K10000002 多了一个K
															// 少截取了个K
					int val = Integer.parseInt(idValue) + 1;
					String id = "SS" + val;
					partss.setPartssID(id);
				} else {
					String id = "SS" + 1;
					partss.setPartssID(id);
				}
				
				stmt.setString(1, partss.getPartssID());
				stmt.setInt(2, partss.getEmpID());
				stmt.setInt(3, partss.getCusID());
				stmt.setDouble(4, partss.getPartsstotal());				
				stmt.setString(5, partss.getSsdate());
				
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

	public boolean update(Partss partss) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update partss set empID=?,cusID=?,partsstotal=?,ssdate=? where partssID=?";
		stmt = conn.prepareStatement(updateStr);
		try {

			stmt.setInt(1, partss.getEmpID());
			stmt.setInt(2, partss.getCusID());
			stmt.setDouble(3, partss.getPartsstotal());
			stmt.setString(4, partss.getSsdate());
			stmt.setString(5, partss.getPartssID());
			stmt.executeUpdate();
			updateFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally {
//			try {
//				stmt.close();
//			} catch (SQLException e) {
//			}
//			try {
//				conn.close();
//			} catch (SQLException e) {
//			}
//		}

		return updateFlag;
	}

	public boolean deleteById(String id) throws Exception {
		String partssID = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {

			String deleteStr = "delete from partss where partssID=?";
			stmt = conn.prepareStatement(deleteStr);
			stmt.setString(1, partssID);
			stmt.executeUpdate();
			deleteStr = "delete from partssmingxi where partssID=?";
			stmt.setString(1, partssID);
			stmt.executeUpdate();
			deleteFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

		return deleteFlag;
	}

	public boolean insertPartssmingxi(List<Partssmingxi> partssmingxis) throws Exception {
		PreparedStatement stmt=null;
		String partssID = "SS0";
		boolean insFlag=false;
		String insertStr="insert into partssmingxi values(?,?,?,?)";
		stmt =conn.prepareStatement(insertStr);
		try
		{ 
			String queryStr="select partssID as mpartssID from partssmingxi  order by cast(substring(partssID,3) as signed integer) desc limit 1";
			Statement qstmt=conn.createStatement();		
			ResultSet rs=qstmt.executeQuery(queryStr);
			if (rs.next())
				partssID=rs.getString("mpartssID");		
				partssID = partssID.substring(2, partssID.length());
				int val = Integer.parseInt(partssID);
				val = val+1;//加1后的数字
				String newpartssID="SS"+val;
				qstmt.close();
		
				for (int i = 0; i < partssmingxis.size(); i++) 
				{
					stmt.setString(1, newpartssID);
					stmt.setString(2, partssmingxis.get(i).getPartID());
					stmt.setDouble(3, partssmingxis.get(i).getPartssnum());
					stmt.setDouble(4, partssmingxis.get(i).getPartssprice());
					stmt.executeUpdate();
				}
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



	public boolean updatePartssmingxi(List<Partssmingxi> partssmingxis)
			throws Exception {
		PreparedStatement stmt = null;
		boolean updateFlag = false;
		String Str = "delete from partssmingxi where partssID=?";

		stmt = conn.prepareStatement(Str);
		try {
			stmt.setString(1, partssmingxis.get(1).getPartssID());
			stmt.executeUpdate();
			Str = "insert into partssmingxi values(?,?,?,?)";
			stmt = conn.prepareStatement(Str);
			for (int i = 0; i < partssmingxis.size(); i++) {

				stmt.setString(1, partssmingxis.get(i).getPartssID());// 第一条商品肯定有订单号.
				stmt.setString(2, partssmingxis.get(i).getPartID());
				stmt.setDouble(3, partssmingxis.get(i).getPartssnum());
				stmt.setDouble(4, partssmingxis.get(i).getPartssprice());
				stmt.executeUpdate();
			}
			updateFlag = true;
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

		return updateFlag;
	}

	public boolean deletePartssmingxi(Partssmingxi partssmingxi)
			throws Exception {
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String deleteStr = "delete from partssmingxi where partssID=? and partID=?";
			stmt = conn.prepareStatement(deleteStr);
			String partssID = partssmingxi.getPartssID();
			String partID = partssmingxi.getPartID();
			stmt.setString(1, partssID);
			stmt.setString(2, partID);
			stmt.executeUpdate();
			deleteFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}

		return deleteFlag;
	}

	public List<Partssmingxi> findByPartssAndMonthRange(String beginMonth,
			String endMonth) throws Exception {
		// TODO Auto-generated method stub
		Statement stmt;
		String queryStr = "select  t.kindID,mx.partssID,mx.partID,p.ssdate,partname,partssnum from partss p left join partssmingxi mx on p.partssID=mx.partssID left join part t  on mx.partID=t.partID left join category c on t.kindID = c.kindID where  p.ssdate>='"
				+ beginMonth + "' and p.ssdate<='" + endMonth + "'";
		ResultSet rs;
		List<Partssmingxi> partssmingxis = new ArrayList<Partssmingxi>();
		Partssmingxi partssmingxi;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {

				partssmingxi = new Partssmingxi();
				partssmingxi.setPartssID(rs.getString("mx.partssID"));
				partssmingxi.setPartID(rs.getString("mx.partID"));
				partssmingxi.setPartname(rs.getString("partname"));
				partssmingxi.setSsdate(rs.getString("Ssdate"));
				partssmingxi.setPartssnum(rs.getDouble("partssnum"));
				partssmingxis.add(partssmingxi);
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
		return partssmingxis;
	}


}