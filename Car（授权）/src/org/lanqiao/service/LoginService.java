package org.lanqiao.service;

import org.lanqiao.bean.User;

public interface LoginService {

	public User login(String username,String password);
}