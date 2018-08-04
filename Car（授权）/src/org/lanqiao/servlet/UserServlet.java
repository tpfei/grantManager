package org.lanqiao.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.User;
import org.lanqiao.service.UserService;
import org.lanqiao.service.impl.UserServiceImpl;
import org.lanqiao.util.TransactionManager;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		int lastIndex=uri.lastIndexOf("/");
		uri=uri.substring(lastIndex+1);
		if("addUser.do".equals(uri))
		{
			request.getRequestDispatcher("user/addUser.jsp").forward(request, response);
		}
		else if("saveUser.do".equals(uri))
		{
			String username=request.getParameter("username");
			User user=new User();
			
			user.setUser_id(UUID.randomUUID().toString());
			user.setUser_name(username);
			
			UserService userService=new TransactionManager<UserService>().createProxy(new UserServiceImpl());
			userService.saveUser(user);

		}
		else if("deleteUser.do".equals(uri))
		{
		}
	}
}