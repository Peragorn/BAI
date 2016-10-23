package com.bai.business;

import com.bai.dao.UserDao;
import com.bai.model.User;

public class UserBusiness{
	
	public UserDao userDao = new UserDao();

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

}
