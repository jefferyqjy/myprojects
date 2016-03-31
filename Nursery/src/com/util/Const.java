package com.util;

public class Const {

    public static final String ERROR_MSG_REPLACEMENT = "%r%";

    public static final String SYS_DB_UTIL_TYPE = "SYS_DB_UTIL_TYPE";
    public static final String SYS_DS_JNDI = "DS_TRX";

    public static final String SYS_DB_DEFAULT_SCHEMA = "ACXDB";

	public final static int FIELD_TYPE_TINYINT = 1;
	public final static int FIELD_TYPE_SMALLINT = 2;
	public final static int FIELD_TYPE_MEDIUMINT = 3;
	public final static int FIELD_TYPE_INT = 4;
	public final static int FIELD_TYPE_BIGINT = 5;
	public final static int FIELD_TYPE_FLOAT = 6;
	public final static int FIELD_TYPE_DOUBLE = 7;
	public final static int FIELD_TYPE_DECIMAL = 8;

	public final static int FIELD_TYPE_CHAR = 9;
	public final static int FIELD_TYPE_VARCHAR = 10;
	public final static int FIELD_TYPE_TINYBLOB = 11;
	public final static int FIELD_TYPE_TINYTEXT = 12;
	public final static int FIELD_TYPE_BLOB = 13;
	public final static int FIELD_TYPE_TEXT = 14;
	public final static int FIELD_TYPE_MEDIUMBLOB = 15;
	public final static int FIELD_TYPE_MEDIUTEXT = 16;
	public final static int FIELD_TYPE_LOGNGBLOB = 17;
	public final static int FIELD_TYPE_LONGTEXT = 18;

	public final static int FIELD_TYPE_DATE = 19;
	public final static int FIELD_TYPE_TIME = 20;
	public final static int FIELD_TYPE_YEAR = 21;
	public final static int FIELD_TYPE_DATETIME = 22;
	public final static int FIELD_TYPE_TIMESTAMP = 23;

	public final static String JDBC_DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	public final static String JDBC_DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
	public final static String CONN_TYPE_MYSQL = "mysql";
	public final static String CONN_TYPE_ORACLE = "oracle";



	public static boolean isCommand(String command[], String src){
		if(command == null || src == null || "".equals(src)){
			return false;
		}
		int len = command.length;
		for(int i = 0; i  < len; i++){
			if (src.equals(command[i])) {
				return true;
			}
		}

		return false;
	}
}
