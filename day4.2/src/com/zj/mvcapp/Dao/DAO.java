package com.zj.mvcapp.Dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zj.mvcapp.utils.JDBCUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

public class DAO<T> {
	
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;
	
	public DAO(){
		Type superClass = getClass().getGenericSuperclass();
	
		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;
			Type [] typeArgs = parameterizedType.getActualTypeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}
		}
	}
	
	/**
	 * 返回某一个字段的值：例如返回某一条记录的 CustomerName, 或返回数据表中有多少条记录
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql, Object ...args){
		Connection connection = null;
		
		try {
			connection = JDBCUtils.getConnection();
			return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.realeseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 返回所对应的 List
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql, Object ...args){
		Connection connection = null;
		
		try {
			connection = JDBCUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.realeseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 返回对应的 T 的一个实例类的对象
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql, Object ... args){
		Connection connection = null;
		
		try {
			connection = JDBCUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanHandler<>(clazz), args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.realeseConnection(connection);
		}
		return null;
	}
	
	/**
	 * 该方法封装了 INSERT、DELETE、UPDEATE 操作
	 * @param sql
	 * @param args
	 */
	public void update(String sql, Object ... args){
		
		Connection connection = null;
		
		try {
			connection = JDBCUtils.getConnection();
			queryRunner.update(connection, sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.realeseConnection(connection);
		}
		
	}
	
}
