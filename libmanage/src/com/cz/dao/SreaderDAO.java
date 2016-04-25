package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cz.entity.Sreader;
import com.cz.utils.ConnOfDatabase;

public class SreaderDAO {
	private Connection conn;

	public SreaderDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<Sreader> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select * from sreader";
		ResultSet rs;
		List<Sreader> sreaders = new ArrayList<Sreader>();
		Sreader sreader;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				sreader = new Sreader();
				sreader.setId(rs.getInt("id"));
				sreader.setUname(rs.getString("uname"));
				sreader.setUpass(rs.getString("upass"));
				sreader.setTname(rs.getString("tname"));
				sreader.setXueli(rs.getString("xueli"));
				sreader.setZiye(rs.getString("ziye"));
				sreader.setKjnum(rs.getString("kjnum"));
				sreader.setTel(rs.getString("tel"));
				sreader.setEmail(rs.getString("email"));
				sreader.setSc(rs.getString("sc"));
				sreaders.add(sreader);
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
		return sreaders;
	}

	/**
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public boolean CheckPassword(String name, String password)
			throws SQLException {
		boolean insFlag = false;
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.prepareStatement(queryStr);
		try {
			queryStr = "select * from sreader where uname = '" + name + "' and upass = '" + password + "'";
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				insFlag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insFlag;
	}
	
	/**
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public Sreader findLoginUser(String name, String password) throws SQLException {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.prepareStatement(queryStr);
		Sreader sreader = new Sreader();
		try {
			queryStr = "select * from sreader where uname = '" + name + "' and upass = '" + password + "'";
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				sreader.setId(rs.getInt("id"));
				sreader.setUname(rs.getString("uname"));
				sreader.setUpass(rs.getString("upass"));
				sreader.setTname(rs.getString("tname"));
				sreader.setXueli(rs.getString("xueli"));
				sreader.setZiye(rs.getString("ziye"));
				sreader.setKjnum(rs.getString("kjnum"));
				sreader.setTel(rs.getString("tel"));
				sreader.setEmail(rs.getString("email"));
				sreader.setSc(rs.getString("sc"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sreader;
	}

	/**
	 * @param tname
	 * @return
	 * @throws Exception
	 */
	public Sreader findByTName(String tname) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Sreader sreader = new Sreader();
		try {
			queryStr = "select * from sreader where tname = '" + tname + "'";
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				sreader.setId(rs.getInt("id"));
				sreader.setUname(rs.getString("uname"));
				sreader.setUpass(rs.getString("upass"));
				sreader.setTname(rs.getString("tname"));
				sreader.setXueli(rs.getString("xueli"));
				sreader.setZiye(rs.getString("ziye"));
				sreader.setKjnum(rs.getString("kjnum"));
				sreader.setTel(rs.getString("tel"));
				sreader.setEmail(rs.getString("email"));
				sreader.setSc(rs.getString("sc"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sreader;
	}
	
	/**
	 * @param uname
	 * @return
	 * @throws Exception
	 */
	public Sreader findByUName(String uname) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Sreader sreader = new Sreader();
		try {
			queryStr = "select * from sreader where uname = '" + uname + "'";
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				sreader.setId(rs.getInt("id"));
				sreader.setUname(rs.getString("uname"));
				sreader.setUpass(rs.getString("upass"));
				sreader.setTname(rs.getString("tname"));
				sreader.setXueli(rs.getString("xueli"));
				sreader.setZiye(rs.getString("ziye"));
				sreader.setKjnum(rs.getString("kjnum"));
				sreader.setTel(rs.getString("tel"));
				sreader.setEmail(rs.getString("email"));
				sreader.setSc(rs.getString("sc"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sreader;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Sreader findById(int id) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Sreader sreader = new Sreader();
		try {
			queryStr = "select * from sreader where id = " + id;
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				sreader.setId(rs.getInt("id"));
				sreader.setUname(rs.getString("uname"));
				sreader.setUpass(rs.getString("upass"));
				sreader.setTname(rs.getString("tname"));
				sreader.setXueli(rs.getString("xueli"));
				sreader.setZiye(rs.getString("ziye"));
				sreader.setKjnum(rs.getString("kjnum"));
				sreader.setTel(rs.getString("tel"));
				sreader.setEmail(rs.getString("email"));
				sreader.setSc(rs.getString("sc"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sreader;
	}

	/**
	 * @param queryStr
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public List<Sreader> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception {
		List<Sreader> sreaders = new ArrayList<Sreader>();
		Statement stmt;
		ResultSet rs;
		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
					Sreader sreader = new Sreader();
					sreader.setId(rs.getInt("id"));
					sreader.setUname(rs.getString("uname"));
					sreader.setUpass(rs.getString("upass"));
					sreader.setTname(rs.getString("tname"));
					sreader.setXueli(rs.getString("xueli"));
					sreader.setZiye(rs.getString("ziye"));
					sreader.setKjnum(rs.getString("kjnum"));
					sreader.setTel(rs.getString("tel"));
					sreader.setEmail(rs.getString("email"));
					sreader.setSc(rs.getString("sc"));
					sreaders.add(sreader);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sreaders;
	}

	/**
	 * add new sysuser
	 * @param sysuser
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Sreader sreader) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into sreader (uname, upass, tname, xueli, ziye, kjnum, tel, email, sc) values(?,?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			stmt.setString(1, sreader.getUname());
			stmt.setString(2, sreader.getUpass());
			stmt.setString(3, sreader.getTname());
			stmt.setString(4, sreader.getXueli());
			stmt.setString(5, sreader.getZiye());
			stmt.setString(6, sreader.getKjnum());
			stmt.setString(7, sreader.getTel());
			stmt.setString(8, sreader.getEmail());
			stmt.setString(9, sreader.getSc());
			stmt.executeUpdate();
			insFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insFlag;
	}

	/**
	 * update sysuser
	 * @param sysuser
	 * @return
	 * @throws Exception
	 */
	public boolean update(Sreader sreader) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update sreader set tname = ?, xueli = ?, ziye = ?, kjnum = ?, tel = ?, email = ?, sc = ? where id = ?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, sreader.getTname());
			stmt.setString(2, sreader.getXueli());
			stmt.setString(3, sreader.getZiye());
			stmt.setString(4, sreader.getKjnum());
			stmt.setString(5, sreader.getTel());
			stmt.setString(6, sreader.getEmail());
			stmt.setString(7, sreader.getSc());
			stmt.setInt(8, sreader.getId());
			stmt.executeUpdate();
			updateFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateFlag;
	}

	/**
	 * reset password
	 * @param id
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public boolean resetPassword(int id, String password) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update sreader set upass = ? where id = ?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, password);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			updateFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateFlag;
	}

	/**
	 * delete by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteById(int id) throws Exception {
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String deleteStr = "delete from sreader where id = ?";
			stmt = conn.prepareStatement(deleteStr);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			deleteFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deleteFlag;
	}

}
