package com.novel.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.novel.Entity.Novel;
import com.novel.Util.DButil;

public class NovelDAOImpl {
	/**
	 * 通过书名和作者获取小说在数据库表中的id值
	 * @param name
	 * @param author
	 * @return
	 */
    public int getId(String name, String author){
    	int id = 0;
    	
    	String sql = "select * from book where bookName = ? and bookAuthor = ?";
    	Object[] params = {name, author};
    	try{
    		ResultSet rs = DButil.executeQuery(sql, params);
    		if(rs.next()){
    			id = rs.getInt("bookId");
    		}
    	}catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		return id;
    }
    
    /**
     * 通过id获取该小说的全部信息
     * @param id
     * @return
     */
    public Novel get(int id){
    	Novel novel = null;
    	
    	String sql = "select * from book where bookId = ?";
    	Object[] params = {id};
    	try{
    		ResultSet rs = DButil.executeQuery(sql, params);
    		if(rs.next()){
    			novel = new Novel();
                String name = rs.getString("bookName");
                String author = rs.getString("bookAuthor");
                String intro = rs.getString("bookIntro");
                String src = rs.getString("bookImg");
                String type = rs.getString("bookType");
                String sign = rs.getString("bookSign");
                
                novel.setId(id);
                novel.setName(name);
                novel.setAuthor(author);
                novel.setIntro(intro);
                novel.setSrc(src);
                novel.setType(type);
                novel.setSign(sign);
    		}
    		return novel;
    	}catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 通过id获取小说是否是热门推荐或者新书推荐领域
     * @param id
     * @return
     */
    public String getSign(int id){
    	String sign = "";
    	
    	String sql = "select * from book where bookId = ?";
    	Object[] params = {id};
    	try{
    		ResultSet rs = DButil.executeQuery(sql, params);
    		if(rs.next()){
    			sign = rs.getString("bookSign");
    		}
    	}catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
		return sign;
    }
    
    /**
     * 获取novel表中所有的记录
     * @param start
	 * @param count
     * @return
     */
    public List<Novel> list() {
        return list(0, Short.MAX_VALUE);
    }
    public List<Novel> list(int start, int count){
    	List<Novel> novels = new ArrayList<Novel>();
    	
    	String sql = "select * from book order by bookId asc limit ?,? ";
        Object[] params = {start, count};
        try{
        	ResultSet rs = DButil.executeQuery(sql, params);
 
            while (rs.next()) {
            	Novel novel = new Novel();
                int id = rs.getInt("bookId");
                String name = rs.getString("bookName");
                String author = rs.getString("bookAuthor");
                String intro = rs.getString("bookIntro");
                String src = rs.getString("bookImg");
                String type = rs.getString("bookType");
                String sign = rs.getString("bookSign");

                novel.setId(id);
                novel.setName(name);
                novel.setAuthor(author);
                novel.setIntro(intro);
                novel.setSrc(src);
                novel.setType(type);
                novel.setSign(sign);
                
                novels.add(novel);
            }
            return novels;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
