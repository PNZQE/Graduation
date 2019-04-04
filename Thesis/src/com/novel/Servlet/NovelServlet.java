package com.novel.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.novel.Dao.NovelDAOImpl;
import com.novel.Entity.Novel;

import net.sf.json.JSONArray;

@WebServlet("/NovelServlet")
public class NovelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NovelServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NovelDAOImpl NovelDao = new NovelDAOImpl();
		List<Novel> novels = NovelDao.list();
		JSONArray jsonArr =JSONArray.fromObject(novels);
		String jsonStr = jsonArr.toString();
		response.setContentType("text/text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
