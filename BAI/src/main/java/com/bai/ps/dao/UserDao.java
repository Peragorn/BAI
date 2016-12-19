package com.bai.ps.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;
import com.bai.ps.model.UserPasswordMask;


public class UserDao{

	public void addUser(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

	    session.save(user);
	    tx.commit();
	}

	public User login(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", user.getName()));
        criteria.add(Restrictions.eq("accountLocked", false));
        criteria.add(Restrictions.like("password_hash", user.getPassword_hash()));
        
        List<User> list = criteria.list();
        if(list.size() > 0){
            user.setUser_id(list.get(0).getUser_id());
            user.setLast_login(list.get(0).getLast_login());
            user.setSalt(list.get(0).getSalt());
            user.setLoginAttempt(list.get(0).getLoginAttempt());
            user.setLast_fail_login(list.get(0).getLast_fail_login());
            user.setLoginAttemptCounterToSucces(list.get(0).getLoginAttemptCounter());
            user.setLoginAttemptCounter(0);
            session.close();
            user.setLast_login(new Date());
            update(user);
            return user;            
        }
        else{
        	System.out.println("Bledne dane, lub uzytkownik zablokowany");
            return null;
        }
	}

	public List<User> getUsersList() {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        
        List<User> userList = new ArrayList<User>();
        userList = criteria.list();
        
        session.close();
        return userList;
	}

	public User getUserByID(long id){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("user_id", id));
        User u = new User();
        u = (User) criteria.list().get(0);
        
        session.close();   
        return u;
	}

	public void setLoginAttemptCounter(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

	    session.update(user);
	    tx.commit();	
	}
	
	public void update(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

	    session.update(user);
	    tx.commit();	
	}

	public void setLastFailLogin(User user) {
		User actualUser = getUserByName(user.getName());
		
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
	    actualUser.setLast_fail_login(new Date());
	    actualUser.setLoginAttemptCounter(actualUser.getLoginAttemptCounter()+1);
	    if(actualUser.getLoginAttempt() > 0 && actualUser.getLoginAttemptCounter() == actualUser.getLoginAttempt()){
	    	actualUser.setAccountLocked(true);
	    }
	    
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.SECOND, (int) Math.pow(2, actualUser.getLoginAttemptCounter()));
	    actualUser.setAccountLoginBlocked(calendar.getTime());
	    
	    session.update(actualUser);
	    tx.commit();
	}
	
	public User getUserByName(String name){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", name));
        
        List<User> list = criteria.list();
        
        session.close();
        return list.get(0);
	}

	/**
	 * Metoda srpawdza czy mozemy zalogowac sie do konta. Glowny parametr to accountLoginBlocked jezeli jego czas jest wiekszy niz aktualny to oznacza ze musimy jeszcze odczekac.
	 * @param user
	 * @return
	 */
	public Date isLoginAvailable(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", user.getName()));
        criteria.add(Restrictions.ge("accountLoginBlocked", new Date()));
        
        List<User> list = criteria.list();
        session.close();
        if(list != null && !list.isEmpty()){
        	return list.get(0).getAccountLoginBlocked();
        }
        else{
        	return null;
        }
	}

	public UserPasswordMask getUserPasswordMaskByUserId(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserPasswordMask.class);
        criteria.add(Restrictions.eq("user_id", user));
        criteria.add(Restrictions.eq("active", true));
        UserPasswordMask upm = new UserPasswordMask();
        upm = (UserPasswordMask) criteria.list().get(0);
        
        session.close();   
        return upm;
	}
	
	public UserPasswordMask loginPwdMask(UserPasswordMask userPwdMask, String pwd) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(UserPasswordMask.class);
        criteria.add(Restrictions.like("password_hash", pwd));
        criteria.add(Restrictions.eq("active", true));
        UserPasswordMask upw = (UserPasswordMask) criteria.uniqueResult();
        session.close();
        
        return upw;
	}
	
	public boolean changeUserMask(UserPasswordMask userPwdMask){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(UserPasswordMask.class);
        Random rnd = new Random();
        
        criteria.add(Restrictions.eq("user_id", userPwdMask.getUser_id()));
        criteria.add(Restrictions.eq("active", false));
        List<UserPasswordMask> result = criteria.list();
        
        if(result != null && !result.isEmpty()){
        	int index = rnd.nextInt(result.size());
        	//new mask
        	UserPasswordMask upm = result.get(index);
        	upm.setActive(true);
        	session.update(upm);
        	//old mask
        	userPwdMask.setActive(false);
        	session.update(userPwdMask);
        	tx.commit();
        	return true;
        }
        return false;
	}

	public boolean isUserRegistered(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", user.getName()));
        
        List<User> list = criteria.list();
        session.close();
        
        
        if(list != null && !list.isEmpty()){
        	return true;
        }
        else{
        	return false;
        }
	}
}
