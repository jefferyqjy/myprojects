package com.sys.web.refnum.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


public class RefNumManager {
	
	public static int getRefNum(String table,String id) {
		try {
		String configPath = RefNumManager.class.getResource("").getPath()+"/refnum.properties";
		InputStream in = new FileInputStream(configPath);
		Properties props = new Properties();
		props.load(in);
		in.close();
		OutputStream out = new FileOutputStream(configPath);
		String key = new StringBuffer(table).append("_").append(id).toString();
		int current = Integer.parseInt(props.getProperty(key));
		current++;
		props.setProperty(key, String.valueOf(current));
		props.store(out, "modify");
		out.close();
		return current;
		}catch(Exception e) {
			return 0;
		}
	}
	
	public static int refuceRefNum(String table,String id) throws Exception {
		String configPath = RefNumManager.class.getResource("").getPath()+"/refnum.properties";
		InputStream in = new FileInputStream(configPath);
		Properties props = new Properties();
		props.load(in);
		in.close();
		OutputStream out = new FileOutputStream(configPath);
		String key = new StringBuffer(table).append("_").append(id).toString();
		int current = Integer.parseInt(props.getProperty(key));
		current--;
		props.setProperty(key, String.valueOf(current));
		props.store(out, "modify");
		out.close();
		return current;
	}
}
