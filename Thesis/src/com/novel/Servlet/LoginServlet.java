package com.novel.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.novel.Dao.UserDAOImpl;
import com.novel.Entity.User;

import net.sf.json.JSONObject;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		JSONObject json = JSONObject.fromObject(data);
		User user = (User)JSONObject.toBean(json,com.novel.Entity.User.class);
		
		UserDAOImpl UserDao = new UserDAOImpl();
		User res = UserDao.get(user.getPhone());
		JSONObject jsonObj = JSONObject.fromObject(res);
		String jsonStr = jsonObj.toString();
		response.setContentType("text/text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonStr);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
