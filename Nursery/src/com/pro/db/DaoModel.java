package com.pro.db;

import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;

import com.util.Const;

public abstract class DaoModel {

	protected abstract DaoHelperOutput execute(DaoHelperInput input)
			throws Exception ;

	protected void setCondition(PreparedStatement ps, Field field) throws Exception{
		int type = field.getType();
		int index = field.getIndex();
		Object value = field.getValue();
		if (type < Const.FIELD_TYPE_TINYINT || type > Const.FIELD_TYPE_TIMESTAMP) {
			throw new Exception();
		} else {
			try {
				switch(type) {
				case Const.FIELD_TYPE_INT:
					Integer it = (Integer)value;
					ps.setInt(index, it.intValue());
					break;
				case Const.FIELD_TYPE_CHAR:
				case Const.FIELD_TYPE_VARCHAR:
					ps.setString(index, (String)value);
					break;
				case Const.FIELD_TYPE_DATE:
					ps.setDate(index, (Date)value);
					break;
				case Const.FIELD_TYPE_FLOAT:
					Float f = (Float)value;
					ps.setFloat(index, f.floatValue());
					break;
				case Const.FIELD_TYPE_DOUBLE:
					Double b = (Double)value;
					ps.setDouble(index, b.doubleValue());
					break;
				case Const.FIELD_TYPE_TIME:
					ps.setTime(index, (Time)value);
					break;
				case Const.FIELD_TYPE_TIMESTAMP:
					ps.setTimestamp(index, (Timestamp)value);
					break;
				case Const.FIELD_TYPE_BLOB:
					ps.setBlob(index, (Blob)value);
					break;
				default:
					ps.setObject(index, value);
				}
			} catch (Exception e) {
				throw e;
			}
		}

	}


	protected Object convertRS(ResultSet rs, Sql sql) throws Exception {
		InquireConvert ic = sql.getConvert();
		if (ic == null) {
				throw new Exception ();
		} else {
			return ic.convert(rs);
		}
	}
}
