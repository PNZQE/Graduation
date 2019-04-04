package com.novel.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.novel.Entity.User;
import com.novel.Util.DButil;

public class UserDAOImpl {
	/**
	 * ��ȡ���ݿ�user�����������������û�����
	 * @return
	 */
	public int getTotal() {
        int total = 0;
        
        String sql = "select count(*) from user";
        try{
            ResultSet rs = DButil.executeQuery(sql, null);
            while (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
	
	/**
	 * ��user�����������
	 */
	public boolean add(User user) {
		String sql = "insert into user(userName,userPwd,userPhone) values(?,?,?)";
	    Object[] params = {user.getName(), user.getPsd(), user.getPhone()};
        try{
        	//���ݿ�user�����ʱ������ ������������id��1��ʼ
        	int count = getTotal();
        	if(count == 0){
        		String truncate = "truncate table user";
        		DButil.executeUpdate(truncate,null);
        	}
        	
			boolean isUpdate = DButil.executeUpdate(sql,params);
            return isUpdate;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
	
	/**
	 * ����user�������
	 */
	public boolean update(User user) {
        String sql = "update user set userName= ?, userPwd = ? , userPhone = ? where userId = ?";
        Object[] params = {user.getName(), user.getPsd(), user.getPhone(), user.getId()};
        try{
        	boolean isUpdate = DButil.executeUpdate(sql,params);
        	return isUpdate;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
 
    }
	
	/**
	 * ɾ��user�������
	 * @param id
	 * @return
	 */
	public boolean delete(int id) {
		String sql = "delete from user where userId = ?";
		Object[] params = {id};
        try{
            boolean isDelete = DButil.executeUpdate(sql,params);
            return isDelete;
 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	/**
	 * ͨ���ֻ��Ż�ȡ�û���Ϣ
	 * @param id
	 * @return
	 */
	public User get(String phone) {
    	User user = null;
    	String sql = "select * from user where userPhone = ?";
    	Object[] params = {phone};
        try{
        	ResultSet rs = DButil.executeQuery(sql, params);
            if (rs.next()) {
            	user = new User();
            	int id = rs.getInt("userId");
                String name = rs.getString("userName");
                String psd = rs.getString("userPwd");
                String like = rs.getString("userLike");
                String history = rs.getString("userHistory");
                
                user.setId(id);
                user.setName(name);
                user.setPsd(psd);
                user.setPhone(phone);
                user.setLike(like);
                user.setHistory(history);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	/**
	 * �ж��û�������ֻ����Ƿ��ڱ����Ѿ�����
	 * @param phone
	 * @return
	 */
	public int checkPhone(String phone) {
		int count = 0;
		String sql = "select count(*) from user where userPhone = ?";
		Object[] params = {phone};
        try{
        	ResultSet rs = DButil.executeQuery(sql, params);
            while (rs.next()) {
            	count = rs.getInt(1);
            }
 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
	
	/**
	 * ��ȡuser�������еļ�¼
	 * @param start
	 * @param count
	 * @return
	 */
	public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }
	
	public List<User> list(int start, int count) {
        List<User> users = new ArrayList<User>();
 
        String sql = "select * from user order by userId asc limit ?,? ";
        Object[] params = {start, count};
        try{
        	ResultSet rs = DButil.executeQuery(sql, params);
 
            while (rs.next()) {
            	User user = new User();
                int id = rs.getInt("userId");
                String name = rs.getString("userName");
                String psd = rs.getString("userPwd");
                String phone = rs.getString("userPhone");

                user.setId(id);
                user.setName(name);
                user.setPsd(psd);
                user.setPhone(phone);
                
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
