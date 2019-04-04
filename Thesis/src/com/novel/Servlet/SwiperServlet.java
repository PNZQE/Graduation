package com.novel.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.novel.Dao.SwiperDAOImpl;
import com.novel.Entity.Swiper;
import net.sf.json.JSONArray;

@WebServlet("/SwiperServlet")
public class SwiperServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public SwiperServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SwiperDAOImpl SwiperDao = new SwiperDAOImpl();
		List<Swiper> swipers = SwiperDao.list();
		JSONArray jsonArr =JSONArray.fromObject(swipers);
		String jsonStr = jsonArr.toString();
		response.setContentType("text/text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
