package com.zj.mvcapp.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	/**
	 * 释放 Connection 连接
	 * @param connection
	 */
	public static void realeseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static DataSource dataSource = null;
	
	static{
		//数据源只能初始化一次
		dataSource = new ComboPooledDataSource("mvcapp");
	}
			
	
	/**
	 * 返回一个数据源的 Connection 连接
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
