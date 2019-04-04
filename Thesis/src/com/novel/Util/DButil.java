package com.novel.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DButil {
	private static final String URL= "jdbc:mysql://127.0.0.1:3306/reading"
		+ "?serverTimezone=UTC&useUnicode=true"
		+ "&characterEncoding=utf8"
		+ "&characterSetResults=utf8"
		+ "&useSSL=false"
		+ "&verifyServerCertificate=false"
		+ "&autoReconnct=true"
		+ "&autoReconnectForPools=true"
		+ "&allowMultiQueries=true";
	private static final String User= "sa";
	private static final String Password="admin";
	private static final String Driver="com.mysql.jdbc.Driver";
	
	public static Connection connection =null;
	public static PreparedStatement pstmt =null;
	public static ResultSet rs=null;
	
	//连接数据库
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(Driver);
		return DriverManager.getConnection(URL, User, Password);
	}
	
	//关闭数据库
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) throws SQLException{
		if(rs!=null){
			rs.close();
		}
		if(stmt!=null){
			stmt.close();
		}
		if(connection!=null){
			connection.close();
		}
	}
	
	public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws SQLException, ClassNotFoundException {
		pstmt = getConnection().prepareStatement(sql);
		if(params!=null) {
			for (int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}
	
	/**
	 * executeUpdate方法，用于执行 INSERT、UPDATE或 DELETE语句以及 SQL DDL（数据定义语言）语句，例如 CREATE TABLE和 DROP TABLE
	 * 返回值是一个整数，指示受影响的行数（即更新计数）
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public static boolean executeUpdate(String sql,Object[] params) throws SQLException {
		try {
			pstmt = createPreparedStatement(sql, params);
			int count = pstmt.executeUpdate();
			if(count>0)
				return true;
			else 
				return false;	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			closeAll(null,pstmt,connection);
		}
	}
	
	/**
	 * executeQuery方法，用于产生单个结果集（ResultSet）的语句，例如SELECT语句
	 * 返回代表查询结果的ResultSet对象
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql,Object[] params) {
		try {
			pstmt =createPreparedStatement(sql, params);
			rs = pstmt.executeQuery();
			if(rs==null) {
				System.out.println("无数据");
			}
			return rs;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
