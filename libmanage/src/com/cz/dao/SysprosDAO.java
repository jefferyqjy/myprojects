package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cz.entity.Syspros;
import com.cz.utils.ConnOfDatabase;

public class SysprosDAO {
	private Connection conn;

	public SysprosDAO() {
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
	public List<Syspros> findBySql(String sql) throws Exception {
		Statement stmt;
		ResultSet rs;
		List<Syspros> sysproses = new ArrayList<Syspros>();
		Syspros syspros;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				syspros = new Syspros();
				syspros.setId(rs.getInt("id"));
				syspros.setProname(rs.getString("proname"));
				syspros.setInfoa(rs.getString("infoa"));
				syspros.setInfob(rs.getString("infob"));
				syspros.setInfoc(rs.getString("infoc"));
				syspros.setInfod(rs.getString("infod"));
				syspros.setInfoe(rs.getString("infoe"));
				syspros.setInfof(rs.getString("infof"));
				syspros.setInfog(rs.getString("infog"));
				sysproses.add(syspros);
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
		return sysproses;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<Syspros> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select * from syspros";
		ResultSet rs;
		List<Syspros> sysproses = new ArrayList<Syspros>();
		Syspros syspros;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				syspros = new Syspros();
				syspros.setId(rs.getInt("id"));
				syspros.setProname(rs.getString("proname"));
				syspros.setInfoa(rs.getString("infoa"));
				syspros.setInfob(rs.getString("infob"));
				syspros.setInfoc(rs.getString("infoc"));
				syspros.setInfod(rs.getString("infod"));
				syspros.setInfoe(rs.getString("infoe"));
				syspros.setInfof(rs.getString("infof"));
				syspros.setInfog(rs.getString("infog"));
				sysproses.add(syspros);
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
		return sysproses;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Syspros findById(int id) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Syspros syspros = new Syspros();
		try {
			queryStr = "select * from syspros where id = " + id;
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				syspros.setId(rs.getInt("id"));
				syspros.setProname(rs.getString("proname"));
				syspros.setInfoa(rs.getString("infoa"));
				syspros.setInfob(rs.getString("infob"));
				syspros.setInfoc(rs.getString("infoc"));
				syspros.setInfod(rs.getString("infod"));
				syspros.setInfoe(rs.getString("infoe"));
				syspros.setInfof(rs.getString("infof"));
				syspros.setInfog(rs.getString("infog"));
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
		return syspros;
	}

	/**
	 * @param queryStr
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public List<Syspros> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception {
		List<Syspros> sysproses = new ArrayList<Syspros>();
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
					Syspros syspros = new Syspros();
					syspros.setId(rs.getInt("id"));
					syspros.setProname(rs.getString("proname"));
					syspros.setInfoa(rs.getString("infoa"));
					syspros.setInfob(rs.getString("infob"));
					syspros.setInfoc(rs.getString("infoc"));
					syspros.setInfod(rs.getString("infod"));
					syspros.setInfoe(rs.getString("infoe"));
					syspros.setInfof(rs.getString("infof"));
					syspros.setInfog(rs.getString("infog"));
					sysproses.add(syspros);
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
		return sysproses;
	}

	/**
	 * @param syspros
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Syspros syspros) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into sysuser (proname, infoa, infob, infoc, infod, infoe, infof, infog) values(?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			stmt.setString(1, syspros.getProname());
			stmt.setString(2, syspros.getInfoa());
			stmt.setString(3, syspros.getInfob());
			stmt.setString(4, syspros.getInfoc());
			stmt.setString(5, syspros.getInfod());
			stmt.setString(6, syspros.getInfoe());
			stmt.setString(7, syspros.getInfof());
			stmt.setString(8, syspros.getInfog());
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
	/*public boolean update(Syspros syspros) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update sysuser set tname = ?, tel = ?, email = ? where id = ?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, sysuser.getTname());
			stmt.setString(2, sysuser.getTel());
			stmt.setString(3, sysuser.getEmail());
			stmt.setInt(4, sysuser.getId());
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
	}*/

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
			String insertStr = "delete from syspros where id = ?";
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
