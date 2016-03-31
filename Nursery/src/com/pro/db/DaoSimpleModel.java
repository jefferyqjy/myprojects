package com.pro.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pro.conn.ConnectionManager;
import com.util.CommonUtil;
import com.util.PageControl;

public class DaoSimpleModel extends DaoModel {

	protected DaoHelperOutput execute(DaoHelperInput input) throws Exception {
		List<Sql> sqls = input.getSqls();
		if (sqls != null) {
			// there is only one sql in the simple model.
			Sql sql = sqls.get(0);
			if (sql != null) {
				if (input.isOnline()) {
					return onlineExecute(sql);
				} else {
					return offlineExecute(sql);
				}
			} else {
				throw new Exception ();
			}
		} else {
			throw new Exception ();
		}
	}

	protected DaoHelperOutput onlineExecute(Sql sql) throws Exception {
		if (sql.isSimpleSql()) {
			return onlineSimpleExecute(sql);
		} else {
			return onlineComplexExecute(sql);
		}
	}

	private DaoHelperOutput offlineExecute(Sql sql) throws Exception {
		Thread t = new DaoSimpleThread(sql);
		t.start();
		DaoHelperOutput output = new DaoHelperOutput();
		output.setbSuccess(true);
		return output;
	}

	private DaoHelperOutput onlineSimpleExecute(Sql sql) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DaoHelperOutput output = new DaoHelperOutput();
		int sumNote = 0;
		PageControl control = new PageControl();
		try {
			String content = sql.getContent();
			System.out.println("sql:" + content);
			String ds = sql.getDateSource();
			if (CommonUtil.checkEmpty(ds)) {
				conn = ConnectionManager.getConnection();
			} else {
				conn = ConnectionManager.getConnection(ds.trim());
			}
			conn.setAutoCommit(false);
			if (sql.isPaging()) {
				stmt = conn.prepareStatement(content,ResultSet.TYPE_SCROLL_INSENSITIVE,
	 					ResultSet.CONCUR_READ_ONLY);
			} else {
				stmt = conn.prepareStatement(content);
			}

			int flag = 0;
			int type = sql.getType();
			switch (type) {
			case Sql.TYPE_INQUIRE:
				rs = stmt.executeQuery();
				List<Object> list = new ArrayList<Object>();
				if (sql.isPaging()) {
					rs.absolute(-1);
					sumNote = rs.getRow();
					control.init(sql.getCurPage(), sumNote, sql.getPerPage());
					if (sumNote > 0) {
						if (control.getStart() == 0) {
							rs.absolute(1);
						} else {
							rs.absolute(control.getStart());
						}
						do {
							Object result = convertRS(rs, sql);
							list.add(result);
							if (!rs.next()) {
								break;
							}
						} while (rs.getRow() < control.getEnd() + 1);
					}
				} else {
					while (rs.next()) {
						Object result = convertRS(rs, sql);
						list.add(result);
					}
				}

				output.setResults(list);
				output.setbSuccess(true);
				break;
			case Sql.TYPE_INSERT:
			case Sql.TYPE_UPDATE:
			case Sql.TYPE_DELETE:
				flag = stmt.executeUpdate();
				conn.commit();
				if (flag > 0) {
					output.setbSuccess(true);
				} else {
					DaoHelperError error = new DaoHelperError(
							"DaoSimpleModel-onlineSimpleExecute",
							"Update Error", "Check the sql");
					List<DaoHelperError> errors = new ArrayList<DaoHelperError>();
					errors.add(error);
					output.setErrors(errors);
				}
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			DaoHelperError error = new DaoHelperError("ERR0210",
					e.getMessage(), "");
			List<DaoHelperError> errors = new ArrayList<DaoHelperError>();
			errors.add(error);
			output.setErrors(errors);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				output.setbSuccess(false);
				throw e;
			}
		}
		return output;
	}

	private DaoHelperOutput onlineComplexExecute(Sql sql) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DaoHelperOutput output = new DaoHelperOutput();
		try {
			String content = sql.getContent();
			System.out.println("sql:" + content);
			String ds = sql.getDateSource();
			if (CommonUtil.checkEmpty(ds)) {
				conn = ConnectionManager.getConnection();
			} else {
				conn = ConnectionManager.getConnection(ds.trim());
			}
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(content);
			List<Field> fields = sql.getConditionFields();
			Field f = null;
			for (int i = 0, len = fields.size(); i < len; i++) {
				f = fields.get(i);
				setCondition(stmt, f);
			}
			int flag = 0;
			int type = sql.getType();
			switch (type) {
			case Sql.TYPE_INQUIRE:
				rs = stmt.executeQuery();
				List<Object> list = new ArrayList<Object>();

				while (rs.next()) {
					Object result = convertRS(rs, sql);
					list.add(result);
				}

				output.setResults(list);
				output.setbSuccess(true);
				break;
			case Sql.TYPE_INSERT:
			case Sql.TYPE_UPDATE:
			case Sql.TYPE_DELETE:
				flag = stmt.executeUpdate();
				conn.commit();
				if (flag > 0) {
					output.setbSuccess(true);
				} else {
					DaoHelperError error = new DaoHelperError(
							"DaoSimpleModel-onlineSimpleExecute",
							"Update Error", "Check the sql");
					List<DaoHelperError> errors = new ArrayList<DaoHelperError>();
					errors.add(error);
					output.setErrors(errors);
				}
				break;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				output.setbSuccess(false);
				throw e;
			}

		}
		return output;
	}

}
