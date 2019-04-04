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

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public AddUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		JSONObject json = JSONObject.fromObject(data);
		User user = (User)JSONObject.toBean(json,com.novel.Entity.User.class);
		
		//判断用户注册的手机号是否已被注册
		UserDAOImpl UserDao = new UserDAOImpl();
		int count = UserDao.checkPhone(user.getPhone());
		if(count==0){
			UserDao.add(user);
		}else{
			response.setContentType("text/text");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("false");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
