package com.bai.ps.business;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bai.ps.dao.UserDao;
import com.bai.ps.model.User;

public class UserBusiness{
	
	public UserDao userDao = new UserDao();

	public void addUser(User user) {
		this.userDao.addUser(user);
	}

	public User login(User user) {
		User userloged = null;
		Date timeToWait = this.userDao.isLoginAvailable(user);
		if( timeToWait == null){
			userloged = this.userDao.login(user);
		}
		else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(timeToWait);
			System.out.println("Kolejna probe logowanie mozesz podjac o godzinie " + cal.getTime().getHours()+":"+cal.getTime().getMinutes()+":"+cal.getTime().getSeconds() );
			return null;		
		}
				
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
