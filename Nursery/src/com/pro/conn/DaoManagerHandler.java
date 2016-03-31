package com.pro.conn;

import java.util.List;

import com.pro.db.Field;
import com.pro.db.InquireConvert;

public class DaoManagerHandler {

	public static IDaoHelperOutput executeQuery(String sql) throws Exception {
		return DaoManager.executeQuery(sql);
	}

	public static IDaoHelperOutput executeQuery(String sql, String convertClass) throws Exception {
		return DaoManager.executeQuery(sql, convertClass);
	}

	public static IDaoHelperOutput executeQuery(String sql, InquireConvert convertClass) throws Exception {
		return DaoManager.executeQuery(sql, convertClass);
	}

	public static IDaoHelperOutput executeQuery(String sql, InquireConvert convertClass, int curPage, int perPage) throws Exception {
		return DaoManager.executeQuery(sql, convertClass, curPage, perPage);
	}

	public static IDaoHelperOutput executeQuery(String sql, List<Field> conditions) throws Exception {
		return DaoManager.executeQuery(sql, conditions);
	}

	public static IDaoHelperOutput executeQuery(String sql, List<Field> conditions, String convertClass) throws Exception {
		return DaoManager.executeQuery(sql, conditions, convertClass);
	}

	public static IDaoHelperOutput executeUpdate(String sql) throws Exception {
		return DaoManager.executeUpdate(sql);
	}

	public static IDaoHelperOutput executeUpdate(List<String> sql) throws Exception {
		return DaoManager.executeUpdate(sql);
	}

	public static IDaoHelperOutput executeUpdate(String sql, List<Field> conditions) throws Exception {
		return DaoManager.executeUpdate(sql, conditions);
	}

	public static IDaoHelperOutput executeOfflineUpdate(String sql) throws Exception {
		return DaoManager.executeOfflineUpdate(sql);
	}

	public static IDaoHelperOutput executeOfflineUpdate(String sql, List<Field> conditions) throws Exception {
		return DaoManager.executeOfflineUpdate(sql, conditions);
	}

	public static IDaoHelperOutput executeQuery (IDaoHelperInput input) throws Exception {
		return DaoManager.executeQuery(input);
	}
}
