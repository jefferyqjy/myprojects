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

public class PartrkDAO {
	private Connection conn;

	public PartrkDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	public List<Partrk> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select partrkID,partrktotal,partrk.supID,supplier.supName,rkdate,partrk.empID,employee.empName from partrk,employee,supplier where partrk.empID=employee.empID and supplier.supID=partrk.supID order by partrkID";
		ResultSet rs;
		List<Partrk> partrks = new ArrayList<Partrk>();
		Partrk partrk;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				partrk = new Partrk();
				partrk.setPartrkID(rs.getString("partrkID"));
				partrk.setEmpID(rs.getInt("partrk.empID"));
				partrk.setEmpName(rs.getString("employee.empName"));
				partrk.setSupID(rs.getInt("partrk.supID"));
				partrk.setSupName(rs.getString("supplier.supName"));
				partrk.setRkdate(rs.getString("rkdate"));
				partrk.setPartrktotal(rs.getDouble("partrktotal"));
				partrks.add(partrk);
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
		return partrks;
	}

	public Partrk findById(String partrkID) throws Exception {
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;
		Partrk partrk = null;
		try {
			queryStr = "select partrkID,partrktotal,partrk.supID,supplier.supName,partrk.empID,employee.empName,rkdate from partrk,employee,supplier where partrk.empID=employee.empID and supplier.supID=partrk.supID and partrkID=?";
			stmt = conn.prepareStatement(queryStr);
			stmt.setString(1, partrkID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				partrk = new Partrk();
				partrk.setPartrkID(rs.getString("partrkID"));
				partrk.setEmpID(rs.getInt("partrk.empID"));
				partrk.setEmpName(rs.getString("employee.empName"));
				partrk.setSupID(rs.getInt("partrk.supID"));
				partrk.setSupName(rs.getString("supplier.supName"));
				partrk.setRkdate(rs.getString("rkdate"));
				partrk.setPartrktotal(rs.getDouble("partrktotal"));
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
		return partrk;
	}

	public List<Partrkmingxi> findPartrkmingxi(String id) throws Exception {
		Statement stmt;
		String queryStr = "select partrkmingxi.partrkID,partrkmingxi.partID,partname,partstandard,partpackaging,kindName,partrknum,partrkprice,partrknum*partrkprice partrktotal from part,partrk,partrkmingxi,category where partrkmingxi.partID=part.partID and partrkmingxi.partrkID=partrk.partrkID and category.kindID=part.kindID and partrkmingxi.partrkID='"
				+ id + "'";
		ResultSet rs;
		List<Partrkmingxi> partrkmingxis = new ArrayList<Partrkmingxi>();
		Partrkmingxi partrkmingxi;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {

				partrkmingxi = new Partrkmingxi();
				partrkmingxi.setPartrkID(rs.getString("partrkmingxi.partrkID"));
				partrkmingxi.setPartID(rs.getString("partrkmingxi.partID"));
				partrkmingxi.setPartname(rs.getString("partname"));
				partrkmingxi.setPartstandard(rs.getString("partstandard"));
				partrkmingxi.setPartpackaging(rs.getString("partpackaging"));
				partrkmingxi.setKindName(rs.getString("kindName"));
				partrkmingxi.setPartrknum(rs.getDouble("partrknum"));
				partrkmingxi.setPartrkprice(rs.getDouble("partrkprice"));
				partrkmingxi.setPartrktotalmoney(rs.getDouble("partrktotal"));
				partrkmingxis.add(partrkmingxi);
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
		return partrkmingxis;
	}

	public List<Partrk> findByPage(String queryStr, Integer currentPage,
			Integer pageCount) throws Exception {
		List<Partrk> partrks = new ArrayList<Partrk>();
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
					Partrk partrk = new Partrk();
					partrk.setPartrkID(rs.getString("partrk.partrkID"));
					partrk.setEmpID(rs.getInt("partrk.empID"));
					partrk.setEmpName(rs.getString("employee.empName"));
					partrk.setSupID(rs.getInt("partrk.supID"));
					partrk.setSupName(rs.getString("supplier.supName"));
					partrk.setRkdate(rs.getString("rkdate"));
					partrk.setPartrktotal(rs.getDouble("partrktotal"));
					partrks.add(partrk);
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
		return partrks;
	}

	// public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Partrk partrk) throws Exception {
		PreparedStatement stmt;
		boolean insFlag = false;
		String insertStr = "insert into partrk values(?,?,?,?,?)";

		stmt = conn.prepareStatement(insertStr);
		try {
			String queryStr = "select partrkID as mpartrkId from partrk order by cast(substring(partrkID,3) as signed integer) desc limit 1";
			Statement qstmt = conn.createStatement();
			ResultSet rs = qstmt.executeQuery(queryStr);
			if (rs.next()) {
				String partrkID = rs.getString("mpartrkId");// maxId="A12334"；
				if (partrkID != null && partrkID != "") {
					String idValue = partrkID.substring(2);// 得到12334 zheliwenyi
															// K10000002 多了一个K
															// 少截取了个K
					int val = Integer.parseInt(idValue) + 1;
					String id = "RK" + val;
					partrk.setPartrkID(id);
				} else {
					String id = "RK" + 1;
					partrk.setPartrkID(id);
				}

				stmt.setString(1, partrk.getPartrkID());
				stmt.setInt(2, partrk.getEmpID());
				stmt.setInt(3, partrk.getSupID());
				stmt.setDouble(4, partrk.getPartrktotal());
				stmt.setString(5, partrk.getRkdate());

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

	public boolean update(Partrk partrk) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update partrk set empID=?,supID=?,partrktotal=?,rkdate=? where partrkID=?";
		stmt = conn.prepareStatement(updateStr);
		try {

			stmt.setInt(1, partrk.getEmpID());
			stmt.setInt(2, partrk.getSupID());
			stmt.setDouble(3, partrk.getPartrktotal());
			stmt.setString(4, partrk.getRkdate());
			stmt.setString(5, partrk.getZhuangtai());
			stmt.executeUpdate();
			updateFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// finally {
		// try {
		// stmt.close();
		// } catch (SQLException e) {
		// }
		// try {
		// conn.close();
		// } catch (SQLException e) {
		// }
		// }

		return updateFlag;
	}

	public boolean deleteById(String id) throws Exception {
		String partrkID = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {

			String deleteStr = "delete from partrk where partrkID=?";
			stmt = conn.prepareStatement(deleteStr);
			stmt.setString(1, partrkID);
			stmt.executeUpdate();
			deleteStr = "delete from partrkmingxi where partrkID=?";
			stmt.setString(1, partrkID);
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

	public boolean insertPartrkmingxi(List<Partrkmingxi> partrkmingxis)
			throws Exception {
		PreparedStatement stmt = null;
		String partrkID = "RK0";
		boolean insFlag = false;
		String insertStr = "insert into partrkmingxi values(?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			String queryStr = "select partrkID as mpartrkID from partrkmingxi order by cast(substring(partrkID,3) as signed integer) desc limit 1";
			Statement qstmt = conn.createStatement();
			ResultSet rs = qstmt.executeQuery(queryStr);
			if (rs.next())
				partrkID = rs.getString("mpartrkID");
			partrkID = partrkID.substring(2, partrkID.length());
			int val = Integer.parseInt(partrkID);
			val = val + 1;// 加1后的数字
			String newpartrkID = "RK" + val;
			qstmt.close();

			for (int i = 0; i < partrkmingxis.size(); i++) {
				stmt.setString(1, newpartrkID);
				stmt.setString(2, partrkmingxis.get(i).getPartID());
				stmt.setDouble(3, partrkmingxis.get(i).getPartrknum());
				stmt.setDouble(4, partrkmingxis.get(i).getPartrkprice());
				stmt.executeUpdate();
			}
			insFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
			// }finally
			// {
			// try {
			// stmt.close();
			// } catch (SQLException e) {
			// }
			// try {
			// conn.close();
			// } catch (SQLException e) {
			// }
		}
		return insFlag;
	}

	public boolean updatePartrkmingxi(List<Partrkmingxi> partrkmingxis)
			throws Exception {
		PreparedStatement stmt = null;
		boolean updateFlag = false;
		String Str = "delete from partrkmingxi where partrkID=?";

		stmt = conn.prepareStatement(Str);
		try {
			stmt.setString(1, partrkmingxis.get(1).getPartrkID());
			stmt.executeUpdate();
			Str = "insert into partrkmingxi values(?,?,?,?)";
			stmt = conn.prepareStatement(Str);
			for (int i = 0; i < partrkmingxis.size(); i++) {

				stmt.setString(1, partrkmingxis.get(i).getPartrkID());// 第一条商品肯定有订单号.
				stmt.setString(2, partrkmingxis.get(i).getPartID());
				stmt.setDouble(3, partrkmingxis.get(i).getPartrknum());
				stmt.setDouble(4, partrkmingxis.get(i).getPartrkprice());
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

	public boolean deletePartrkmingxi(Partrkmingxi partrkmingxi)
			throws Exception {
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String deleteStr = "delete from partrkmingxi where partrkID=? and partID=?";
			stmt = conn.prepareStatement(deleteStr);
			String partrkID = partrkmingxi.getPartrkID();
			String partID = partrkmingxi.getPartID();
			stmt.setString(1, partrkID);
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

	public List<Partrkmingxi> findByPartrkAndMonthRange(String beginMonth,
			String endMonth) throws Exception {
		// TODO Auto-generated method stub
		Statement stmt;
		String queryStr = "select  t.kindID,mx.partrkID,mx.partID,p.rkdate,partname,partrknum from partrk p left join partrkmingxi mx on p.partrkID=mx.partrkID left join part t  on mx.partID=t.partID left join category c on t.kindID = c.kindID where  p.rkdate>='"
				+ beginMonth + "' and p.rkdate<='" + endMonth + "'";
		ResultSet rs;
		List<Partrkmingxi> partrkmingxis = new ArrayList<Partrkmingxi>();
		Partrkmingxi partrkmingxi;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {

				partrkmingxi = new Partrkmingxi();
				partrkmingxi.setPartrkID(rs.getString("mx.partrkID"));
				partrkmingxi.setPartID(rs.getString("mx.partID"));
				partrkmingxi.setPartname(rs.getString("partname"));
				partrkmingxi.setRkdate(rs.getString("rkdate"));
				partrkmingxi.setPartrknum(rs.getDouble("partrknum"));
				partrkmingxis.add(partrkmingxi);
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
		return partrkmingxis;
	}

}