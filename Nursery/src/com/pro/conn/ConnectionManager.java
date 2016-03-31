/**
 *
 */
package com.pro.conn;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.util.Const;

public class ConnectionManager {

    public static Connection getConnection() throws Exception{
        return getConnection( Const.SYS_DS_JNDI);
    }

    public static Connection getConnection(String jndi) throws Exception {
    	InitialContext ctx = null;
    	DataSource ds = null;
    	try {
    		ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/" + jndi);
    	} catch (Exception e) {
    		throw e;
    	}

    	try {
    		Connection conn = ds.getConnection();
            return conn;
    	} catch (Exception e) {
    		throw e;
    	}
    }

}
