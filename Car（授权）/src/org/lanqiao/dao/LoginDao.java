package org.lanqiao.dao;

import org.lanqiao.bean.User;

public interface LoginDao {

	public User login(String username,String password);
}