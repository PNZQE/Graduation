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
	
	//�������ݿ�
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(Driver);
		return DriverManager.getConnection(URL, User, Password);
	}
	
	//�ر����ݿ�
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
	 * executeUpdate����������ִ�� INSERT��UPDATE�� DELETE����Լ� SQL DDL�����ݶ������ԣ���䣬���� CREATE TABLE�� DROP TABLE
	 * ����ֵ��һ��������ָʾ��Ӱ��������������¼�����
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
	 * executeQuery���������ڲ��������������ResultSet������䣬����SELECT���
	 * ���ش�����ѯ�����ResultSet����
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultSet executeQuery(String sql,Object[] params) {
		try {
			pstmt =createPreparedStatement(sql, params);
			rs = pstmt.executeQuery();
			if(rs==null) {
				System.out.println("������");
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