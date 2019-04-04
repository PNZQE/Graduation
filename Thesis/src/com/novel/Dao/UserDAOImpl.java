package com.novel.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.novel.Entity.User;
import com.novel.Util.DButil;

public class UserDAOImpl {
	/**
	 * 获取数据库user表中数据总数，即用户总数
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
	 * 往user表中添加数据
	 */
	public boolean add(User user) {
		String sql = "insert into user(userName,userPwd,userPhone) values(?,?,?)";
	    Object[] params = {user.getName(), user.getPsd(), user.getPhone()};
        try{
        	//数据库user表被清空时，数据 重置自增长即id从1开始
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
	 * 更新user表的数据
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
	 * 删除user表的数据
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
	 * 通过手机号获取用户信息
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
	 * 判断用户输入的手机号是否在表中已经存在
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
	 * 获取user表中所有的记录
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
