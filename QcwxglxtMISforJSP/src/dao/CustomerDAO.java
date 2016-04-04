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

public class CustomerDAO {
	private Connection conn;

	public CustomerDAO() {
		ConnOfDatabase sqlconn;
		sqlconn = new ConnOfDatabase();
		conn = sqlconn.getConn();
	}

	public List<Customer> findAll() throws Exception {
		Statement stmt;
		String queryStr = "select cusID,cusName,cuspwd,cusphone,question,answer,cusadd,vip,zongxiaofei from customer";
		// String queryStr="select * from product";
		ResultSet rs;
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer;
		stmt = conn.createStatement();
		try {
			rs = stmt.executeQuery(queryStr);
			// int len=rs.getRow();
			while (rs.next()) {
				customer = new Customer();
				customer.setCusID(rs.getInt("cusID"));
				customer.setCusName(rs.getString("cusName"));
				customer.setCuspwd(rs.getString("cuspwd"));
				customer.setCusphone(rs.getString("cusphone"));
				customer.setQuestion(rs.getString("question"));
				customer.setAnswer(rs.getString("answer"));
				customer.setCusadd(rs.getString("cusadd"));
				customer.setVip(rs.getInt("vip"));
				customer.setZongxiaofei(rs.getDouble(("zongxiaofei")));
				customers.add(customer);
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
		return customers;
	}

	public Customer findById(int id) throws Exception {
		PreparedStatement stmt = null;
		String queryStr = "";
		ResultSet rs;
		Customer customer = null;

		try {
			queryStr = "select cusID,cusName,cuspwd,cusphone,question,answer,cusadd,vip,zongxiaofei from customer where cusID=?";
					stmt = conn.prepareStatement(queryStr);
					stmt.setInt(1, id);
					rs = stmt.executeQuery();
			if (rs.next()) {
				customer =new Customer();
				customer.setCusID(rs.getInt("cusID"));
				customer.setCusName(rs.getString("cusName"));
				customer.setCuspwd(rs.getString("cuspwd"));
				customer.setCusphone(rs.getString("cusphone"));
				customer.setQuestion(rs.getString("question"));
				customer.setAnswer(rs.getString("answer"));
				customer.setCusadd(rs.getString("cusadd"));
				customer.setVip(rs.getInt("vip"));
				customer.setZongxiaofei(rs.getDouble(("zongxiaofei")));

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
		return customer;
	}

	public List<Customer> findByPage(String queryStr, Integer currentPage,
			Integer pageCount) throws Exception {
		List<Customer> customers = new ArrayList<Customer>();
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
					Customer customer = new Customer();
					customer.setCusID(rs.getInt("cusID"));
					customer.setCusName(rs.getString("cusName"));
					customer.setCuspwd(rs.getString("cuspwd"));
					customer.setCusphone(rs.getString("cusphone"));
					customer.setQuestion(rs.getString("question"));
					customer.setAnswer(rs.getString("answer"));
					customer.setCusadd(rs.getString("cusadd"));
					customer.setVip(rs.getInt("vip"));
					customer.setZongxiaofei(rs.getDouble(("zongxiaofei")));
					customers.add(customer);
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
		return customers;
	}

	// public abstract <T> boolean insert(T t) throws Exception;
	public boolean insert(Customer customer) throws Exception {
		PreparedStatement stmt;
		int cusID = 0;
		boolean insFlag = false;
		String insertStr = "insert into customer values(?,?,?,?,?,?,?,?,?)";
		stmt = conn.prepareStatement(insertStr);
		try {
			String queryStr = "select max(cusID) as mcusId from customer";
			Statement qstmt = conn.createStatement();
			ResultSet rs = qstmt.executeQuery(queryStr);
			if (rs.next()) {
				cusID = 1 + rs.getInt("mcusId");
				customer.setCusID(cusID);
			}
			stmt.setInt(1, customer.getCusID());
			stmt.setString(2, customer.getCusName());
			stmt.setString(3, customer.getCuspwd());
			stmt.setString(4, customer.getCusphone());
			stmt.setString(5, customer.getQuestion());
			stmt.setString(6, customer.getAnswer());
			stmt.setString(7, customer.getCusadd());
			stmt.setInt(8, customer.getVip());
			stmt.setDouble(9, customer.getZongxiaofei());
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

	public boolean update(Customer cus) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update customer set cusName=?,cusphone=?,cusadd=? where cusID=?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, cus.getCusName());
			stmt.setString(2, cus.getCusphone());
			stmt.setString(3, cus.getCusadd());
			stmt.setInt(4, cus.getCusID());
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
	public boolean Update(Customer cus) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update customer set vip=?,zongxiaofei=? where cusID=?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setInt(1, cus.getVip());
			stmt.setDouble(2, cus.getZongxiaofei());
			stmt.setInt(3, cus.getCusID());
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
	public boolean updatecus(Customer cus) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update customer set cusName=?,cusphone=?,question=?,answer=?,cusadd=? where cusID=?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, cus.getCusName());
			stmt.setString(2, cus.getCusphone());
			stmt.setString(3, cus.getQuestion());
			stmt.setString(4, cus.getAnswer());
			stmt.setString(5, cus.getCusadd());
			stmt.setInt(6, cus.getCusID());
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
		int cusId = id;
		PreparedStatement stmt;
		boolean deleteFlag = false;
		try {
			String insertStr = "delete from customer where cusID=?";
			stmt = conn.prepareStatement(insertStr);
			stmt.setInt(1, cusId);
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

	public Customer findByaID(int aID) {
		// String sql =
		// "select c.* from db_qx.customer c, db_qx.yuyue y, db_qx.arrange a, db_qx.checkouts co where c.cusID=y.cusID and y.yID=a.yID and co.aID=a.aID  and co.cID=?";
		String sql = "select c.* from db_qx.customer c, db_qx.yuyue y, db_qx.arrange a where c.cusID=y.cusID and y.yID=a.yID  and aID=?";
		PreparedStatement stmt = null;
		ResultSet rs;
		Customer customer = null;

		try {
			customer = new Customer();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, aID);
			rs = stmt.executeQuery();
			if (rs.next()) {

				customer.setCusID(rs.getInt("cusID"));
				customer.setCusName(rs.getString("cusName"));
				customer.setCuspwd(rs.getString("cuspwd"));
				customer.setCusphone(rs.getString("cusphone"));
				customer.setCusadd(rs.getString("cusadd"));
				customer.setVip(rs.getInt("vip"));
				customer.setZongxiaofei(rs.getDouble("zongxiaofei"));
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
		return customer;
	}

	public Customer findByCusName(String cusName) throws Exception {
		Statement stmt;
		String queryStr = "";
		ResultSet rs;
		stmt = conn.createStatement();
		Customer customer = new Customer();

		try {
			queryStr = "select cusID,cusName,cuspwd,cusphone,question,answer,cusadd,vip,zongxiaofei from customer where cusName='"
					+ cusName + "'";
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {

				customer.setCusID(rs.getInt("cusID"));
				customer.setCusName(rs.getString("cusName"));
				customer.setCuspwd(rs.getString("cuspwd"));
				customer.setCusphone(rs.getString("cusphone"));
				customer.setQuestion(rs.getString("question"));
				customer.setAnswer(rs.getString("answer"));
				customer.setCusadd(rs.getString("cusadd"));
				customer.setVip(rs.getInt("vip"));
				customer.setZongxiaofei(rs.getDouble(("zongxiaofei")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return customer;
	}

	public boolean Update(String cusName,String cuspwd) throws Exception {
		PreparedStatement stmt;
		boolean updateFlag = false;
		String updateStr = "update customer set cuspwd=? where cusName=?";
		stmt = conn.prepareStatement(updateStr);
		try {
			stmt.setString(1, cuspwd);
			stmt.setString(2, cusName);
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

	public boolean CheckPassword(int cusID, String password) throws SQLException{
		boolean insFlag = false;
		Statement stmt;
		String queryStr="";
		ResultSet rs;
		stmt=conn.prepareStatement(queryStr);
		try
		{
			queryStr="select * from customer where cusID='"+cusID+"' and cuspwd='"+password+"'";
			rs=stmt.executeQuery(queryStr);

			while (rs.next()) {
				insFlag=true;
			}
			
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		return insFlag;
	}

	public  boolean UpdatePassword(int cusID,String cuspwd ) throws Exception
	{
		PreparedStatement stmt;
		boolean updateFlag=false;
		String updateStr="update customer set cuspwd=? where cusID=?";
		stmt =conn.prepareStatement(updateStr);	
		try
		{
			stmt.setString(1,cuspwd);		
			stmt.setInt(2,cusID);
			stmt.executeUpdate();
			updateFlag=true;
		}catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
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
}
