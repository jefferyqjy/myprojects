package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cz.entity.Bookhj;
import com.cz.utils.ConnOfDatabase;

public class BookhjDAO {
	private Connection conn;

	public BookhjDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<Bookhj> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select * from bookhj";
		ResultSet rs;
		List<Bookhj> bookhjes = new ArrayList<Bookhj>();
		Bookhj bookhj;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				bookhj = new Bookhj();
				bookhj.setId(rs.getInt("id"));
				bookhj.setJtime(rs.getString("jtime"));
				bookhj.setHtime(rs.getString("htime"));
				bookhj.setBookname(rs.getString("bookname"));
				bookhj.setReadername(rs.getString("readername"));
				bookhj.setYjin(rs.getString("yjin"));
				bookhj.setBei(rs.getString("bei"));
				bookhj.setHbtime(rs.getString("hbtime"));
				bookhj.setHbkou(rs.getString("hbkou"));
				bookhj.setHbbei(rs.getString("hbbei"));
				bookhj.setSjtime(rs.getString("sjtime"));
				bookhj.setSjstatus(rs.getString("sjstatus"));
				bookhjes.add(bookhj);
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
		return bookhjes;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Bookhj findById(int id) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Bookhj bookhj = new Bookhj();
		try {
			queryStr = "select * from bookhj where id = " + id;
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				bookhj.setId(rs.getInt("id"));
				bookhj.setJtime(rs.getString("jtime"));
				bookhj.setHtime(rs.getString("htime"));
				bookhj.setBookname(rs.getString("bookname"));
				bookhj.setReadername(rs.getString("readername"));
				bookhj.setYjin(rs.getString("yjin"));
				bookhj.setBei(rs.getString("bei"));
				bookhj.setHbtime(rs.getString("hbtime"));
				bookhj.setHbkou(rs.getString("hbkou"));
				bookhj.setHbbei(rs.getString("hbbei"));
				bookhj.setSjtime(rs.getString("sjtime"));
				bookhj.setSjstatus(rs.getString("sjstatus"));
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
		return bookhj;
	}

	/**
	 * 
	 * @param queryStr
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public List<Bookhj> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception {
		List<Bookhj> bookhjes = new ArrayList<Bookhj>();
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
					Bookhj bookhj = new Bookhj();
					bookhj.setId(rs.getInt("id"));
					bookhj.setJtime(rs.getString("jtime"));
					bookhj.setHtime(rs.getString("htime"));
					bookhj.setBookname(rs.getString("bookname"));
					bookhj.setReadername(rs.getString("readername"));
					bookhj.setYjin(rs.getString("yjin"));
					bookhj.setBei(rs.getString("bei"));
					bookhj.setHbtime(rs.getString("hbtime"));
					bookhj.setHbkou(rs.getString("hbkou"));
					bookhj.setHbbei(rs.getString("hbbei"));
					bookhj.setSjtime(rs.getString("sjtime"));
					bookhj.setSjstatus(rs.getString("sjstatus"));
					bookhjes.add(bookhj);
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
		return bookhjes;
	}

	/**
	 * @param bookhj
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Bookhj bookhj) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into bookhj (jtime, htime, bookname, readername, yjin, bei, hbtime, hbkou, hbbei, sjtime, sjstatus) values(?,?,?,?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			stmt.setString(1, bookhj.getJtime());
			stmt.setString(2, bookhj.getHtime());
			stmt.setString(3, bookhj.getBookname());
			stmt.setString(4, bookhj.getReadername());
			stmt.setString(5, bookhj.getYjin());
			stmt.setString(6, bookhj.getBei());
			stmt.setString(7, bookhj.getHbtime());
			stmt.setString(8, bookhj.getHbkou());
			stmt.setString(9, bookhj.getHbbei());
			stmt.setString(10, bookhj.getSjtime());
			stmt.setString(10, bookhj.getSjstatus());
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
	 * @param bookhj
	 * @return
	 * @throws Exception
	 */
	public boolean update(Bookhj bookhj) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update bookhj set jtime = ?, htime = ?, sjtime = ?, sjstatus = ? where id = ?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, bookhj.getJtime());
			stmt.setString(2, bookhj.getHtime());
			stmt.setString(3, bookhj.getSjtime());
			stmt.setString(4, bookhj.getSjstatus());
			stmt.setInt(5, bookhj.getId());
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
			String insertStr = "delete from bookhj where id = ?";
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
