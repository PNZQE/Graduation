package com.novel.Dao;

import java.util.List;

import com.novel.Entity.User;
 
public interface UserDAO {
	
	/**
	 * ��ȡ���ݿ�user�����������������û�����
	 * @return
	 */
    public int getTotal();
 
    /**
     * ��user�����������
     * @param user
     * @return
     */
    public boolean add(User user);
 
    /**
     * ����user�������
     * @param user
     * @return
     */
    public boolean update(User user);
 
    /**
     * ɾ��user�������
     * @param id
     * @return
     */
    public boolean delete(int id);
 
    /**
     * ͨ��id��ȡ�û���Ϣ
     * @param id
     * @return
     */
    public User get(int id);
    
    /**
     * �ж�����������Ƿ���user����userPhone�е�ĳ����ֵ��ͬ
     * @return
     */
    public boolean checkPhone(String phone);
 
    /**
     * ��ȡuser�������еļ�¼
     * @return
     */
    public List<User> list();
}
