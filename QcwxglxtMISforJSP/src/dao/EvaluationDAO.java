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


	public class EvaluationDAO {
		private Connection conn;	
		public EvaluationDAO()
		{
			ConnOfDatabase sqlconn;
			sqlconn=new ConnOfDatabase();
			conn=sqlconn.getConn();	
		}	
		public List<Evaluation> findAll() throws Exception
		{
			Statement stmt;
			String queryStr="select eID,evaluation.cID,evaluatime,evaluation from evaluation,checkouts where evaluation.cID=checkouts.cID";
			ResultSet rs;
			List<Evaluation> evaluations=new ArrayList<Evaluation>();
			Evaluation evaluation;
			stmt =conn.createStatement();	
			try
			{
				rs=stmt.executeQuery(queryStr);
				while (rs.next())
				{
					evaluation=new Evaluation();
					evaluation.seteID(rs.getInt("eID"));
					evaluation.setcID(rs.getInt("cID"));
					evaluation.setEvaluatime(rs.getString("evaluatime"));
					evaluation.setEvaluation(rs.getString("evaluation"));
					evaluations.add(evaluation);
				}		
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
			return evaluations;
		}
		
		public  Evaluation findById(int eID) throws Exception
		{
			PreparedStatement stmt = null;
			String queryStr = "";
			ResultSet rs;	
			Evaluation evaluation=new Evaluation();
			try
			{
				queryStr="select eID,evaluation.cID,evaluatime,evaluation from evaluation,checkouts where evaluation.cID=checkouts.cID and eID=?";
				stmt = conn.prepareStatement(queryStr);
				stmt.setInt(1, eID);
				rs = stmt.executeQuery();
		        if (rs.next())
		        {
		        	evaluation=new Evaluation();
					evaluation.seteID(rs.getInt("eID"));
					evaluation.setcID(rs.getInt("cID"));
					evaluation.setEvaluatime(rs.getString("evaluatime"));
					evaluation.setEvaluation(rs.getString("evaluation"));

		    	}

			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return evaluation;
		}
		
		public  Evaluation findBycusId(int id) throws Exception{
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt =conn.createStatement();
			Evaluation evaluation = new Evaluation();

		try {
			queryStr="select cusID from checkouts,arrange,yuyue where checkouts.aID=arrange.aID and arrange.yID=yuyue.yID and cID="+id;
			rs=stmt.executeQuery(queryStr);
			if (rs.next()) {
				evaluation.setCusID(rs.getInt("cusID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return evaluation;
	}
		
		public  Evaluation findBycId(int id) throws Exception{
			Statement stmt;
			String queryStr="";
			ResultSet rs;
			stmt =conn.createStatement();
			Evaluation evaluation = new Evaluation();

		try {
			queryStr = "select * from evaluation where cID=" +id;
			rs=stmt.executeQuery(queryStr);
			if (rs.next()) {
				evaluation.setcID(rs.getInt("cID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return evaluation;
	}
		
		public  List<Evaluation> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception
		{
			List<Evaluation> evaluations= new ArrayList<Evaluation>();
			Statement stmt;
			ResultSet rs;
			stmt =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);

			try
			{
			rs=stmt.executeQuery(queryStr);
			if (rs.next())
			{
				rs.beforeFirst();
				if ((currentPage - 1) * pageCount > 0) {
				// 移动结果集数据到当前页
				rs.absolute((currentPage - 1) * pageCount);
				}   
				if (currentPage==1)
				{
					rs.beforeFirst();				
				}
					int i = 0; // Readed pages
					while (rs.next() && i < pageCount) 
					{
					i++;
					Evaluation evaluation=new Evaluation();
					evaluation.seteID(rs.getInt("eID"));
					evaluation.setcID(rs.getInt("evaluation.cID"));
					evaluation.setEvaluatime(rs.getString("evaluatime"));
					evaluation.setEvaluation(rs.getString("evaluation"));
					evaluations.add(evaluation);
					}  
			}	
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally
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
			return evaluations;
		}
		//public abstract <T> boolean insert(T t) throws Exception;
		public boolean insert(Evaluation evaluation) throws Exception
		{
			PreparedStatement stmt;
			boolean insFlag = false;
			String insertStr = "insert into evaluation values(?,?,?,?)";
			int eID=0;
			stmt = conn.prepareStatement(insertStr);
			try {

				String queryStr="select max(eID) as meId from evaluation";
				Statement qstmt=conn.createStatement();		
				ResultSet rs=qstmt.executeQuery(queryStr);
				if (rs.next())
				{	
					eID=1+rs.getInt("meId");			
					evaluation.seteID(eID);
				}	
				stmt.setInt(1,evaluation.geteID());
				stmt.setInt(2,evaluation.getcID());
				stmt.setString(3,evaluation.getEvaluatime());
				stmt.setString(4,evaluation.getEvaluation());
				stmt.executeUpdate();
				insFlag=true;
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
			return insFlag;
		}
		
		public  boolean update(Evaluation evaluation) throws Exception
		{
			PreparedStatement stmt;
			boolean updateFlag=false;
			String updateStr="update evaluation set cID=?,evaluatime=?,evaluation=? where eID=?";
			stmt =conn.prepareStatement(updateStr);	
			try
			{
				stmt.setInt(1,evaluation.getcID());
				stmt.setString(2,evaluation.getEvaluatime());
				stmt.setString(3,evaluation.getEvaluation());
				stmt.setInt(4,evaluation.geteID());
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
		public boolean deleteById(int id) throws Exception
		{
			int eId=id;
			PreparedStatement stmt;
			boolean deleteFlag=false;
			try
			{
		    	String deleteStr="delete from evaluation where eID=?";
				stmt =conn.prepareStatement(deleteStr);
				stmt.setInt(1,eId);
				stmt.executeUpdate();
				deleteFlag=true;
			}catch(SQLException e)
			{
				e.printStackTrace();
			}finally
			{
				try {
					conn.close();
				} catch (SQLException e) {
				}    					
			}
			
			return deleteFlag;	
		}

		
	}

