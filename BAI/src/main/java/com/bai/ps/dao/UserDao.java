package com.bai.ps.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.Message;
import com.bai.ps.model.User;


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
	    
	    session.update(actualUser);
	    tx.commit();
	}
	
	private User getUserByName(String name){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.like("name", name));
        
        List<User> list = criteria.list();
        
        session.close();
        return list.get(0);
	}
}
