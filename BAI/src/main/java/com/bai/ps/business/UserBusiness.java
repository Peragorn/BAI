package com.bai.ps.business;

import com.bai.ps.dao.UserDao;
import com.bai.ps.model.User;

public class UserBusiness{
	
	public UserDao userDao = new UserDao();

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	public User login(User user) {
		return this.userDao.login(user);
	}

}
