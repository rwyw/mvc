package com.zj.mvcapp.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	/**
	 * �ͷ� Connection ����
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
		//����Դֻ�ܳ�ʼ��һ��
		dataSource = new ComboPooledDataSource("mvcapp");
	}
			
	
	/**
	 * ����һ������Դ�� Connection ����
	 * @return
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
