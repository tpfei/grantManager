package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.bean.Role;
import org.lanqiao.service.RoleService;
import org.lanqiao.service.impl.RoleServiceImpl;
import org.lanqiao.util.PageModel;
import org.lanqiao.util.TransactionManager;

public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//一个地方用到乱码解决
		
		String uri=request.getRequestURI();
		int lastIndex=uri.lastIndexOf("/");
		uri=uri.substring(lastIndex+1);
		if("saveRole.do".equals(uri))
		{
			RoleService roleService=new TransactionManager<RoleService>().createProxy(new RoleServiceImpl());
			String roleName=request.getParameter("rolename");
			Role role=new Role();
			role.setRole_id(UUID.randomUUID().toString());
			role.setRole_name(roleName);//从JSP页面获取的
			if(roleService.saveRole(role)==1)
			{
				request.getRequestDispatcher("viewRole.do").forward(request, response);
			}
		}
		else if("viewRole.do".equals(uri))
		{
			String currentPage=request.getParameter("currentPage");
			if(currentPage==null)
			{
				currentPage="1";
			}
			RoleService roleService=new TransactionManager<RoleService>().createProxy(new RoleServiceImpl());
			PageModel<Role> pm=roleService.getAllRole(Integer.parseInt(currentPage));//第一页
			request.setAttribute("pm",pm);
			request.setAttribute("currentPage",currentPage);
			request.getRequestDispatcher("role/viewRole.jsp").forward(request, response);
			//跳转到viewRole.jsp
		}
		else if("grantRole.do".equals(uri))
		{
			String roleId=request.getParameter("roleId");
			
			RoleService roleService=new TransactionManager<RoleService>().createProxy(new RoleServiceImpl());
			Role role=roleService.getRoleGrant(roleId);
			
			//查询到role（role包含了菜单、菜单包含了权限），role是什么？是Model   MVC的M
			
			request.setAttribute("role",role);
			
			request.getRequestDispatcher("role/grantRole.jsp").forward(request, response);
		}
	}
}