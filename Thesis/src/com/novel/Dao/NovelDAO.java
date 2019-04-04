package com.novel.Dao;

import java.util.List;

import com.novel.Entity.Novel;

public interface NovelDAO {
	/**
	 * ͨ�����������߻�ȡС˵�����ݿ���е�idֵ
	 * @param name
	 * @param author
	 * @return
	 */
    public int getId(String name, String author);
    
	/**
     * ͨ��id��ȡ��С˵��ȫ����Ϣ
     * @param id
     * @return
     */
    public Novel get(int id);
    
    /**
     * ͨ��id��ȡС˵�Ƿ��������Ƽ����������Ƽ�����
     * @param id
     * @return
     */
    public String getSign(int id);
	
	/**
     * ��ȡnovel�������еļ�¼
     * @return
     */
    public List<Novel> list();
}
