package org.lanqiao.service.impl;

import org.lanqiao.bean.User;
import org.lanqiao.dao.UserDao;
import org.lanqiao.dao.impl.UserDaoImpl;
import org.lanqiao.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao;
	public UserServiceImpl()
	{
		userDao=new UserDaoImpl();
	}
	public int saveUser(User user) {
		
		return userDao.saveUser(user);
	}
}