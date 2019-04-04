package com.novel.Dao;

import java.util.List;

import com.novel.Entity.Swiper;

public interface SwiperDAO {
	/**
     * 获取swiper表中所有的记录
     * @return
     */
    public List<Swiper> list();
}
