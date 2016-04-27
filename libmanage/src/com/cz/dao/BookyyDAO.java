package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cz.entity.Bookyy;
import com.cz.utils.ConnOfDatabase;

public class BookyyDAO {
	private Connection conn;

	public BookyyDAO() {
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
	public List<Bookyy> findBySql(String sql) throws Exception {
		Statement stmt;
		ResultSet rs;
		List<Bookyy> bookyys = new ArrayList<Bookyy>();
		Bookyy bookyy;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bookyy = new Bookyy();
				bookyy.setId(rs.getInt("id"));
				bookyy.setYytime(rs.getString("yytime"));
				bookyy.setHtime(rs.getString("htime"));
				bookyy.setReadername(rs.getString("readername"));
				bookyy.setBookname(rs.getString("bookname"));
				bookyy.setBei(rs.getString("bei"));
				bookyy.setStatus(rs.getString("status"));
				bookyys.add(bookyy);
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
		return bookyys;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	public List<Bookyy> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select * from bookyy";
		ResultSet rs;
		List<Bookyy> bookyys = new ArrayList<Bookyy>();
		Bookyy bookyy;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				bookyy = new Bookyy();
				bookyy.setId(rs.getInt("id"));
				bookyy.setYytime(rs.getString("yytime"));
				bookyy.setHtime(rs.getString("htime"));
				bookyy.setReadername(rs.getString("readername"));
				bookyy.setBookname(rs.getString("bookname"));
				bookyy.setBei(rs.getString("bei"));
				bookyy.setStatus(rs.getString("status"));
				bookyys.add(bookyy);
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
		return bookyys;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bookyy findById(int id) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Bookyy bookyy = new Bookyy();
		try {
			queryStr = "select * from bookyy where id = " + id;
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				bookyy.setId(rs.getInt("id"));
				bookyy.setYytime(rs.getString("yytime"));
				bookyy.setHtime(rs.getString("htime"));
				bookyy.setReadername(rs.getString("readername"));
				bookyy.setBookname(rs.getString("bookname"));
				bookyy.setBei(rs.getString("bei"));
				bookyy.setStatus(rs.getString("status"));
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
		return bookyy;
	}

	/**
	 * 
	 * @param queryStr
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public List<Bookyy> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception {
		List<Bookyy> bookyys = new ArrayList<Bookyy>();
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
					Bookyy bookyy = new Bookyy();
					bookyy.setId(rs.getInt("id"));
					bookyy.setYytime(rs.getString("yytime"));
					bookyy.setHtime(rs.getString("htime"));
					bookyy.setReadername(rs.getString("readername"));
					bookyy.setBookname(rs.getString("bookname"));
					bookyy.setBei(rs.getString("bei"));
					bookyy.setStatus(rs.getString("status"));
					bookyys.add(bookyy);
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
		return bookyys;
	}

	/**
	 * @param bookyy
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Bookyy bookyy) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into bookyy (yytime, htime, readername, bookname, bei, status) values(?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			stmt.setString(1, bookyy.getYytime());
			stmt.setString(2, bookyy.getHtime());
			stmt.setString(3, bookyy.getReadername());
			stmt.setString(4, bookyy.getBookname());
			stmt.setString(5, bookyy.getBei());
			stmt.setString(6, bookyy.getStatus());
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
	 * delete by id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteById(int id) throws Exception {
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String insertStr = "delete from bookyy where id = ?";
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
