package com.novel.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.novel.Entity.Swiper;
import com.novel.Util.DButil;

public class SwiperDAOImpl {
	/**
	 * 获取swiper表中所有的记录
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Swiper> list() {
        return list(0, Short.MAX_VALUE);
    }
	
	public List<Swiper> list(int start, int count) {
        List<Swiper> swipers = new ArrayList<Swiper>();
 
        String sql = "select * from swiper order by swiperId asc limit ?,? ";
        Object[] params = {start, count};
        try{
        	ResultSet rs = DButil.executeQuery(sql, params);
 
            while (rs.next()) {
            	Swiper swiper = new Swiper();
                int id = rs.getInt("swiperId");
                String type = rs.getString("type");
                String title = rs.getString("title");
                String url = rs.getString("url");
                String href = rs.getString("href");

                swiper.setId(id);
                swiper.setType(type);
                swiper.setTitle(title);
                swiper.setUrl(url);
                swiper.setHref(href);
                
                swipers.add(swiper);
            }
            return swipers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
