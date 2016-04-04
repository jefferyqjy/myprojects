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

public class ArrangeDAO {
	private Connection conn;

	public ArrangeDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	public List<Arrange> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select aID,arrange.empID,empName,empphone,arrange.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyue.yuyuetime,anpaitime,wangongtime,arrange.partID,arrange.partname,checknum from arrange,yuyue,employee,customer,part where arrange.empID=employee.empID and arrange.yID=yuyue.yID and yuyue.cusID=customer.cusID and arrange.partID=part.partID";
		ResultSet rs;// 遍历结果集
		List<Arrange> arranges = new ArrayList<Arrange>();// 创建容器，存储对象为Category
		Arrange arrange;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);// 执行sql语句
			// int len=rs.getRow();
			while (rs.next())// 遍历结果集，当结果到达结尾，没有多余结果就退出
			{
				arrange = new Arrange();// 初始化结果对象
				arrange.setaID(rs.getInt("aID"));// 这里是将数据库查询的数据集存储到对象中，然后放入结果集
				arrange.setEmpID(rs.getInt("arrange.empID"));
				arrange.setEmpName(rs.getString("empName"));
				arrange.setEmpphone(rs.getString("empphone"));
				arrange.setYID(rs.getInt("arrange.yID"));
				arrange.setCusID(rs.getInt("yuyue.cusID"));
				arrange.setCusName(rs.getString("yuyue.cusName"));
				arrange.setCusphone(rs.getString("yuyue.cusphone"));
				arrange.setCarnum(rs.getString("carnum"));
				arrange.setCarmoder(rs.getString("carmoder"));
				arrange.setRepairitem(rs.getString("repairitem"));
				arrange.setYuyuetime(rs.getString("yuyue.yuyuetime"));
				arrange.setAnpaitime(rs.getString("anpaitime"));
				arrange.setWangongtime(rs.getString("wangongtime"));
				arrange.setPartID(rs.getString("arrange.partID"));
				arrange.setPartname(rs.getString("arrange.partname"));
				arrange.setChecknum(rs.getDouble("checknum"));
				arranges.add(arrange);
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
		return arranges;
	}

	public Arrange findById(int id) throws Exception {
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;
		Arrange arrange = null;

		try {
			queryStr = "select aID,arrange.empID,empName,empphone,arrange.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyue.yuyuetime,anpaitime,wangongtime,arrange.partID,arrange.partname,checknum from arrange,yuyue,employee,customer,part where arrange.empID=employee.empID and arrange.yID=yuyue.yID and yuyue.cusID=customer.cusID and arrange.partID=part.partID  and aID=?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				arrange=new Arrange();
				arrange.setaID(rs.getInt("aID"));// 这里是将数据库查询的数据集存储到对象中，然后放入结果集
				arrange.setEmpID(rs.getInt("arrange.empID"));
				arrange.setEmpName(rs.getString("empName"));
				arrange.setEmpphone(rs.getString("empphone"));
				arrange.setYID(rs.getInt("arrange.yID"));
				arrange.setCusID(rs.getInt("yuyue.cusID"));
				arrange.setCusName(rs.getString("yuyue.cusName"));
				arrange.setCusphone(rs.getString("yuyue.cusphone"));
				arrange.setCarnum(rs.getString("carnum"));
				arrange.setCarmoder(rs.getString("carmoder"));
				arrange.setRepairitem(rs.getString("repairitem"));
				arrange.setYuyuetime(rs.getString("yuyue.yuyuetime"));
				arrange.setAnpaitime(rs.getString("anpaitime"));
				arrange.setWangongtime(rs.getString("wangongtime"));
				arrange.setPartID(rs.getString("arrange.partID"));
				arrange.setPartname(rs.getString("arrange.partname"));
				arrange.setChecknum(rs.getDouble("checknum"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return arrange;
	}
	public Arrange findByyId(int id) throws Exception {
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt =conn.createStatement();
		Arrange arrange = new Arrange();

		try {
			queryStr = "select aID,arrange.empID,empName,empphone,arrange.yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyue.yuyuetime,anpaitime,wangongtime,arrange.partID,arrange.partname,checknum from arrange,yuyue,employee,customer,part where arrange.empID=employee.empID and arrange.yID=yuyue.yID and yuyue.cusID=customer.cusID and arrange.partID=part.partID  and arrange.yID=" +id;
			rs=stmt.executeQuery(queryStr);
			if (rs.next()) {
				arrange=new Arrange();
				arrange.setaID(rs.getInt("aID"));// 这里是将数据库查询的数据集存储到对象中，然后放入结果集
				arrange.setEmpID(rs.getInt("arrange.empID"));
				arrange.setEmpName(rs.getString("empName"));
				arrange.setEmpphone(rs.getString("empphone"));
				arrange.setYID(rs.getInt("arrange.yID"));
				arrange.setCusID(rs.getInt("yuyue.cusID"));
				arrange.setCusName(rs.getString("yuyue.cusName"));
				arrange.setCusphone(rs.getString("yuyue.cusphone"));
				arrange.setCarnum(rs.getString("carnum"));
				arrange.setCarmoder(rs.getString("carmoder"));
				arrange.setRepairitem(rs.getString("repairitem"));
				arrange.setYuyuetime(rs.getString("yuyue.yuyuetime"));
				arrange.setAnpaitime(rs.getString("anpaitime"));
				arrange.setWangongtime(rs.getString("wangongtime"));
				arrange.setPartID(rs.getString("arrange.partID"));
				arrange.setPartname(rs.getString("arrange.partname"));
				arrange.setChecknum(rs.getDouble("checknum"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return arrange;
	}
	public List<Arrange> findByPage(String queryStr, Integer currentPage,
			Integer pageCount) throws Exception {
		List<Arrange> arranges = new ArrayList<Arrange>();
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
					Arrange arrange = new Arrange();
					arrange.setaID(rs.getInt("aID"));// 这里是将数据库查询的数据集存储到对象中，然后放入结果集
					arrange.setEmpID(rs.getInt("arrange.empID"));
					arrange.setEmpName(rs.getString("empName"));
					arrange.setEmpphone(rs.getString("empphone"));
					arrange.setYID(rs.getInt("arrange.yID"));
					arrange.setCusID(rs.getInt("yuyue.cusID"));
					arrange.setCusName(rs.getString("yuyue.cusName"));
					arrange.setCusphone(rs.getString("yuyue.cusphone"));
					arrange.setCarnum(rs.getString("carnum"));
					arrange.setCarmoder(rs.getString("carmoder"));
					arrange.setRepairitem(rs.getString("repairitem"));
					arrange.setYuyuetime(rs.getString("yuyue.yuyuetime"));
					arrange.setAnpaitime(rs.getString("anpaitime"));
					arrange.setWangongtime(rs.getString("wangongtime"));
					arrange.setPartID(rs.getString("arrange.partID"));
					arrange.setPartname(rs.getString("arrange.partname"));
					arrange.setChecknum(rs.getDouble("checknum"));
					arrange.setModifyflag(rs.getInt("modifyflag"));
					arranges.add(arrange);
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
		return arranges;
	}

	public boolean insert(Arrange arrange) throws Exception {
		PreparedStatement stmt;
		int aID = 0;
		boolean insFlag = false;
		String insertStr = "insert into arrange values(?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			String queryStr = "select max(aID) as maId from arrange";
			Statement qstmt = conn.createStatement();
			ResultSet rs = qstmt.executeQuery(queryStr);
			if (rs.next()) {
				aID = 1 + rs.getInt("maId");
				arrange.setaID(aID);
			}
			stmt.setInt(1, arrange.getaID());
			stmt.setInt(2, arrange.getEmpID());
			stmt.setInt(3, arrange.getyID());
			stmt.setString(4, arrange.getAnpaitime());
			stmt.setString(5, arrange.getWangongtime());
			stmt.setString(6, arrange.getPartID());
			stmt.setString(7, arrange.getPartname());
			stmt.setDouble(8, arrange.getChecknum());
			stmt.executeUpdate();
			insFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return insFlag;
	}

	public boolean update(Arrange arrange) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update arrange set empID=?,yID=?,anpaitime=?,wangongtime=?,partID=?,partname=?,checknum=? where aID=?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setInt(1, arrange.getEmpID());
			stmt.setInt(2, arrange.getyID());
			stmt.setString(3, arrange.getAnpaitime());
			stmt.setString(4, arrange.getWangongtime());
			stmt.setString(5, arrange.getPartID());
			stmt.setString(6, arrange.getPartname());
			stmt.setDouble(7, arrange.getChecknum());
			stmt.setInt(8, arrange.getaID());
			stmt.executeUpdate();
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

	public boolean deleteById(int id) throws Exception {
		int aId = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String insertStr = "delete from arrange where aID=?";
			stmt = conn.prepareStatement(insertStr);
			stmt.setInt(1, aId);
			stmt.executeUpdate();
			deleteFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deleteFlag;
	}
}