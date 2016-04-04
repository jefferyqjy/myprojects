package abstractClasses;

import java.sql.Connection;


import java.util.List;
import util.ConnOfDatabase;

public abstract class DAO <T>{
	protected Connection conn;	
	public DAO()
	{
		ConnOfDatabase sqlconn;
		sqlconn=new ConnOfDatabase();
		conn=sqlconn.getConn();	
	}
	
	public abstract  List<?> findAll() throws Exception;
	public abstract  T findById(int id) throws Exception;
	public abstract  List<?> findByPage(String queryStr, Integer currentPage, Integer pageCount) throws Exception;
	public abstract  boolean insert(T t) throws Exception;
	public abstract  boolean update(T t) throws Exception;
	public abstract boolean deleteById(int id) throws Exception;
}

