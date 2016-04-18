package com.base.common.util;

import java.util.StringTokenizer;
import java.util.UUID;

public class UUIDGenerator {
	
	public static String genFileName() {
		String uuid =  UUID.randomUUID().toString();
		StringTokenizer token = new StringTokenizer(uuid,"-");
		StringBuffer sb = new StringBuffer();
		while(token.hasMoreElements()) {
			sb.append(token.nextToken());
		}
		return sb.toString().toUpperCase();
	}
	
	public static String getCommUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(UUIDGenerator.genFileName());
	}
}
