package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cz.entity.Sysuser;
import com.cz.utils.ConnOfDatabase;

public class SysuserDAO {
	private Connection conn;

	public SysuserDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	/**
	 * find list via sql
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Sysuser> findBySql(String sql) throws Exception {
		Statement stmt;
		ResultSet rs;
		List<Sysuser> sysusers = new ArrayList<Sysuser>();
		Sysuser sysuser;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				sysuser = new Sysuser();
				sysuser.setId(rs.getInt("id"));
				sysuser.setUname(rs.getString("uname"));
				sysuser.setUpass(rs.getString("upass"));
				sysuser.setTname(rs.getString("tname"));
				sysuser.setTel(rs.getString("tel"));
				sysuser.setEmail(rs.getString("email"));
				sysusers.add(sysuser);
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
		return sysusers;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<Sysuser> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select * from sysuser";
		ResultSet rs;
		List<Sysuser> sysusers = new ArrayList<Sysuser>();
		Sysuser sysuser;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				sysuser = new Sysuser();
				sysuser.setId(rs.getInt("id"));
				sysuser.setUname(rs.getString("uname"));
				sysuser.setUpass(rs.getString("upass"));
				sysuser.setTname(rs.getString("tname"));
				sysuser.setTel(rs.getString("tel"));
				sysuser.setEmail(rs.getString("email"));
				sysusers.add(sysuser);
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
		return sysusers;
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
			queryStr = "select * from sysuser where uname = '" + name + "' and upass = '" + password + "'";
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
	public Sysuser findLoginUser(String name, String password) throws SQLException {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.prepareStatement(queryStr);
		Sysuser sysuser = new Sysuser();
		try {
			queryStr = "select * from sysuser where uname = '" + name + "' and upass = '" + password + "'";
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				sysuser.setId(rs.getInt("id"));
				sysuser.setUname(rs.getString("uname"));
				sysuser.setUpass(rs.getString("upass"));
				sysuser.setTname(rs.getString("tname"));
				sysuser.setTel(rs.getString("tel"));
				sysuser.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sysuser;
	}

	/**
	 * @param tname
	 * @return
	 * @throws Exception
	 */
	public Sysuser findByTName(String tname) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Sysuser sysuser = new Sysuser();
		try {
			queryStr = "select * from sysuser where tname = '" + tname + "'";
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				sysuser.setId(rs.getInt("id"));
				sysuser.setUname(rs.getString("uname"));
				sysuser.setUpass(rs.getString("upass"));
				sysuser.setTname(rs.getString("tname"));
				sysuser.setTel(rs.getString("tel"));
				sysuser.setEmail(rs.getString("email"));
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
		return sysuser;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Sysuser findById(int id) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Sysuser sysuser = new Sysuser();
		try {
			queryStr = "select * from sysuser where id = " + id;
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				sysuser.setId(rs.getInt("id"));
				sysuser.setUname(rs.getString("uname"));
				sysuser.setUpass(rs.getString("upass"));
				sysuser.setTname(rs.getString("tname"));
				sysuser.setTel(rs.getString("tel"));
				sysuser.setEmail(rs.getString("email"));
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
		return sysuser;
	}

	/**
	 * @param queryStr
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public List<Sysuser> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception {
		List<Sysuser> sysusers = new ArrayList<Sysuser>();
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
					Sysuser sysuser = new Sysuser();
					sysuser.setId(rs.getInt("id"));
					sysuser.setUname(rs.getString("uname"));
					sysuser.setUpass(rs.getString("upass"));
					sysuser.setTname(rs.getString("tname"));
					sysuser.setTel(rs.getString("tel"));
					sysuser.setEmail(rs.getString("email"));
					sysusers.add(sysuser);
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
		return sysusers;
	}

	/**
	 * add new sysuser
	 * @param sysuser
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Sysuser sysuser) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into sysuser (uname, upass, tname, tel, email) values(?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			stmt.setString(1, sysuser.getUname());
			stmt.setString(2, sysuser.getUpass());
			stmt.setString(3, sysuser.getTname());
			stmt.setString(4, sysuser.getTel());
			stmt.setString(5, sysuser.getEmail());
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
	public boolean update(Sysuser sysuser) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update sysuser set tname = ?, tel = ?, email = ?, upass = ? where id = ?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, sysuser.getTname());
			stmt.setString(2, sysuser.getTel());
			stmt.setString(3, sysuser.getEmail());
			stmt.setString(4, sysuser.getUpass());
			stmt.setInt(5, sysuser.getId());
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
		String updateStr = "update sysuser set upass = ? where id = ?";
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
			String insertStr = "delete from sysuser where id = ?";
			stmt = conn.prepareStatement(insertStr);
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
