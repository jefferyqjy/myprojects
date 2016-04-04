package util;

import java.sql.*;

public class ConnOfDatabase {
	protected Connection conn = null;//数据库连接
	public Connection getConn() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}		
			
		try {
			// 2. 获取数据库的连接
			conn = java.sql.DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db_qx?useUnicode=true&characterEncoding=utf-8", "root","root"); // root是用户名，密码为空
			// 3. 获取表达式
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return conn;
	}
}
