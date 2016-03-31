package com.pro.db;

import java.util.List;

public class DaoBatchThread extends Thread{

private List<Sql> sqls;

	protected DaoBatchThread(List<Sql> sqls) {
		this.sqls = sqls;
	}
	public void run(){
		try {
			DaoBatchModel model = new DaoBatchModel();
			model.onlineExecute(sqls);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
