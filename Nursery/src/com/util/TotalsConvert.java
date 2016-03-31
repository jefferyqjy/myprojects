package com.util;

import java.sql.ResultSet;

import com.pro.db.InquireConvert;

public class TotalsConvert implements InquireConvert{
	public final static String TOTAL_FIELD_NAME = "totals";
	 public Object convert(ResultSet rs) throws Exception {
		 try {
			 String totalUser = (String)rs.getString(TOTAL_FIELD_NAME);
			 return totalUser;
		 } catch (Exception e) {
			 return null;
		 }
	 }
 }