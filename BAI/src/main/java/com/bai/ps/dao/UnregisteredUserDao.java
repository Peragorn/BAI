package com.bai.ps.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.bai.ps.database.HibernateUtil;
import com.bai.ps.model.UnregisteredUser;
import com.bai.ps.model.User;

public class UnregisteredUserDao {

	public void addUnregisteredUser(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();
	    UnregisteredUser unregisteredUser = new UnregisteredUser();
	    unregisteredUser.setName(user.getName());
	    unregisteredUser.setPassword_hash(user.getPassword_hash());
	    int randomNum = ThreadLocalRandom.current().nextInt(2, 10 + 1);
	    unregisteredUser.setLoginAttempt(randomNum);
	    session.save(unregisteredUser);
	    tx.commit();
	}

	public boolean isUserRegistered(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(UnregisteredUser.class);
        criteria.add(Restrictions.like("name", user.getName()));
        
        List<UnregisteredUser> list = criteria.list();
        session.close();
        
        
        if(list != null && !list.isEmpty()){
        	return true;
        }
        else{
        	addUnregisteredUser(user);
        	return false;
        }
	}

	public Date isLoginAvailable(User user) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(UnregisteredUser.class);
        criteria.add(Restrictions.like("name", user.getName()));
        criteria.add(Restrictions.ge("accountLoginBlocked", new Date()));
        
        List<UnregisteredUser> list = criteria.list();
        session.close();
        if(list != null && !list.isEmpty()){
        	return list.get(0).getAccountLoginBlocked();
        }
        else{
        	return null;
        }
	}

	public void setLastFailLogin(User user) {
		UnregisteredUser actualUser = getUserByName(user.getName());
		
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
	
	public UnregisteredUser getUserByName(String name){
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    Transaction tx = session.beginTransaction();

        Criteria criteria = session.createCriteria(UnregisteredUser.class);
        criteria.add(Restrictions.like("name", name));
        
        List<UnregisteredUser> list = criteria.list();
        
        session.close();
        return list.get(0);
	}

}
