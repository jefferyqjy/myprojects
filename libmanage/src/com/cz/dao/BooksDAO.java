package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cz.entity.Books;
import com.cz.utils.ConnOfDatabase;

public class BooksDAO {
	private Connection conn;

	public BooksDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	/**
	 * @return
	 * @throws Exception
	 */
	public List<Books> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select * from books";
		ResultSet rs;
		List<Books> bookses = new ArrayList<Books>();
		Books books;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				books = new Books();
				books.setId(rs.getInt("id"));
				books.setIsbn(rs.getString("isbn"));
				books.setBookname(rs.getString("bookname"));
				books.setPrice(rs.getString("price"));
				books.setTslb(rs.getString("tslb"));
				books.setCbs(rs.getString("cbs"));
				books.setJianj(rs.getString("jianj"));
				books.setAuthor(rs.getString("author"));
				books.setCbrq(rs.getString("cbrq"));
				books.setKucun(rs.getString("kucun"));
				books.setFilename(rs.getString("filename"));
				bookses.add(books);
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
		return bookses;
	}

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Books findById(int id) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Books books = new Books();
		try {
			queryStr = "select * from books where id = " + id;
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				books.setId(rs.getInt("id"));
				books.setIsbn(rs.getString("isbn"));
				books.setBookname(rs.getString("bookname"));
				books.setPrice(rs.getString("price"));
				books.setTslb(rs.getString("tslb"));
				books.setCbs(rs.getString("cbs"));
				books.setJianj(rs.getString("jianj"));
				books.setAuthor(rs.getString("author"));
				books.setCbrq(rs.getString("cbrq"));
				books.setKucun(rs.getString("kucun"));
				books.setFilename(rs.getString("filename"));
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
		return books;
	}

	/**
	 * 
	 * @param queryStr
	 * @param currentPage
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public List<Books> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception {
		List<Books> bookses = new ArrayList<Books>();
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
					Books books = new Books();
					books.setId(rs.getInt("id"));
					books.setIsbn(rs.getString("isbn"));
					books.setBookname(rs.getString("bookname"));
					books.setPrice(rs.getString("price"));
					books.setTslb(rs.getString("tslb"));
					books.setCbs(rs.getString("cbs"));
					books.setJianj(rs.getString("jianj"));
					books.setAuthor(rs.getString("author"));
					books.setCbrq(rs.getString("cbrq"));
					books.setKucun(rs.getString("kucun"));
					books.setFilename(rs.getString("filename"));
					bookses.add(books);
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
		return bookses;
	}

	/**
	 * @param books
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Books books) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into books (isbn, bookname, price, tslb, cbs, jianj, author, cbrq, kucun, filename) values(?,?,?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			stmt.setString(1, books.getIsbn());
			stmt.setString(2, books.getBookname());
			stmt.setString(3, books.getPrice());
			stmt.setString(4, books.getTslb());
			stmt.setString(5, books.getCbs());
			stmt.setString(6, books.getJianj());
			stmt.setString(7, books.getAuthor());
			stmt.setString(8, books.getCbrq());
			stmt.setString(9, books.getKucun());
			stmt.setString(10, books.getFilename());
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
			String insertStr = "delete from books where id = ?";
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
