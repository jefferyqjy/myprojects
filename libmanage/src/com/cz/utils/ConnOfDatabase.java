package com.cz.utils;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnOfDatabase {
	protected Connection conn = null;// 数据库连接

	public Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// 2. 获取数据库的连接
			conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/libmange?useUnicode=true&characterEncoding=utf-8", "root", "root"); // root是用户名，密码为空
			// 3. 获取表达式
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
