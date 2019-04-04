package com.novel.Dao;

import java.util.List;

import com.novel.Entity.User;
 
public interface UserDAO {
	
	/**
	 * 获取数据库user表中数据总数，即用户总数
	 * @return
	 */
    public int getTotal();
 
    /**
     * 往user表中添加数据
     * @param user
     * @return
     */
    public boolean add(User user);
 
    /**
     * 更新user表的数据
     * @param user
     * @return
     */
    public boolean update(User user);
 
    /**
     * 删除user表的数据
     * @param id
     * @return
     */
    public boolean delete(int id);
 
    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    public User get(int id);
    
    /**
     * 判断输入的数据是否与user表中userPhone列的某个数值相同
     * @return
     */
    public boolean checkPhone(String phone);
 
    /**
     * 获取user表中所有的记录
     * @return
     */
    public List<User> list();
}
