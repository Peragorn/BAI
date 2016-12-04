package com.bai.ps.business;

import java.util.List;

import com.bai.ps.dao.UserDao;
import com.bai.ps.model.User;

public class UserBusiness{
	
	public UserDao userDao = new UserDao();

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	public User login(User user) {
		User userloged = this.userDao.login(user);
		if(userloged == null){
			this.userDao.setLastFailLogin(user);
		}
		return userloged;
	}
	
	public List<User> getUsersList(){
		return this.userDao.getUsersList();
	}

	public User getUserByID(long id){
		return this.userDao.getUserByID(id);
	}

	/**
	 * Metoda do ustawiania po ilu probach ma zablokowac konto
	 * @param n
	 */
	public void setLoginAttemptCounter(User user){
		this.userDao.setLoginAttemptCounter(user);
	}
}
