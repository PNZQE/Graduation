package com.novel.Dao;

import java.util.List;

import com.novel.Entity.Novel;

public interface NovelDAO {
	/**
	 * 通过书名和作者获取小说在数据库表中的id值
	 * @param name
	 * @param author
	 * @return
	 */
    public int getId(String name, String author);
    
	/**
     * 通过id获取该小说的全部信息
     * @param id
     * @return
     */
    public Novel get(int id);
    
    /**
     * 通过id获取小说是否是热门推荐或者新书推荐领域
     * @param id
     * @return
     */
    public String getSign(int id);
	
	/**
     * 获取novel表中所有的记录
     * @return
     */
    public List<Novel> list();
}
