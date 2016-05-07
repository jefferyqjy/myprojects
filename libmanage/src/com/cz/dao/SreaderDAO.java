package com.cz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cz.entity.Sreader;
import com.cz.entity.Syspros;
import com.cz.utils.ConnOfDatabase;

public class SreaderDAO {
	private Connection conn;

	public SreaderDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}
	
	public static void main(String[] args) throws ParseException {
		String day = "2012-05-01";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date start = df.parse(day);
		Date end = new Date();
		System.out.println(dateDiff(start, end));
	}
	
	/**
	 * get days between dates.
	 * @param start
	 * @param end
	 * @return
	 */
	private static Integer dateDiff(Date start, Date end) {
		long nd = 1000*24*60*60;//一天的毫秒数
		//long nh = 1000*60*60;//一小时的毫秒数
		//long nm = 1000*60;//一分钟的毫秒数
		//long ns = 1000;//一秒钟的毫秒数
		//获得两个时间的毫秒时间差异
		Long diff = end.getTime() - start.getTime();
		Long day = diff/nd;//计算差多少天
		//Long hour = diff%nd/nh;//计算差多少小时
		//Long min = diff%nd%nh/nm;//计算差多少分钟
		//Long sec = diff%nd%nh%nm/ns;//计算差多少秒//输出结果
		//System.out.println("时间相差："+day+"天"+hour+"小时"+min+"分钟"+sec+"秒。");
		return day.intValue();
	}
	
	public List<Map<String,Object>> statBlacklist() throws Exception {
		SysprosDAO sysprosdao = new SysprosDAO();
		String sql = "select * from syspros where infoa = '超期金额'";
		List<Syspros> list = sysprosdao.findBySql(sql);
		Integer fineMoney = 0;
		if(list != null && list.size() > 0) {
			Syspros syspros = list.get(0);
			if(syspros != null) {
				String proname = syspros.getProname();
				fineMoney = Integer.valueOf(proname.trim());
			}
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		sql = "select hj.readername as readername, s.tel as tel, hj.bookname as bookname, hj.htime as htime from bookhj hj, sreader s where 1=1 and s.uname = hj.readername";
		sql += " and (hj.hbtime is null or hj.hbtime = '')";
		sql += " and hj.htime < now()";
		sql += " and (hj.sjstatus <> '已通过' or hj.sjstatus is null";
		sql += " or (hj.sjstatus = '已通过' and hj.sjstatus < now())";
		sql += " )";
		
		List<Map<String, Object>> nlist = new ArrayList<Map<String, Object>>();
		Statement stmt;
		ResultSet rs;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("readername", rs.getString("readername"));
				map.put("tel", rs.getString("tel"));
				map.put("bookname", rs.getString("bookname"));
				map.put("htime", rs.getString("htime"));
				String htime = rs.getString("htime");
				Date start = df.parse(htime);
				Date end = new Date();
				int diff = dateDiff(start, end);
				map.put("fine", fineMoney*diff);
				nlist.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nlist;
	}
	
	/**
	 * find list via sql
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Sreader> findBySql(String sql) throws Exception {
		Statement stmt;
		ResultSet rs;
		List<Sreader> sreaders = new ArrayList<Sreader>();
		Sreader sreader;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(sql);
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
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sreaders;
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
		String updateStr = "update sreader set tname = ?, xueli = ?, ziye = ?, kjnum = ?, tel = ?, email = ?, sc = ?, upass = ? where id = ?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, sreader.getTname());
			stmt.setString(2, sreader.getXueli());
			stmt.setString(3, sreader.getZiye());
			stmt.setString(4, sreader.getKjnum());
			stmt.setString(5, sreader.getTel());
			stmt.setString(6, sreader.getEmail());
			stmt.setString(7, sreader.getSc());
			stmt.setString(8, sreader.getUpass());
			stmt.setInt(9, sreader.getId());
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
