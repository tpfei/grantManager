package org.lanqiao.service.impl;

import org.lanqiao.bean.User;
import org.lanqiao.dao.LoginDao;
import org.lanqiao.dao.impl.LoginDaoImpl;
import org.lanqiao.service.LoginService;

public class LoginServiceImpl implements LoginService {

	LoginDao loginDao;
	public LoginServiceImpl()
	{
		loginDao=new LoginDaoImpl();
	}
	public User login(String username, String password) {
		return loginDao.login(username, password);
	}
}