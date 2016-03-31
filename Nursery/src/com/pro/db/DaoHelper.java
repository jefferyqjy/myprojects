package com.pro.db;

import com.pro.conn.IDaoHelper;
import com.pro.conn.IDaoHelperInput;
import com.pro.conn.IDaoHelperOutput;

public class DaoHelper implements IDaoHelper {

	private DaoHelperInput input;
	@Override
	public IDaoHelperOutput execute(IDaoHelperInput input)
			throws Exception {
		if (input != null) {
			if (input instanceof DaoHelperInput) {
				this.input = (DaoHelperInput)input;
				return execute();
			} else {
				throw new Exception();
			}
		} else {
			throw new Exception();
		}
	}

	private DaoHelperOutput execute() throws Exception{
		if (input.isToSchedule()) {
			return submitSchedule();
		} else {
			if (input.isBatch()) {
				return submitBatch();
			} else {
				return submitSimple();
			}
		}
	}

	private DaoHelperOutput submitSimple() throws Exception{
		DaoModel model = new DaoSimpleModel();
		return model.execute(input);
	}

	private DaoHelperOutput submitSchedule() throws Exception{
		DaoModel model = new DaoScheduleModel();
		return model.execute(input);
	}

	private DaoHelperOutput submitBatch() throws Exception{
		DaoModel model = new DaoBatchModel();
		return model.execute(input);
	}
}
