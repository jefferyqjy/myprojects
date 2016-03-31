package com.pro.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pro.conn.ConnectionManager;

public class DaoBatchModel extends DaoModel {

	protected DaoHelperOutput execute(DaoHelperInput input) throws Exception {
		List<Sql> sqls = input.getSqls();
		if (sqls != null) {
			// there is only one sql in the simple model.
			if (input.isOnline()) {
				return onlineExecute(sqls);
			} else {
				return offlineExecute(sqls);
			}

		} else {
			throw new Exception();
		}
	}

	protected DaoHelperOutput onlineExecute(List<Sql> sqls) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DaoHelperOutput output = new DaoHelperOutput();
		List<Object> list = new ArrayList<Object>();
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			for (Sql sql:sqls) {
				String content = sql.getContent();
				stmt = conn.prepareStatement(content);
				if (!sql.isSimpleSql()) {
					List <Field> fields = sql.getConditionFields();
					Field f = null;
					for (int i = 0, len = fields.size(); i < len; i++) {
						f = fields.get(i);
						setCondition(stmt, f);
					}
				}
				int type = sql.getType();
				int flag = 0;
				switch (type) {
				case Sql.TYPE_INQUIRE:
					rs = stmt.executeQuery();
					List<Object> rsList = new ArrayList<Object>();

					while(rs.next()) {
						Object result = convertRS(rs, sql);
						rsList.add(result);
					}
					list.add(rsList);

					break;
				case Sql.TYPE_INSERT:
				case Sql.TYPE_UPDATE:
				case Sql.TYPE_DELETE:
					flag = stmt.executeUpdate();

					if (flag <= 0) {
						DaoHelperError error = new DaoHelperError("DaoSimpleModel-onlineSimpleExecute", "Update Error", "Check the sql");
						List<DaoHelperError> errors = new ArrayList<DaoHelperError>();
						errors.add(error);
						output.setErrors(errors);
						return output;
					}
					break;
				}

			}
			output.setResults(list);
			output.setbSuccess(true);
			conn.commit();
		} catch (Exception e) {
			DaoHelperError error = new DaoHelperError("ERR0210", e.getMessage(), "");
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

	private DaoHelperOutput offlineExecute(List<Sql> sqls) throws Exception {
		Thread t = new DaoBatchThread(sqls);
		t.start();
		DaoHelperOutput output = new DaoHelperOutput();
		output.setbSuccess(true);
		return output;
	}


}
