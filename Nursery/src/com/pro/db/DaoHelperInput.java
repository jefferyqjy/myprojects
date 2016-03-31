package com.pro.db;

import java.util.List;

import com.pro.conn.IDaoHelperInput;

public class DaoHelperInput implements IDaoHelperInput {

	/**
	 * type is the operation type.
	 * Value are
	 * TYPE_INQUIRE : select * from xx
	 * TYPE_INSERT: insert into xx values
	 * TYPE_UPDATE: update xx set x=y
	 * TYPE_DELETE: delete from xx where x = y
	 */
	private int type;

	/**
	 * isBatch is the flag for the multiple sqls.
	 */
	private boolean isBatch = false;
	/**
	 * batchNum is the number for the multiple sqls.
	 */
	private int batchNum = 0;

	/**
	 * needResponse is the flag for the response.
	 * If it is true it will give the return value.
	 */
	private boolean isOnline = true;
	/**
	 * toSchedule is the flag for the schedule.
	 * If toSchedule is true it will send to the Schedule Manager to handle this.
	 * If toSchedule is false the request will be done immediately...
	 */
	private boolean toSchedule = false;

	/**
	 * scheduleInfo is the schedule information
	 */
	private Object scheduleInfo;

	private List<Sql> sqls;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isBatch() {
		return isBatch;
	}

	public void setBatch(boolean isBatch) {
		this.isBatch = isBatch;
	}

	public int getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(int batchNum) {
		this.batchNum = batchNum;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isToSchedule() {
		return toSchedule;
	}

	public void setToSchedule(boolean toSchedule) {
		this.toSchedule = toSchedule;
	}

	public Object getScheduleInfo() {
		return scheduleInfo;
	}

	public void setScheduleInfo(Object scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}

	public List<Sql> getSqls() {
		return sqls;
	}

	public void setSqls(List<Sql> sqls) {
		this.sqls = sqls;
	}

}
