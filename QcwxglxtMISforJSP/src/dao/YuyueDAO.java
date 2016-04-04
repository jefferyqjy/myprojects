package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.*;
import abstractClasses.DAO;

public class YuyueDAO extends DAO<Yuyue> {
	public List<Yuyue> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,yuyue.cusphone,repairitem,yuyuetime " +
				"from yuyue,customer " +
				"where yuyue.cusID=customer.cusID";
		// String queryStr="select * from product";
		ResultSet rs;
		List<Yuyue> yuyues = new ArrayList<Yuyue>();
		Yuyue yuyue;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			// int len=rs.getRow();
			while (rs.next()) {
				yuyue = new Yuyue();
				yuyue.setyID(rs.getInt("yID"));
				yuyue.setCusID(rs.getInt("yuyue.cusID"));
				yuyue.setCusName(rs.getString("yuyue.cusName"));
				yuyue.setCusphone(rs.getString("yuyue.cusphone"));
				yuyue.setCarnum(rs.getString("carnum"));
				yuyue.setCarmoder(rs.getString("carmoder"));
				yuyue.setRepairitem(rs.getString("repairitem"));
				yuyue.setYuyuetime(rs.getString("yuyuetime"));
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
		return yuyues;
	}

	public Yuyue findById(int id) throws Exception {
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;
		Yuyue yuyue = null;

		try {
			queryStr = "select yID,yuyue.cusID,yuyue.cusName,carnum,carmoder,"
					+ "yuyue.cusphone,repairitem,yuyuetime from yuyue,customer "
					+ "where yuyue.cusID=customer.cusID and yID=?";

			stmt = conn.prepareStatement(queryStr);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				yuyue = new Yuyue();
				yuyue.setyID(rs.getInt("yID"));
				yuyue.setCusID(rs.getInt("yuyue.cusID"));
				yuyue.setCusName(rs.getString("yuyue.cusName"));
				yuyue.setCusphone(rs.getString("yuyue.cusphone"));
				yuyue.setCarnum(rs.getString("carnum"));
				yuyue.setCarmoder(rs.getString("carmoder"));
				yuyue.setRepairitem(rs.getString("repairitem"));
				yuyue.setYuyuetime(rs.getString("yuyuetime"));

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
		return yuyue;
	}
	public List<Yuyue> findByPage(String queryStr, Integer currentPage,
			Integer pageCount) throws Exception {
		List<Yuyue> yuyues = new ArrayList<Yuyue>();
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
					Yuyue yuyue = new Yuyue();
					yuyue.setyID(rs.getInt("yID"));
					yuyue.setCusID(rs.getInt("yuyue.cusID"));
					yuyue.setCusName(rs.getString("yuyue.cusName"));
					yuyue.setCusphone(rs.getString("yuyue.cusphone"));
					yuyue.setCarnum(rs.getString("carnum"));
					yuyue.setCarmoder(rs.getString("carmoder"));
					yuyue.setRepairitem(rs.getString("repairitem"));
					yuyue.setYuyuetime(rs.getString("yuyuetime"));
					yuyue.setModifyflag(rs.getInt("modifyflag"));
					yuyues.add(yuyue);
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
		return yuyues;
	}

	// public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Yuyue yuyue) throws Exception {
		PreparedStatement stmt;
		int yID = 0;
		boolean insFlag = false;
		String insertStr = "insert into yuyue values(?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			String queryStr = "select max(yID) as myId from yuyue";
			Statement qstmt = conn.createStatement();
			ResultSet rs = qstmt.executeQuery(queryStr);
			if (rs.next()) {
				yID = 1 + rs.getInt("myId");
				yuyue.setyID(yID);
			}
			stmt.setInt(1, yuyue.getyID());
			stmt.setInt(2, yuyue.getCusID());
			stmt.setString(3, yuyue.getCusName());
			stmt.setString(4, yuyue.getCusphone());
			stmt.setString(5, yuyue.getCarnum());
			stmt.setString(6, yuyue.getCarmoder());
			stmt.setString(7, yuyue.getRepairitem());
			stmt.setString(8, yuyue.getYuyuetime());
			stmt.executeUpdate();
			insFlag = true;
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

	public boolean update(Yuyue yuyue) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update yuyue set cusID=?,cusname=?,cusphone=?,carnum=?,carmoder=?,repairitem=?,yuyuetime=? where yID=?";
		stmt = conn.prepareStatement(updateStr);
		try {

			stmt.setInt(1, yuyue.getCusID());
			stmt.setString(2, yuyue.getCusName());
			stmt.setString(3, yuyue.getCusphone());
			stmt.setString(4, yuyue.getCarnum());
			stmt.setString(5, yuyue.getCarmoder());
			stmt.setString(6, yuyue.getRepairitem());
			stmt.setString(7, yuyue.getYuyuetime());
			stmt.setInt(8, yuyue.getyID());
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
		int yId = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String insertStr = "delete from yuyue where yID=?";
			stmt = conn.prepareStatement(insertStr);
			stmt.setInt(1, yId);
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

}
