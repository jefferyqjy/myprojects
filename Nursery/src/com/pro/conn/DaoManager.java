package com.pro.conn;

import java.util.ArrayList;
import java.util.List;

import com.pro.db.DaoConvertManager;
import com.pro.db.DaoHelper;
import com.pro.db.DaoHelperInput;
import com.pro.db.DaoHelperOutput;
import com.pro.db.Field;
import com.pro.db.InquireConvert;
import com.pro.db.Sql;
import com.util.TotalsConvert;

public class DaoManager{

	private static DaoHelperInput prepareQuery() throws Exception{
		DaoHelperInput input = new DaoHelperInput();
		Sql _sql = new Sql();
		List<Sql> list = new ArrayList<Sql>();
	    list.add(_sql);
	    input.setSqls(list);
		return input;
	}

	private static DaoHelperInput prepareSimpleQuery(String sql) throws Exception{
		DaoHelperInput input = prepareQuery();
		try {
			Sql _sql = input.getSqls().get(0);
			_sql.setType(Sql.TYPE_INQUIRE);
			_sql.setContent(sql);
		} catch (Exception e) {
			throw e;
		}
    	return input;
	}

	protected static IDaoHelperOutput executeQuery(String sql) throws Exception {
		DaoHelperInput input = prepareSimpleQuery(sql);
        return execute(input);
	}


	private static IDaoHelperOutput execute(DaoHelperInput input) throws Exception{
		 IDaoHelper dao = new DaoHelper();
	     return dao.execute(input);
	}

	protected static IDaoHelperOutput executeQuery(String sql, String convertClass) throws Exception {
		DaoHelperInput input = prepareSimpleQuery(sql);
		input.getSqls().get(0).setConvert(DaoConvertManager.createConvert(convertClass));
        return execute(input);
	}

	protected static IDaoHelperOutput executeQuery(String sql, InquireConvert convertClass) throws Exception {
		DaoHelperInput input = prepareSimpleQuery(sql);
		input.getSqls().get(0).setConvert(convertClass);
        return execute(input);
	}

	public static IDaoHelperOutput executeQuery(String sql, InquireConvert convertClass, int curPage, int perPage) throws Exception {
		DaoHelperInput input = prepareSimpleQuery(sql);
		input.getSqls().get(0).setConvert(convertClass);
		input.getSqls().get(0).setCurPage(curPage);
		input.getSqls().get(0).setPerPage(perPage);
		input.getSqls().get(0).setPaging(true);
		return execute(input);
	}

	protected static IDaoHelperOutput executeQuery(String sql, List<Field> conditions) throws Exception {
		DaoHelperInput input = prepareComplexQuery(sql, conditions);
        return execute(input);
	}

	protected static IDaoHelperOutput executeQuery(String sql, List<Field> conditions, String convertClass) throws Exception {
		DaoHelperInput input = prepareComplexQuery(sql, conditions);
		input.getSqls().get(0).setConvert(DaoConvertManager.createConvert(convertClass));
        return execute(input);
	}

	public static IDaoHelperOutput executeUpdate(List<String> sql) throws Exception {

		DaoHelperInput input = new DaoHelperInput();
		input.setBatch(true);
		List<Sql> list = new ArrayList<Sql>();
		for (int i = 0; i < sql.size(); i++) {
			Sql _sql = new Sql();
			_sql.setContent(sql.get(i));
			_sql.setType(Sql.TYPE_UPDATE);
			 list.add(_sql);
		}

	    input.setSqls(list);

		return execute(input);
	}

	protected static IDaoHelperOutput executeUpdate(String sql) throws Exception {
		DaoHelperInput input = prepareSimpleQuery(sql);
		input.getSqls().get(0).setType(Sql.TYPE_UPDATE);
        return execute(input);
	}

	protected static IDaoHelperOutput executeUpdate(String sql, List<Field> conditions) throws Exception {
		DaoHelperInput input = prepareComplexQuery(sql, conditions);
		input.getSqls().get(0).setType(Sql.TYPE_UPDATE);
        return execute(input);
	}

	protected static IDaoHelperOutput executeOfflineUpdate(String sql) throws Exception {
		DaoHelperInput input = prepareSimpleQuery(sql);
		input.getSqls().get(0).setType(Sql.TYPE_UPDATE);
		input.setOnline(false);
        return execute(input);
	}

	protected static IDaoHelperOutput executeOfflineUpdate(String sql, List<Field> conditions) throws Exception {
		DaoHelperInput input = prepareComplexQuery(sql, conditions);
		input.getSqls().get(0).setType(Sql.TYPE_UPDATE);
		input.setOnline(false);
        return execute(input);
	}

	private static DaoHelperInput prepareComplexQuery(String sql, List<Field> conditions) throws Exception{
		DaoHelperInput input = prepareQuery();
		try {
			Sql _sql = input.getSqls().get(0);
			_sql.setType(Sql.TYPE_INQUIRE);
			_sql.setContent(sql);
	        _sql.setSimpleSql(false);
	        _sql.setConditionFields(conditions);
		} catch (Exception e) {
			throw e;
		}
    	return input;
	}

	public static IDaoHelperOutput executeQuery (IDaoHelperInput input) throws Exception {
		DaoHelperInput input2 = (DaoHelperInput)input;

		return execute(input2);
	}

	public static int executeTotalsQuery(String cause){
		try {
			String sql = "select count(*) totals from " + cause;
			DaoHelperOutput output = (DaoHelperOutput) DaoManagerHandler
			.executeQuery(sql, new TotalsConvert());
			return Integer.parseInt((String)output.getResults().get(0));
		} catch (Exception e) {
			return 0;
		}

	}
}
