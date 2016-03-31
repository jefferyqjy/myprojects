package com.pro.db;


public class DaoSimpleThread extends Thread{

	private Sql sql;

	protected DaoSimpleThread(Sql sql) {
		this.sql = sql;
	}
	public void run(){
		try {
			DaoSimpleModel model = new DaoSimpleModel();
			model.onlineExecute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
