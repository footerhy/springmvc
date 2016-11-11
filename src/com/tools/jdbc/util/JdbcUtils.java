package com.tools.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tools.log.util.LogUtils;

public class JdbcUtils {
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/test?user=root&password=root&useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void free(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			if(rs != null){
				rs.close();
			}
			
			if(ps != null){
				ps.close();
			}
			
			if(null != conn){
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.error(e);
		}
		
	}
}
